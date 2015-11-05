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
		String[] menuItems = {"Gå op","Opret kunde","Vis alle kunder","Vis en kunde"};
		
		while(true) {
			int valg = ui.visMenu("Costa Kalundborg", menuItems);
			
			switch (valg) {
			case 0:
				//Gå op
				return;
			case 1:
				//Opret kunde
				opretKunde();
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
	
	public static void opretKunde() {
		ui.besked("Oprettelse af ny kunde");	
		ui.besked("Indtast -1 på et givent tidspunkt for at afbryde");	
		
		//Navn
		String navn = ui.input("Indtast navn:");
		if(navn.equals("-1")) {
			System.out.println("Afbryder");			
			return;
		}
		//Tlf
		String tlf = ui.input("Indtast telefonnumer:");
		if(tlf.equals("-1")) {
			System.out.println("Afbryder");	
			return;
		}
		
		Kunde kunde = new Kunde(navn, tlf);
		kundeDAL.pushNew(kunde);
	}
	
	public static void alleKunder() {
		ArrayList<Kunde> kundeList = new ArrayList<Kunde>();
		kundeList = kundeDAL.pullAll();
		ui.besked("Liste over alle kunder:");
		for(Kunde kunde : kundeList) {
			ui.besked("ID: " + kunde.getId() + " Navn: " + kunde.getNavn() + " Tlf: " + kunde.getTlf());
		}
	}
}