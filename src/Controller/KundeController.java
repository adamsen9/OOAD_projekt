package Controller;

import Boundary.IUI;

public class KundeController extends MotherController {

	public KundeController(IUI ui) {
		super(ui);
	}
	
	public void run() {
		String[] menuItems = {"Gå op","Vis alle kunder","Vis alle kunder"};
		
		while(true) {
			int valg = ui.visMenu("Costa Kalundborg", menuItems);
			
			switch (valg) {
			case 0:
				//Gå op
				return;
			case 1:
				//Opret kunde
				break;
			case 2:
				//Vis alle kunder
				break;
			default:
				
				break;
			}
		}
		
	}
	
	
	

}


