package Controller;

import Boundary.IUI;
import Entity.KundeDAL;
import Entity.Dataklasser.Kunde;

import java.util.ArrayList;

public class KundeController extends MotherController {
	static KundeDAL kundeDAL;
	
	public KundeController(IUI ui) {
		super(ui);
		kundeDAL = new KundeDAL();
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
				alleKunder();
				
				break;
			default:
				break;
			}
		}
	}
	
	public static void alleKunder() {
		ArrayList<Kunde> kundeList = new ArrayList<Kunde>();
		kundeList = kundeDAL.pullAll();
		
		for(Kunde kunde : kundeList) {
			ui.besked("ID: " + kunde.getId() + " Navn: " + kunde.getNavn() + " Tlf: " + kunde.getTlf());
		}
	}
}


