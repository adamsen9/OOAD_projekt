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
				"Administrér pladser og hytter",
				"Administrér Priser",
				"Administrér Sessoner"
		};

		Runnable[] menuFunktioner = {
			() -> { færdig = true;},
			() -> { hyttePladsMenu();},
			() -> { prisMenu();},
			() -> { sessonMenu();}
		};

		færdig = false;
		while (!færdig){
			menuFunktioner[ui.visMenu(menuTittel, menuOptions)].run();
		}
	}
	
	private void sessonMenu() {
		// TODO Auto-generated method stub
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
	
	// Administrér pladser og hytter
	private void hyttePladsMenu() {
		String menuTittel = "Administrér pladser og hytter";
		String[] menuOptions = {
				"Tilbage",
				"Vis alle hytter og pladser",
				"Opret ny hytte eller plads",
				"Ret eksisterende hytte eller plads",
				"Slet en hytte eller plads"
		};

		Runnable[] menuFunktioner = {
			() -> { færdig = true; },
			() -> { visHytterPladser(); },
			() -> { nyHyttePlads(); }, 
			() -> { retHyttePlads(); },
			() -> { sletHyttePlads();} 
		};

		while (!færdig){
			menuFunktioner[ui.visMenu(menuTittel, menuOptions)].run();
		}
		færdig = false;
		
	}
	
	//Vis hytter og pladser
	private void visHytterPladser(){
		ui.besked("Hytter og pladser \n");
		
		HyttePlads[] resultat = AdministrationsDAL.getHyttePladser();
		if (resultat == null){
			ui.besked("Der er ingen hytter eller pladser i systemet\n");
			return;
		}
		
		for (HyttePlads hp : resultat){
			ui.besked(hp.toString());
		}
	}
	
	// opret hytte eller plads
	private void nyHyttePlads(){
		String[] svar = ui.multiInput("Indtast oplysninger på ny hytte/plads", hyttePladsAttributter);
		HyttePlads nyHyttePlads = hyttePladsFraArray(svar);
		AdministrationsDAL.createHyttePlads(nyHyttePlads);
	}
	
	// Ret specifik hytte eller plads
	private void retHyttePlads(){
		String sId = ui.input("Indtast id på hytte eller plads");
		
		int id;
		try{
			id = Integer.parseInt(sId);
		} catch (NumberFormatException e){
			ui.besked("Det indtastede id kan ikke genkendes som et tal\n");
			return;
		}
		
		HyttePlads gammelHP = AdministrationsDAL.getHyttePlads(id);
		if (gammelHP == null){
			ui.besked("Der findes ingen hytter eller pladser med det indtastede ID\n");
			return;
		}
		
		String[] svar = ui.multiInput("Ret plads ", hyttePladsAttributter);
		HyttePlads nyHP = hyttePladsFraArray(svar);
		nyHP.setId(gammelHP.getId());
		AdministrationsDAL.updateHyttePlads(nyHP);
		
	}
	
	private void sletHyttePlads() {
		String sId = ui.input("Indtast id på hytte eller plads");
		
		int id;
		try{
			id = Integer.parseInt(sId);
		} catch (NumberFormatException e){
			ui.besked("Det indtastede id kan ikke genkendes som et tal\n");
			return;
		}
		
		HyttePlads gammelHP = AdministrationsDAL.getHyttePlads(id);
		if (gammelHP == null){
			ui.besked("Der findes ingen hytter eller pladser med det indtastede ID\n");
			return;
		}
		
		if(ui.bekræft("Er du sikker på at du vill slette dette ?\n" + gammelHP.toString())){
			AdministrationsDAL.deleteHyttePlads(gammelHP.getId());
		}
	}
	
	private HyttePlads hyttePladsFraArray(String[] attributes){
		//TODO
		return null;
	}
}
