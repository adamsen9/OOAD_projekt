package controller;

import boundary.IUI;

public class ConsoleMainMenuController extends GeneralController {
	ConsoleKundeController kundeC = new ConsoleKundeController(ui);
	ConsoleAdminController adminC = new ConsoleAdminController(ui);
	ConsoleReservationsController reservC = new ConsoleReservationsController(ui, kundeC, adminC);
	
	public ConsoleMainMenuController(IUI ui){
		super(ui);
	}
	
	public void run() {
		String[] menuItems = {"Luk Programmet","Reservationer","Kunder","Tjek ind","Tjek ud","Administrator menu"};
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
				//Kunder
				kundeC.run();
				break;
			case 3:
				// Tjek ind
				reservC.tjekInd();
				break;
			case 4:
				// Tjek ud
				reservC.tjekUd();
				break;
			case 5:
				//Lejradministration
				adminC.run();
				break;
			}
		}
	}
}
