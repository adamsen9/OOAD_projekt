package Controller;

import java.util.ArrayList;

import Boundary.IUI;
import Entity.HyttePladsDAL;
import Entity.PrisDAL;
import Entity.SæsonDAL;
import Entity.Dataklasser.IListEntity;
import Function.AdministrationsFunc;

public class ConsoleAdminController extends MotherController {
	
	private static final int hyttePlads = 0;
	private static final int pris = 1;
	private static final int sæson = 2;
	
	boolean færdig;
	String[] hyttePladsAttributter = {
			"type",
			"måler id",
			"Status kode",
			"Status beskrivelse"
	};

	String[] prisAttributter = {
			"enhed",
			"sæson",
			"værdi",
	};
	
	String[] sæsonAttributter = {
			"start",
			"slut",
			"navn",
	};
	
	public ConsoleAdminController(IUI ui) {
		super(ui);
	}
	

	// Administrationsmenu
	// Vælg type
	public void run(){
		String menuTittel = "Administrationsmenu";
		String[] menuOptions = {
				"Tilbage",
				"Administrér pladser og hytter",
				"Administrér Priser",
				"Administrér sæsoner"
		};

		Runnable[] menuFunktioner = {
			() -> { færdig = true;},
			() -> { funktionsMenu(hyttePlads);},
			() -> { funktionsMenu(pris);},
			() -> { funktionsMenu(sæson);}
		};

		færdig = false;
		while (!færdig){
			menuFunktioner[ui.visMenu(menuTittel, menuOptions)].run();
		}
	}
	
	//Vælg funktion
	private void funktionsMenu(int typpe){
		String menuTittel = "";
		String[] menuOptions = {};

		if (typpe == hyttePlads){
			menuTittel = "Administrér pladser og hytter.";
			menuOptions = new String[]{
				"Tilbage",
				"Vis alle hytter og pladser",
				"Opret ny hytte eller plads",
				"Ret eksisterende hytte eller plads",
				"Slet en hytte eller plads"
			};
		} else if (typpe == pris){
			menuTittel = "Administrér priser.";
			menuOptions = new String[]{
				"Tilbage",
				"Vis alle priser",
				"Opret ny pris",
				"Ret eksisterende pris",
				"Slet en pris"
			};
		} else if (typpe == sæson){
			menuTittel = "Administrér sæsoner.";
			menuOptions = new String[]{
				"Tilbage",
				"Vis alle sæsoner",
				"Opret ny sæson",
				"Ret eksisterende sæson",
				"Slet en sæson"
			};
		}

		Runnable[] menuFunktioner = {
			() -> { færdig = true; },
			() -> { visAlle(typpe); },
			() -> { ny(typpe); }, 
			() -> { ret(typpe); },
			() -> { slet(typpe);} 
		};

		while (!færdig){
			menuFunktioner[ui.visMenu(menuTittel, menuOptions)].run();
		}
		færdig = false;
	}
	
	//Vis 
	private void visAlle(int typpe){
		ArrayList<IListEntity> resultat = null;
		if (typpe == hyttePlads){
			ui.besked("Hytter og pladser \n");
			resultat = new ArrayList<IListEntity>(HyttePladsDAL.getHyttePladser());
		} else if (typpe == pris){
			ui.besked("Priser \n");
			resultat = new ArrayList<IListEntity>(PrisDAL.getPriser());
		} else if (typpe == sæson){
			ui.besked("sæsoner \n");
			resultat = new ArrayList<IListEntity>(SæsonDAL.getSæsoner());
		}
		
		if (resultat.isEmpty()){
			if (typpe == hyttePlads){
				ui.besked("Der er ingen hytter eller pladser i systemet\n");
			} else if (typpe == pris){
				ui.besked("Der er ingen priser i systemet\n");
			} else if (typpe == sæson){
				ui.besked("Der er ingen sæsoner i systemet\n");
			}
			return;
		}
		
		for (IListEntity hp : resultat){
			ui.besked(hp.prettyPrint());
		}
	}
	
	// Opret ny
	private void ny(int typpe){
		String sTyppe = "";
		String[] sNavne = {};
		if (typpe == hyttePlads){
			sTyppe = "hytte eller plads";
			sNavne = hyttePladsAttributter;
		} else if (typpe == pris){
			sTyppe = "pris";
			sNavne = prisAttributter;
		} else if (typpe == sæson){
			sTyppe = "sæson";
			sNavne = sæsonAttributter;
		}
		
		String[] svar = ui.multiInput("Indtast oplysninger på ny " + sTyppe, sNavne);
		
		if (typpe == hyttePlads){
			AdministrationsFunc.opretHyttePlads(svar);
		} else if (typpe == pris){
			AdministrationsFunc.opretPris(svar);
		} else if (typpe == sæson){
			AdministrationsFunc.opretSæson(svar);
		}
	}
	
	// Ret specifik
	private void ret(int typpe){
		String sTyppe = "";
		String[] sNavne = {};
		if (typpe == hyttePlads){
			sTyppe = "hytte eller plads";
			sNavne = hyttePladsAttributter;
		} else if (typpe == pris){
			sTyppe = "pris";
			sNavne = prisAttributter;
		} else if (typpe == sæson){
			sTyppe = "sæson";
			sNavne = sæsonAttributter;
		}

		String sId = ui.input("Indtast id på " + sTyppe + ", der skal rettes.");
		
		int id;
		try{
			id = Integer.parseInt(sId);
		} catch (NumberFormatException e){
			ui.besked("Det indtastede id kan ikke genkendes som et tal\n");
			return;
		}
		
		IListEntity gammel = null;
		if (typpe == hyttePlads){
			gammel = HyttePladsDAL.getHyttePlads(id);
		} else if (typpe == pris){
			gammel = PrisDAL.getPris(id);
		} else if (typpe == sæson){
			gammel = SæsonDAL.getSæson(id);
		}

		if (gammel == null){
			ui.besked("Der findes ingen " + sTyppe + " i systemet med det indtastede ID\n");
			return;
		}
		
		String[] svar = ui.multiInput("Indtast nye oplysninger for " + sTyppe, sNavne);
		if (typpe == hyttePlads){
			AdministrationsFunc.retHyttePlads(gammel.getId(), svar);
		} else if (typpe == pris){
			AdministrationsFunc.retPris(gammel.getId(), svar);
		} else if (typpe == sæson){
			AdministrationsFunc.retSæson(gammel.getId(), svar);
		}
	}
	
	// Slet
	private void slet(int typpe) {
		String sTyppe = "";
		if (typpe == hyttePlads){
			sTyppe = "hytte eller plads";
		} else if (typpe == pris){
			sTyppe = "pris";
		} else if (typpe == sæson){
			sTyppe = "sæson";
		}

		String sId = ui.input("Indtast id på "+ sTyppe);
		
		int id;
		try{
			id = Integer.parseInt(sId);
		} catch (NumberFormatException e){
			ui.besked("Det indtastede id kan ikke genkendes som et tal\n");
			return;
		}
		
		IListEntity gammel = null;
		if (typpe == hyttePlads){
			gammel = HyttePladsDAL.getHyttePlads(id);
		} else if (typpe == pris){
			gammel = PrisDAL.getPris(id);
		} else if (typpe == sæson){
			gammel = SæsonDAL.getSæson(id);
		}

		if (gammel == null){
			ui.besked("Der findes ingen " + sTyppe + " i systemet med det indtastede ID\n");
			return;
		}
		
		if(ui.bekræft("Er du sikker på at du vill slette dette ?\n" + gammel.prettyPrint())){
			if (typpe == hyttePlads){
				HyttePladsDAL.deleteHyttePlads(gammel.getId());
			} else if (typpe == pris){
				gammel = PrisDAL.getPris(id);
				PrisDAL.deletePris(gammel.getId());
			} else if (typpe == sæson){
				gammel = SæsonDAL.getSæson(id);
				SæsonDAL.deleteSæson(gammel.getId());
			}
		}
	}
	
}
