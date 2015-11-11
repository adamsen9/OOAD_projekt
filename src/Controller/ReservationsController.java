package Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import Boundary.IUI;
import Entity.Dataklasser.Reservation;

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
		//TODO stuff
		int start_day, start_month, start_year;
		int end_day, end_month, end_year;
		ui.besked("Indtast den dag du gerne vil starte dit ophold");
		start_day = Day();
		ui.besked("Indtast den måned du gerne vil starte dit ophold");
		start_month = Month();
		ui.besked("Indtast det år du gerne vil starte dit ophold");
		start_year = Year();
		
		ui.besked("Indtast den dag du gerne vil slutte dit ophold");
		end_day = Day();
		ui.besked("Indtast den måned du gerne vil slutte dit ophold");
		end_month = Month();
		ui.besked("Indtast det år du gerne vil slutte dit ophold");
		end_year = Year();
	}
	
	public void Checkout(){
		//TODO stuff
		
	}
	
	public void createReservation(){
		//TODO stuff
		
	}
	
	public void showReservations(){
		// stuff
		String[] menulist = {"tilbage", "Luksushytte med tagteresse", "Luksushytte", "4 personers hytte", "2 personer hytte", "2 personer hytte, lille"};
		
		while (true) {
			int valg = ui.visMenu("Costa Kalundborg", menulist);
						switch (valg) {
						case 0:
							//Sluk programmet
							return;
						case 1:
							showCabinReservations(valg);
							break;
						case 2:
							showCabinReservations(valg);
							break;
						case 3:
							showCabinReservations(valg);
							break;
						case 4:
							showCabinReservations(valg);
							break;
						case 5:
							showCabinReservations(valg);
							break;
						}
					}
		
	}

private void showCabinReservations(int valg){
	ArrayList<Reservation> ReservList = new ArrayList<Reservation>();
//	ReservList = ReservationsDAL.showCabinReservation(valg);
}

public int Month(){
	int month;
	while(true) {
		try {
			month = Integer.parseInt(ui.input(" "));
			if(month < 1 || month >= 13) {
				ui.besked("Indtast tal mellem 0 og 12");
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
				day = Integer.parseInt(ui.input(" "));
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
				year = Integer.parseInt(ui.input(" "));
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
