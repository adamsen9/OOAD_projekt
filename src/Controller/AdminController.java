package Controller;

import Boundary.IUI;
import Entity.AdministrationsDAL;
import Entity.Dataklasser.HyttePlads;

public class AdminController extends MotherController {

	boolean done;
	
	public AdminController(IUI ui) {
		super(ui);
	}
	

	// Administrationsmenu
	public void run(){
		String menuTitle = "Administrationsmenu";
		String[] menuOptions = {
				"Tilbage",
				"Ret Priser",
				"Administrer pladser og hytter"
		};

		Runnable[] menuFunctions = {
			() -> { done = true;},
			() -> { prisMenu();},
			() -> { pladsMenu();} 
		};

		done = false;
		while (!done){
			menuFunctions[ui.visMenu(menuTitle, menuOptions)].run();
		}
	}
	
	// Prismenu
	private void prisMenu(){
		String menuTitle = "Ret priser";
		String[] menuOptions = {
				"Tilbage",
				"ting",
				"andre ting"
		};

		Runnable[] menuFunctions = {
			() -> { done = true;},
			() -> { },
			() -> { } 
		};

		while (!done){
			menuFunctions[ui.visMenu(menuTitle, menuOptions)].run();
		}
		done = false;
	}
	
	//Plasdsmenu
	private void pladsMenu(){
		String menuTitle = "Administrer pladser og hytter";
		HyttePlads[] result = AdministrationsDAL.getHyttePladser();
		
		String[] menuOptions = new String[result.length+1];
		menuOptions[0] = "Tilbage";
		for (int i = 0; i < result.length; i++){
			menuOptions[i+1] = result[i].toString();
		}

		while (true){
			int valg = ui.visMenu(menuTitle, menuOptions);
			if (valg == 0)
				return;

			
			
		}
	}
	
	private void retPlads(HyttePlads hp){
		
	}
	
	

}
