package Controller;

import Boundary.IUI;

public abstract class GeneralController {
	IUI ui;
	
	public GeneralController(IUI ui){
		this.ui = ui;
	}
	
	public abstract void run();
	
}
