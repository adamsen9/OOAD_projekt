package controller;

import java.util.ArrayList;
import java.util.HashSet;

import boundary.IUI;
import entity.HyttePladsDAL;
import entity.PrisDAL;
import entity.SæsonDAL;
import entity.dataklasser.Hytte;
import entity.dataklasser.HyttePlads;
import entity.dataklasser.IListEntity;
import function.AdministrationsFunc;

public class ConsoleAdminController extends GeneralController implements AdminController{
	
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
	@Override
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
	
	// Vælg plads eller hytte
	@Override
	public HyttePlads vælgHyttePlads(){
		HyttePlads retur;
		int type = ui.visMenu("Vælg type", HyttePlads.TYPER);
		if (type == HyttePlads.HYTTE){
			ArrayList<Hytte> hytter = AdministrationsFunc.getAlleHytter();
			HashSet<String> kategorier = new HashSet<String>();
			for (Hytte h : hytter){
				kategorier.add(h.getHytteType());
			}
			String tittel = "Vælg hytte type";
			
			String[] menuOptions = new String[kategorier.size()+1];
			int i = 0;
			for (String kategori: kategorier){
				menuOptions[i] = kategori;
				i++;
			}
			
			Hytte returHytte = new Hytte();
			returHytte.setHytteType( menuOptions[ui.visMenu(tittel, menuOptions)]);
			if(ui.bekræft("Vil du vælge en specifik hytte ?")){
				ArrayList<Hytte> hytteMulighedder = new ArrayList<Hytte>();
				ArrayList<String> valgmulighedder = new ArrayList<String>();
				for (Hytte h : hytter){
					if (h.getHytteType().equals(returHytte.getHytteType())){
						valgmulighedder.add(h.prettyPrint());
						hytteMulighedder.add(h);
					}
				}
				int input = ui.visMenu("Hytter af typen " + returHytte.getHytteType(), valgmulighedder.toArray(new String[0]));
				returHytte = AdministrationsFunc.getHytte(hytteMulighedder.get(input).getId());
			}
			retur = returHytte;
		} else {
			retur = new HyttePlads();
			if(ui.bekræft("Vil du vælge en specifik plads ?")){
				ArrayList<HyttePlads> pladser = AdministrationsFunc.getHyttePladsAfType(type);
				String[] menuText = new String[pladser.size()];
				int i = 0;
				for (HyttePlads plads : pladser){
					menuText[i] = plads.prettyPrint();
					i++;
				}
				
				int input = ui.visMenu("Pladser af typen " + HyttePlads.TYPER[type], menuText);
				retur = AdministrationsFunc.getHyttePlads(pladser.get(input).getId());
			}
		}

		retur.setType(type);
		return retur;
	}
	
	//Vælg funktion
	private void funktionsMenu(int type){
		String menuTittel = "";
		String[] menuOptions = {};

		if (type == hyttePlads){
			menuTittel = "Administrér pladser og hytter.";
			menuOptions = new String[]{
				"Tilbage",
				"Vis alle hytter og pladser",
				"Opret ny hytte eller plads",
				"Ret eksisterende hytte eller plads",
				"Slet en hytte eller plads"
			};
		} else if (type == pris){
			menuTittel = "Administrér priser.";
			menuOptions = new String[]{
				"Tilbage",
				"Vis alle priser",
				"Opret ny pris",
				"Ret eksisterende pris",
				"Slet en pris"
			};
		} else if (type == sæson){
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
			() -> { visAlle(type); },
			() -> { ny(type); }, 
			() -> { ret(type); },
			() -> { slet(type);} 
		};

		while (!færdig){
			menuFunktioner[ui.visMenu(menuTittel, menuOptions)].run();
		}
		færdig = false;
	}
	
	//Vis 
	private void visAlle(int type){
		ArrayList<IListEntity> resultat = null;
		if (type == hyttePlads){
			ui.besked("Hytter og pladser \n");
			resultat = new ArrayList<IListEntity>(HyttePladsDAL.getHyttePladser());
		} else if (type == pris){
			ui.besked("Priser \n");
			resultat = new ArrayList<IListEntity>(PrisDAL.getPriser());
		} else if (type == sæson){
			ui.besked("sæsoner \n");
			resultat = new ArrayList<IListEntity>(SæsonDAL.getSæsoner());
		}
		
		if (resultat.isEmpty()){
			if (type == hyttePlads){
				ui.besked("Der er ingen hytter eller pladser i systemet\n");
			} else if (type == pris){
				ui.besked("Der er ingen priser i systemet\n");
			} else if (type == sæson){
				ui.besked("Der er ingen sæsoner i systemet\n");
			}
			return;
		}
		
		for (IListEntity hp : resultat){
			ui.besked(hp.prettyPrint());
		}
	}
	
	// Opret ny
	private void ny(int type){
		String sType = "";
		String[] sNavne = {};
		if (type == hyttePlads){
			sType = "hytte eller plads";
			sNavne = hyttePladsAttributter;
		} else if (type == pris){
			sType = "pris";
			sNavne = prisAttributter;
		} else if (type == sæson){
			sType = "sæson";
			sNavne = sæsonAttributter;
		}
		
		String[] svar = ui.multiInput("Indtast oplysninger på ny " + sType, sNavne);
		
		if (type == hyttePlads){
			AdministrationsFunc.opretHyttePlads(svar);
		} else if (type == pris){
			AdministrationsFunc.opretPris(svar);
		} else if (type == sæson){
			AdministrationsFunc.opretSæson(svar);
		}
	}
	
	// Ret specifik
	private void ret(int type){
		String sType = "";
		String[] sNavne = {};
		if (type == hyttePlads){
			sType = "hytte eller plads";
			sNavne = hyttePladsAttributter;
		} else if (type == pris){
			sType = "pris";
			sNavne = prisAttributter;
		} else if (type == sæson){
			sType = "sæson";
			sNavne = sæsonAttributter;
		}

		String sId = ui.input("Indtast id på " + sType + ", der skal rettes.");
		
		int id;
		try{
			id = Integer.parseInt(sId);
		} catch (NumberFormatException e){
			ui.besked("Det indtastede id kan ikke genkendes som et tal\n");
			return;
		}
		
		IListEntity gammel = null;
		if (type == hyttePlads){
			gammel = HyttePladsDAL.getHyttePlads(id);
		} else if (type == pris){
			gammel = PrisDAL.getPris(id);
		} else if (type == sæson){
			gammel = SæsonDAL.getSæson(id);
		}

		if (gammel == null){
			ui.besked("Der findes ingen " + sType + " i systemet med det indtastede ID\n");
			return;
		}
		
		String[] svar = ui.multiInput("Indtast nye oplysninger for " + sType, sNavne);
		if (type == hyttePlads){
			AdministrationsFunc.retHyttePlads(gammel.getId(), svar);
		} else if (type == pris){
			AdministrationsFunc.retPris(gammel.getId(), svar);
		} else if (type == sæson){
			AdministrationsFunc.retSæson(gammel.getId(), svar);
		}
	}
	
	// Slet
	private void slet(int type) {
		String sType = "";
		if (type == hyttePlads){
			sType = "hytte eller plads";
		} else if (type == pris){
			sType = "pris";
		} else if (type == sæson){
			sType = "sæson";
		}

		String sId = ui.input("Indtast id på "+ sType);
		
		int id;
		try{
			id = Integer.parseInt(sId);
		} catch (NumberFormatException e){
			ui.besked("Det indtastede id kan ikke genkendes som et tal\n");
			return;
		}
		
		IListEntity gammel = null;
		if (type == hyttePlads){
			gammel = HyttePladsDAL.getHyttePlads(id);
		} else if (type == pris){
			gammel = PrisDAL.getPris(id);
		} else if (type == sæson){
			gammel = SæsonDAL.getSæson(id);
		}

		if (gammel == null){
			ui.besked("Der findes ingen " + sType + " i systemet med det indtastede ID\n");
			return;
		}
		
		if(ui.bekræft("Er du sikker på at du vill slette dette ?\n" + gammel.prettyPrint())){
			if (type == hyttePlads){
				HyttePladsDAL.deleteHyttePlads(gammel.getId());
			} else if (type == pris){
				gammel = PrisDAL.getPris(id);
				PrisDAL.deletePris(gammel.getId());
			} else if (type == sæson){
				gammel = SæsonDAL.getSæson(id);
				SæsonDAL.deleteSæson(gammel.getId());
			}
		}
	}
	
}
