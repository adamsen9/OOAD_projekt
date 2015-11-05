package Controller;

import java.io.IOException;

import Boundary.IUI;

public class MainMenuController extends MotherController {
	KundeController kundeC = new KundeController(ui);
	ReservationsController reservC = new ReservationsController(ui);
	ConsoleAdminController adminC = new ConsoleAdminController(ui);
	
	public MainMenuController(IUI ui){
		super(ui);
	}
	
	public void run() {
		String[] menuItems = {"Luk Programmet","Vis reservation","Kunder","Tjek ind","Tjek ud","Administrator menu"};
		while(true) {
			int valg = ui.visMenu("Costa Kalundborg", menuItems);
			
			
			switch (valg) {
			case 0:
				//Sluk programmet
				return;
			case 1:
				//Reservationer
				reservC.run();
				break;
			case 2:
				kundeC.run();
				break;
			case 3:
				// Tjek ind
				try {
					reservC.Checkin();
				} catch (Exception e) {
					System.out.println(e);
				}
				
				break;
			case 4:
				// Tjek ud
				reservC.Checkout();
				break;
			case 5:
				//Lejradministration
				adminC.run();
				break;
			}
		}
	}
}
