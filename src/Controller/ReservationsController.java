package Controller;

import java.util.ArrayList;

import Boundary.IUI;
import Entity.ReservationsDAL;
import Entity.Dataklasser.HyttePlads;
import Entity.Dataklasser.Kunde;
import Entity.Dataklasser.Reservation;
import Function.ReservationsFunc;


public class ReservationsController extends MotherController{

	KundeController kc;
	private interface ReservationVælger{ ArrayList<Reservation> vælg();	}
	
	public ReservationsController(IUI ui, KundeController kc) {
		super(ui);
		this.kc = kc;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
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
			default:
				
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
		
		HyttePlads hyttePlads = vælgHyttePlads();
		
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
				"Antal voksne",
				"Antal børn",
				"Antal hunde",
		};
		
		String[] svar = ui.multiInput("Indtast oplysninger på ny reservation til /n" + kunde.prettyPrint(), sNavne);

		Reservation ny = ReservationsFunc.nyFraArray(svar);
		ny.setPlads_typpe(hyttePlads.getType());
		ny.setPlads_id(hyttePlads.getId());
		
		if(!ReservationsFunc.checkLedige(ny.getPlads_typpe(), ny.getStart_dato(), ny.getSlut_dato(), antal)){
			//TODO vælg nyt eller stop ?
			return null;
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
	
	private HyttePlads vælgHyttePlads(){
		//TODO implement
		return null;
	}
}
