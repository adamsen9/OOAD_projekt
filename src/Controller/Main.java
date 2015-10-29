package Controller;

import Boundary.*;
import Entity.*;

public class Main {

	public static void main(String[] args) {
		
		ConsoleUI console = new ConsoleUI();
		MainMenuController MenuController = new MainMenuController(console);
		
		MenuController.run();
		
		
	}
}
