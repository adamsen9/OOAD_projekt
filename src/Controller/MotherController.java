package Controller;

import Boundary.IUI;

public abstract class MotherController {
	IUI ui;
	
	public MotherController(IUI ui){
		this.ui = ui;
	}
	
	public abstract void run();
	
}
