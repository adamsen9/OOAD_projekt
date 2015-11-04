package Controller;

import Boundary.IUI;

public class ReservationsController extends MotherController{

	public ReservationsController(IUI ui) {
		super(ui);
		
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
				
				break;
			case 2:
				//Vis alle reservationer
				break;
			default:
				
				break;
			}
		}
		
	}
	
	public void Checkin(){
		//do stuff
	}
	
	public void Checkout(){
		//do stuff
	}

}
