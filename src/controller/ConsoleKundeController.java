package controller;

import boundary.IUI;
import entity.KundeDAL;
import entity.dataklasser.Kunde;

import java.util.ArrayList;

public class ConsoleKundeController extends GeneralController implements KundeController{

	private interface KundeVælger{ Kunde vælg(); }
	
	public ConsoleKundeController(IUI ui) {
		super(ui);
	}

	@Override
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
				opretNyKunde();
				break;
			case 2:
				// Vis en kunde
				findKunder();
				break;
			case 3:
				// Vis alle kunder
				visAlleKunder();
			}
		}
	}
	
	@Override
	public Kunde vælgKunde(){
		String menuTittel = "Vælg kunde";
		String[] menuOptions = {
				"Tilbage", 
				"Ny kunde", 
				"Eksisterende kunde"
		};

		KundeVælger[] menuFunktioner = {
			() -> { return null; },
			() -> { return opretNyKunde(); },
			() -> { return findEnKunde(); }
		};
		
		Kunde valg;
		do{
			int input = ui.visMenu(menuTittel, menuOptions);
			if (input == 0)
				return null;
		
			valg = menuFunktioner[input].vælg();
		} while(valg == null);
			
		return valg;
	}

	private Kunde opretNyKunde() {
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
		
		kunde.setId(KundeDAL.pushNew(kunde));
		
		if(kunde.getId() == 0)
			return null;
		
		ui.besked("Kunde oprettet \n" + kunde.prettyPrint());
		return kunde;
	}

	private void visAlleKunder() {
		ArrayList<Kunde> kundeList = new ArrayList<Kunde>();
		kundeList = KundeDAL.pullAll();
		ui.besked("Liste over alle kunder:");
		for (Kunde kunde : kundeList) {
			ui.besked(kunde.prettyPrint());
		}
	}
	
	private ArrayList<Kunde> findKunder() {
		ArrayList<Kunde> kundeList = new ArrayList<Kunde>();
		
		String[] menuItems = {"Annuller","Navn","Telefonnummner","ID"};
		int valg = ui.visMenu("Vælg kunde ud fra: ", menuItems);
		if (valg == 0)
			return null;
		
		kundeList = KundeDAL.pull(ui.input("Indtast " + menuItems[valg]),valg);
		
		for (Kunde kunde : kundeList) {
			ui.besked(kunde.prettyPrint());
		}
	
		if (kundeList.isEmpty()){
			ui.besked("Der kunne ikke findes nogen kunder der passer til søgningen");
			return null;
		}
		
		return kundeList;
	}
	
	private Kunde findEnKunde(){
		while(true) {
			ArrayList<Kunde> liste = findKunder();
			if(liste == null || liste.isEmpty())
				return null;
			
			if(liste.size() > 1) {
				int choice = ui.visMenu("Der var mere end en kunde der matchede søgningen.\nVil du", 
						new String[]{"Lave ny søgning","Fortsætte med\n\t" + liste.get(0).prettyPrint()});
				if (choice == 0)
					continue;
			}
			return liste.get(0);
		}
	}
}