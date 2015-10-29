package Controller;

import Boundary.IUI;

public class MainMenuController extends MotherController {
	
	public MainMenuController(IUI ui){
		super(ui);
	}
	
	public void run() {
		String[] menuItems = {"Luk Programmet","Vis reservation","Kunder","Tjek ind","Tjek ud"};
		while(true) {
			int valg = ui.visMenu("Costa Kalundborg", menuItems);
			
			
			switch (valg) {
			case 0:
				//Sluk programmet
				return;
			case 1:
				//Reservationer
				break;
			case 2:
				//Kunder
				break;
			case 3:
				//Regninger
				break;
			case 4:
				//Lejradministration
				break;
			case 5:
				//
				break;
			}
		}
	}
}
