package Controller;

import Boundary.IUI;

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
	protected void prisMenu(){
		String menuTitle = "Ret Priser";
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
	protected void pladsMenu(){
		
	}

}
