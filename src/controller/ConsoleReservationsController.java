package controller;

import java.util.ArrayList;

import boundary.IUI;
import entity.ReservationsDAL;
import entity.dataklasser.HyttePlads;
import entity.dataklasser.Kunde;
import entity.dataklasser.Reservation;
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
	
	public void Checkin() {
		// find/opret kunde
		Kunde kunde = kc.vælgKunde();
		if (kunde == null)
			return;
		
		// find/opret reservation
		String menuTittel = "Check ind - vælg reservation";
		String[] menuOptions = {
				"Annullér", 
				"Ny reservation", 
				"Eksisterende reservation"
		};

		ReservationVælger[] menuFunktioner = {
			() -> { return null; },
			() -> { return opretReservation(kunde); },
			() -> { return vælgReservationForKunde(kunde.getId()); }
		};

		ArrayList<Reservation> reservationer = 
				menuFunktioner[ui.visMenu(menuTittel, menuOptions)].vælg();
		if (reservationer == null)
			return;
		
		ReservationsFunc.checkInd(reservationer);
	}
	
	public void Checkout(){
		//TODO stuff
	}
	

	public ArrayList<Reservation> opretReservation(Kunde kunde){
		if (kunde == null)
			kunde = kc.vælgKunde();
		
		HyttePlads hyttePlads = ac.vælgHyttePlads();
		
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
			}
		}
		
		String[] sNavne = {
				"Dato for ankomst (yyyy-mm-dd)", 
				"Dato for afrejse (yyyy-mm-dd) eller skriv antal overnatninger",
				"Antal voksne (Intet valg = "+ Reservation.STANDARD_VOKSNE +")",
				"Antal børn (Intet valg = "+ Reservation.STANDARD_BØRN +")",
				"Antal hunde (Intet valg = "+ Reservation.STANDARD_HUND +")",
		};
		
		String[] svar = ui.multiInput("Indtast oplysninger på ny reservation til /n" + kunde.prettyPrint(), sNavne);

		Reservation ny = ReservationsFunc.nyFraArray(svar);
		ny.setPlads_type(hyttePlads.getType());
		ny.setPlads_id(hyttePlads.getId());
		
		while(!ReservationsFunc.checkLedige(hyttePlads, ny.getStart_dato(), ny.getSlut_dato(), antal)){
			int input = ui.visMenu("Der er ikke tilstækkelige ledige hytter/pladser af den valgte type i valgte tidsrum\n"
					+ "Vælg handling", 
					new String[]{
							"Afbryd reservation",
							"Vælg en anden ankomstsdato",
							"Vælg en anden afrejsedato",
							"Vælg en anden plads eller hytte",
							"Vælg et andet antal pladser"});
			switch (input) {
			case 0:
				return null;
			case 1:
				ny.setStart_dato(ui.input("Indtast ny dato for ankomst (yyyy-mm-dd)"));
				break;
			case 2:
				ny.setSlut_dato(ui.input("Indtast ny dato for afrejse (yyyy-mm-dd) eller skriv antal overnatninger"));
				break;
			case 3:
				hyttePlads = ac.vælgHyttePlads();
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
		}
		
		ArrayList<Reservation> nyeReservationer = new ArrayList<Reservation>(); 
		for (int i = 0; i < antal; i++){
			int id = ReservationsDAL.opretReservation(ny);
			if (id != -1){
				nyeReservationer.add(ny);
				ny.setAntal_voksne(0);
				ny.setAntal_børn(0);
				ny.setAntal_hunde(0);
			} else {
				// TODO håndtér fejl
			}
		}

		return nyeReservationer;
	}
	
	public void visReservationer(){
		// TODO stuff
	}
	
	public ArrayList<Reservation> vælgReservationForKunde(int kundeId){

		
		return null;
	}
	
	public ArrayList<Reservation> reservationerForKunde(int kundeId){
		// TODO implement
		ArrayList<Reservation> resList = new ArrayList<Reservation>();
		return resList;
	}
}
