package Main;

import Entity.*;

public class Main {

	public static void main(String[] args) {
		
		DAL dataAccessLayer = new DAL();
		if(!dataAccessLayer.insertName(5, "Victor")) {
			System.out.println("Opdaterede ikke databasen");
		}

		dataAccessLayer.printNames();
		
		
	}
}
