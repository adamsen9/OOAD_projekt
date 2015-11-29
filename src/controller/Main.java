package controller;

import boundary.*;

public class Main {

	public static void main(String[] args) {
		
		ConsoleUI console = new ConsoleUI();
		ConsoleMainMenuController MenuController = new ConsoleMainMenuController(console);
		
		MenuController.run();
		
		
	}
}
