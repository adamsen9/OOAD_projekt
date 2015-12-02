package controller;

import java.time.LocalDate;
import java.util.ArrayList;

import boundary.IUI;
import entity.dataklasser.HyttePlads;
import entity.dataklasser.Kunde;
import entity.dataklasser.Reservation;
import function.FunctionException;
import function.ReservationsFunc;


public class ConsoleReservationsController extends GeneralController{

	KundeController kc;
	AdminController ac;
	private interface ReservationVælger{ ArrayList<Reservation> vælg();	}
	
	public ConsoleReservationsController(IUI ui, KundeController kc, AdminController ac) {
		super(ui);
		this.kc = kc;
		this.ac = ac;
	}

	@Override
	public void run() {
		String[] menuItems = {"Gå op","Lav reservation","Vis alle reservationer"};
		
		while(true) {
			int valg = ui.visMenu("Costa Kalundborg", menuItems);
			
			switch (valg) {
			case 0:
				//Gå op
				return;
			case 1:
				//Lav reservation
				opretReservation(null);
				break;
			case 2:
				//Vis alle reservationer
				visReservationer();
				break;
			}
		}
		
	}
	
	public void tjekInd() {
		// find/opret kunde
		Kunde kunde = kc.vælgKunde();
		if (kunde == null)
			return;
		
		// find/opret reservation
		String menuTittel = "Tjek ind - vælg reservation";
		String[] menuOptions = {
				"Annullér", 
				"Ny reservation", 
				"Eksisterende reservation"
		};

		ReservationVælger[] menuFunktioner = {
			() -> { return null; },
			() -> { return opretReservation(kunde); },
			() -> { return vælgReservationForKunde(kunde.getId(),Reservation.STATUS_RESERVERET); }
		};

		int input = ui.visMenu(menuTittel, menuOptions);
		if (input == 0)
			return;
		
		// gem oprettede/valgte resevationer
		ArrayList<Reservation> reservationer = menuFunktioner[input].vælg();
		
		// hvis vi ikke kunne/ville vælge en eksisterende reservation
		if (reservationer == null){
			if (input == 2)
				if(ui.bekræft("Vil du oprette en ny reservatione i stedet ?"))
					reservationer = opretReservation(kunde);
		}
		
		// hvis vi hverken har oprettet eller valgt en reservation
		if (reservationer == null)
			return;
		
		// markér valgte reservationer som checket ind
		ReservationsFunc.tjekInd(reservationer);
		ui.besked("Følgende reservationer er checket ind");
		
		
		for (Reservation res : reservationer){
			ui.besked(res.prettyPrint());
		}
	}
	
	public void tjekUd(){
		//TODO
	}
	

	public ArrayList<Reservation> opretReservation(Kunde kunde){
		// Vælg en kunde hvis vi ikke allerede har valgt en
		if (kunde == null)
			kunde = kc.vælgKunde();
		
		// Hvis vi ikke har valg en kunde kan vi ikke lave en reservation
		if (kunde == null)
			return null;
		
		// Vælg type eller specifik hytte/plads
		HyttePlads hyttePlads = ac.vælgHyttePlads();
		
		// Hvis man har valgt en specifik hytte/plads, skal man selvfølgelig kun have én
		// Ellers skal man vælge hvor mange pladser man vil reservere
		int antal = 1;
		if(hyttePlads.getId() == 0){
			String sAntal = null;
			while (sAntal == null){
				sAntal = ui.input("Vælg antal pladser (Intet valg = 1)");
				if (sAntal.equals(""))
					sAntal = "1";
				try {
					antal = Integer.parseInt(sAntal);
				} catch (NumberFormatException e) {
					ui.besked("Den indtastede værdi er ikke et tal");
					sAntal = null;
				}
				if (antal < 1){
					sAntal = null;
					ui.besked("Antal skal være mindst 1");
				}
			}
		}
		
		// Indtastning af oplysninger om reservationen
		String[] sNavne = {
				"Dato for ankomst (yyyy-mm-dd) (intet valg betyder dags dato)", 
				"Dato for afrejse (yyyy-mm-dd) eller skriv antal overnatninger",
				"Antal voksne (Intet valg = "+ Reservation.STANDARD_VOKSNE +")",
				"Antal børn (Intet valg = "+ Reservation.STANDARD_BØRN +")",
				"Antal hunde (Intet valg = "+ Reservation.STANDARD_HUND +")",
		};
		
		Reservation nyReservation = null;
		do {
			String[] svar = ui.multiInput(
				"Indtast oplysninger på ny reservation til \n" 
				+ kunde.prettyPrint(), sNavne);
			try {
				// Forsøg at lave en reservation ud fra de indtastede oplysninger 
				nyReservation = ReservationsFunc.nyFraArray(svar);
			} catch (FunctionException e) {
				ArrayList<String> beskedder = e.getFejlbeskedder(); 
				for (String fejl: beskedder){
					ui.besked(fejl);
				}
				if (!ui.bekræft("ønsker du at prøve igen ?"))
					return null;
			}
		// Forsøg at oprætte reservation indtil det lykkedes eller vi afslutter
		} while (nyReservation == null);

		// Indsæt de valgte oplysninger om hytte/plads i den ønskede reservation
		nyReservation.setPlads_type(hyttePlads.getType());
		nyReservation.setPlads_id(hyttePlads.getId());
		// Indsæt kunde id
		nyReservation.setKunde_id(kunde.getId());
		
		// Kontrollér om der er nok ledige pladser
		// og giv mulighed for at rette reservationen hvis der ikke er plads nok
		int ledige = ReservationsFunc.tjekLedige(hyttePlads, nyReservation.getStart_dato(), nyReservation.getSlut_dato());
		while(ledige < antal){
			String overskrift;
			if (hyttePlads.getId()!= 0){
				overskrift = "Den valgte hytte/plads er ikke ledig";
			} else {
				overskrift = "Der er kun "+ ledige +" ledige hytter/pladser "
						+ "af den valgte type";
			}
			overskrift += " i det valgte tidsrum\nVælg handling";
						
			int input = ui.visMenu(overskrift, new String[]{
							"Afbryd reservation",
							"Vælg en anden ankomstsdato",
							"Vælg en anden afrejsedato",
							"Vælg en anden plads eller hytte",
							"Vælg et andet antal pladser"});
			switch (input) {
			case 0:
				return null;
			case 1:
				nyReservation.setStart_dato(LocalDate.parse(ui.input("Indtast ny dato for ankomst (yyyy-mm-dd)")));
				break;
			case 2:
				nyReservation.setSlut_dato(LocalDate.parse(ui.input("Indtast ny dato for afrejse (yyyy-mm-dd) eller skriv antal overnatninger")));
				break;
			case 3:
				hyttePlads = ac.vælgHyttePlads();
				nyReservation.setPlads_type(hyttePlads.getType());
				nyReservation.setPlads_id(hyttePlads.getId());
				break;
			case 4:
				String sAntal = null;
				while (sAntal == null){
					sAntal = ui.input("Vælg nyt antal pladser");
					try {
						antal = Integer.parseInt(sAntal);
					} catch (NumberFormatException e) {
						ui.besked("Den indtastede værdi er ikke et tal");
						sAntal = null;
					}
				}
				break;
			}
			// Find antal af ledige pladser med de nye oplysninger 
			ledige = ReservationsFunc.tjekLedige(hyttePlads, nyReservation.getStart_dato(), nyReservation.getSlut_dato());
		}
		
		// Opret reservationer i DB 
		ArrayList<Reservation> nyeReservationer = new ArrayList<Reservation>();
		for (int i = 0; i < antal; i++){
			int id = ReservationsFunc.opretReservation(nyReservation);
			if (id != 0){
				nyReservation.setId(id);
				nyeReservationer.add(nyReservation);
				// Vi gemmer kun antal personer og hunde i den første reservation i en serie
				nyReservation.setAntal_voksne(0);
				nyReservation.setAntal_børn(0);
				nyReservation.setAntal_hunde(0);
			} else {
				ui.besked("Der skete en fejl under oprettelse af reservation i databasen");
			}
		}
		
		ui.besked("Der er oprettet følgende reservationer");
		for (Reservation res: nyeReservationer){
			ui.besked(ReservationsFunc.getReservation(res.getId()).prettyPrint());
		}

		return nyeReservationer;
	}
	
	public void visReservationer(){
		// TODO
	}
	
	public ArrayList<Reservation> vælgReservationForKunde(int kundeId){
		return vælgReservationForKunde(kundeId, -1);
	}
	
	
	public ArrayList<Reservation> vælgReservationForKunde(int kundeId, int status){
		ArrayList<Reservation> kundeReservationer = ReservationsFunc.getReservationerForKunde(kundeId,status);
		
		int size = kundeReservationer.size();
		if (size == 0){
			String besked = "Der var ingen reservationer til valgte kunde";
			if (status >= 0)
				besked += " med status:" + Reservation.STATUSER[status];
			ui.besked(besked);
			return null;
		}
		
		String[] menuValg = new String[size+2];
		menuValg[0] = "Afbryd";
		menuValg[size+1] = "Gå viddere";
		
		boolean[] valgt = new boolean[size];
				
		while(true) {
			int i = 1;
			for (Reservation res : kundeReservationer){
				menuValg[i] = res.prettyPrint();
				if (valgt[i-1]){
					menuValg[i] += " *Valgt*";
				}
				i++;
			}
			int input = ui.visMenu("Vælg reservationer", menuValg);
			if (input == 0)
				return null;
			
			if (input != size +1){
				valgt[input-1] = !valgt[input-1]; 
			} else
				break;
		}
		
		ArrayList<Reservation> returnList = new ArrayList<Reservation>();
		for (int i = 0; i < size; i++){
			if (valgt[i])
				returnList.add(kundeReservationer.get(i));
		}
		return returnList;
	}
}
