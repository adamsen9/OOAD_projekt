package Controller;

import Boundary.IUI;
import Entity.AdministrationsDAL;
import Entity.Dataklasser.HyttePlads;

public class ConsoleAdminController extends MotherController {

	boolean færdig;
	String[] hyttePladsAttributter = {
			"type",
			"måler id",
			"Status kode",
			"Status beskrivelse"
	};
	
	public ConsoleAdminController(IUI ui) {
		super(ui);
	}
	

	// Administrationsmenu
	public void run(){
		String menuTittel = "Administrationsmenu";
		String[] menuOptions = {
				"Tilbage",
				"Ret Priser",
				"Administrer pladser og hytter"
		};

		Runnable[] menuFunktioner = {
			() -> { færdig = true;},
			() -> { prisMenu();},
			() -> { pladsMenu();} 
		};

		færdig = false;
		while (!færdig){
			menuFunktioner[ui.visMenu(menuTittel, menuOptions)].run();
		}
	}
	
	// Prismenu
	private void prisMenu(){
		String menuTittel = "Ret priser";
		String[] menuOptions = {
				"Tilbage",
				"ting",
				"andre ting"
		};

		Runnable[] menuFunktioner = {
			() -> { færdig = true;},
			() -> { },
			() -> { } 
		};

		while (!færdig){
			menuFunktioner[ui.visMenu(menuTittel, menuOptions)].run();
		}
		færdig = false;
	}
	
	//Plasdsmenu
	private void pladsMenu(){
		while (true){
			String menuTittel = "Administrer pladser og hytter\n";
			
			HyttePlads[] resultat = AdministrationsDAL.getHyttePladser();
			if (resultat == null){
				menuTittel = menuTittel + "Der er ingen hytter eller pladser i systemet\n";
				resultat = new HyttePlads[0];
			}
			
			String[] menuOptions = new String[resultat.length+2];
			menuOptions[0] = "Tilbage";
			menuOptions[resultat.length+1] = "Opret ny hytte";

			for (int i = 0; i < resultat.length; i++){
				menuOptions[i+1] = resultat[i].toString();
			}

			int valg = ui.visMenu(menuTittel, menuOptions);
			if (valg == 0)
				return;
			
			if (valg == resultat.length+1){
				nyHyttePlads();
				continue;
			}

			retHyttePlads(resultat[valg-1]);
			
		}
	}
	
	// opret hytte eller plads
	private void nyHyttePlads(){
		String[] svar = ui.multiInput("Indtast oplysninger på ny hytte/plads", hyttePladsAttributter);
		
	}
	
	// Ret specifik hytte eller plads
	private void retHyttePlads(HyttePlads hp){
		String[] svar = ui.multiInput("Ret plads ", hyttePladsAttributter);
		
		if ( svar != null){
			if (svar[0] != null)
				System.out.println("*" + svar[0] + "*");
			
		}
	}
	
	

}
