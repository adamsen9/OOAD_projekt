package Controller;

import Boundary.IUI;
import Entity.KundeDAL;
import Entity.Dataklasser.Kunde;

import java.util.ArrayList;

public class KundeController extends MotherController {

	public KundeController(IUI ui) {
		super(ui);
	}

	public void run() {
		String[] menuItems = { "Gå op", "Opret kunde", "Find en kunde", "Vis alle kunder" };

		while (true) {
			int valg = ui.visMenu("Costa Kalundborg", menuItems);

			switch (valg) {
			case 0:
				// Gå op
				return;
			case 1:
				//Opret kunde
				opretKunde();
				break;
			case 2:
				// Vis en kunde
				getKunde();
				break;
			case 3:
				// Vis alle kunder
				alleKunder();
				
				
			default:
				break;
			}
		}
	}

	public Kunde opretKunde() {
		ui.besked("Oprettelse af ny kunde");
		ui.besked("Indtast -1 på et givent tidspunkt for at afbryde");

		// Navn
		String navn = ui.input("Indtast navn:");
		if (navn.equals("-1")) {
			ui.besked("Afbryder");
			return null;
		}
		// Tlf
		String tlf = ui.input("Indtast telefonnumer:");
		if (tlf.equals("-1")) {
			ui.besked("Afbryder");
			return null;
		}

		Kunde kunde = new Kunde(navn, tlf);
		int id = KundeDAL.pushNew(kunde);
		if (id != 0) {
			Kunde nyKunde = KundeDAL.pull(id);
			ui.besked("Kunde oprettet \n " + nyKunde.prettyPrint());
			return nyKunde;
		}
		return null;
	}

	public void alleKunder() {
		ArrayList<Kunde> kundeList = new ArrayList<Kunde>();
		kundeList = KundeDAL.pullAll();
		ui.besked("Liste over alle kunder:");
		for (Kunde kunde : kundeList) {
			ui.besked(kunde.prettyPrint());
		}
	}
	
	public Kunde getKunde() {
		ArrayList<Kunde> kundeList = new ArrayList<Kunde>();
		
		String[] menuItems = {"Annuller","Navn","Telefonnummner","ID"};
		
		int valg = ui.visMenu("Vælg kunde ud fra: ", menuItems);

		switch (valg) {
		case 0:
			// Annuller
			return null;
		case 1:
			// Navn
			kundeList = KundeDAL.pull(ui.input("Indtast navn"),valg);
			break;
		case 2:
			// Telefonnumer
			kundeList = KundeDAL.pull(ui.input("Indtast telefonnummer"),valg);
			break;
		case 3:
			// ID
			kundeList = KundeDAL.pull(ui.input("Indtast ID"),valg);
			break;
		default:
			break;
		}
		
		for (Kunde kunde : kundeList) {
			ui.besked(kunde.prettyPrint());
		}
	
		return kundeList.get(0);
	}
}