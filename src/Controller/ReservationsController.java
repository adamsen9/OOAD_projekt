package Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import Boundary.IUI;

public class ReservationsController extends MotherController{
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public ReservationsController(IUI ui) {
		super(ui);
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		String[] menuItems = {"Gå op","Lav reservation","Vis alle reservationer"};
		
		
		while(true) {
			int valg = ui.visMenu("Costa Kalundborg", menuItems);
			
			switch (valg) {
			case 0:
				//Gå op
				return;
			case 1:
				//Lav reservation
				createReservation();
				break;
			case 2:
				//Vis alle reservationer
				showReservations();
				break;
			default:
				
				break;
			}
		}
		
	}
	
	public void Checkin() throws NumberFormatException, IOException{
		//do stuff
		System.out.println("Indtast den dag du gerne vil starte dit ophold");
		Day();
		System.out.println("Indtast den måned du gerne vil starte dit ophold");
		Month();
		System.out.println("Indtast det år du gerne vil starte dit ophold");
		Year();
	}
	
	public void Checkout(){
		//do stuff
		
	}
	
	public void createReservation(){
		//do stuff
		
	}
	
	public void showReservations(){
		//do stuff
		
	}
	
public int Month(){
	int month;
	while(true) {
		try {
			month = Integer.parseInt(br.readLine());
			if(month < 1 || month >= 13) {
				System.out.println("Indtast tal mellem 0 og 12");
			}else{
				return month;
			}
		} catch(Exception e) {
			System.out.println("Indtast et heltal");
		}
	}
}
	
	
	public int Day(){
		int day;
		while(true) {
			try {
				day = Integer.parseInt(br.readLine());
				if(day < 1 || day > 31) {
					System.out.println("Indtast en dag mellem 1 og 31");
				}else{
					return day;
				}
			} catch(Exception e) {
				System.out.println("Indtast et heltal");
			}
		}
	}
	
	public int Year(){
		int year;
		while(true) {
			try {
				year = Integer.parseInt(br.readLine());
				if(year < 2015) {
					System.out.println("Du kan ikke rejse tilbage i tiden");
				}else{
					return year;
				}
			} catch(Exception e) {
				System.out.println("Indtast et heltal");
			}
		}
	}



}
