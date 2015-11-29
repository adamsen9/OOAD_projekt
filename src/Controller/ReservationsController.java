package Controller;

import java.util.ArrayList;

import Boundary.IUI;
import Entity.Dataklasser.Kunde;
import Entity.Dataklasser.Reservation;
import Function.ReservationsFunc;


public class ReservationsController extends MotherController{

	KundeController kc;
	Kunde kunde;
	Reservation reservation;
	
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
				opretReservation();
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
		String menuTittel = "Check ind - vælg kunde";
		String[] menuOptions = {
				"Tilbage", 
				"Ny kunde", 
				"Eksisterende kunde"
		};

		Runnable[] menuFunktioner = {
			() -> { kunde = null; },
			() -> { kunde = kc.opretKunde(); },
			() -> { kunde = kc.getKunde(); }
		};

		menuFunktioner[ui.visMenu(menuTittel, menuOptions)].run();
		if (kunde == null)
			return;
		
		// find/opret reservation
		menuTittel = "Check ind - vælg reservation";
		menuOptions = new String[]{
				"Annullér", 
				"Ny reservation", 
				"Eksisterende reservation"
		};

		menuFunktioner = new Runnable[]{
			() -> { reservation = null; },
			() -> { reservation = opretReservation(); },
			() -> { reservation = vælgReservationForKunde(kunde.getId()); }
		};

		menuFunktioner[ui.visMenu(menuTittel, menuOptions)].run();
		if (reservation == null)
			return;
		
		ReservationsFunc.checkInd(reservation.getId());
		
	}
	
	public void Checkout(){
		//TODO stuff
		
	}
	

	public Reservation opretReservation(){
		//TODO stuff
		return null;
	}
	
	public void visReservationer(){
		// TODO stuff
	}
	
	public Reservation vælgReservationForKunde(int kundeId){

		
		return null;
	}
	
	public ArrayList<Reservation> reservationerForKunde(int kundeId){
		// TODO implement
		ArrayList<Reservation> resList = new ArrayList<Reservation>();
		return resList;
	}
}
