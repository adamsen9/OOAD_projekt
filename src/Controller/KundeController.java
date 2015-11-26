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
		String[] menuItems = { "Gå op", "Opret kunde", "Vis en kunde", "Vis alle kunder" };

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

	public void opretKunde() {
		ui.besked("Oprettelse af ny kunde");
		ui.besked("Indtast -1 på et givent tidspunkt for at afbryde");

		// Navn
		String navn = ui.input("Indtast navn:");
		if (navn.equals("-1")) {
			ui.besked("Afbryder");
			return;
		}
		// Tlf
		String tlf = ui.input("Indtast telefonnumer:");
		if (tlf.equals("-1")) {
			ui.besked("Afbryder");
			return;
		}

		Kunde kunde = new Kunde(navn, tlf);
		if (KundeDAL.pushNew(kunde) != 0) {
			ui.besked("Kunde oprettet");
		}
	}

	public void alleKunder() {
		ArrayList<Kunde> kundeList = new ArrayList<Kunde>();
		kundeList = KundeDAL.pullAll();
		ui.besked("Liste over alle kunder:");
		for (Kunde kunde : kundeList) {
			ui.besked("ID: " + kunde.getId() + " Navn: " + kunde.getNavn() + " Tlf: " + kunde.getTlf());
		}
	}
	
	public void getKunde() {
		ArrayList<Kunde> kundeList = new ArrayList<Kunde>();
		
		String[] menuItems = {"Annuller","Navn","Telefonnummner","ID"};
		
		int valg = ui.visMenu("Vælg kunde ud fra: ", menuItems);

		switch (valg) {
		case 0:
			// Annuller
			return;
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
			ui.besked("ID: " + kunde.getId() + " Navn: " + kunde.getNavn() + " Tlf: " + kunde.getTlf());
		}
		
	}
}