package Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import Boundary.IUI;
import Entity.HyttePladsDAL;
import Entity.ReservationsDAL;
import Entity.Dataklasser.HyttePlads;
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
//		int start_day, start_month, start_year;
//		int end_day, end_month, end_year;
//		ui.besked("Indtast datoen du gerne vil starte dit ophold i DD/MM/YY");
//		end_day = Day();
//		
//		ui.besked("Indtast datoen du gerne vil starte dit ophold i DDMMYY");
//		end_day = Day();
//		
//		ui.besked("vælg hvilken type hytte du gerne vil leje");
//		String[] menulist = {"tilbage", "Luksushytte med tagteresse", "Luksushytte", "4 personers hytte", "2 personer hytte", "Lille 2 personer hytte"};
//		ArrayList<Reservation> TypeList;
//		ReservationsDAL hej = new ReservationsDAL();
//		while (true) {
//			int valg = ui.visMenu("type", menulist);
//						switch (valg) {
//						case 0:
//							//Sluk programmet
//							return;
//						case 1:
//							TypeList = hej.getHytteReservationsByType(valg);
//							break;
//						case 2:
//							TypeList = hej.getHytteReservationsByType(valg);
//							break;
//						case 3:
//							TypeList = hej.getHytteReservationsByType(valg);
//							break;
//						case 4:
//							TypeList = hej.getHytteReservationsByType(valg);
//							break;
//						case 5:
//							TypeList = hej.getHytteReservationsByType(valg);
//							break;
//						}
//					}
	}
	
	public void Checkout(){
		//TODO stuff
		
	}
	
	public void testOverLap(int start_day, int start_month, int start_year, int end_day, int end_month, int end_year, Reservation reservation){
		if(start_year ==1){
			
		}
	}
	
	public void createReservation(){
		//TODO stuff
		
	}
	
	public void showReservations(){
		// stuff
	}

private void getCabinReservations(int valg) {
		// TODO Auto-generated method stub
	
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
				ui.besked("Indtast tal mellem 1 og 12");
			}else{
				return month;
			}
		} catch(Exception e) {
			System.out.println("Indtast et heltal");
		}
	}
}
	
	
	public String Day(){
		String date = "hej";
		while (true) {
			try {
				date = ui.input("");
				
				if(date.length()>10){
					ui.besked("Du har ikke indtastet startdatoen i formatet DD/MM/YY, f.eks 01/10/2015. Prøv igen");
					break;
				}
				
				String[] parts = date.split("/");
				if(parts[1].length()!=2 || parts[2].length()!=2 || parts[3].length()!=4){
					ui.besked("Du har ikke indtastet startdatoen i formatet DD/MM/YY, f.eks 01/10/2015. Prøv igen");
					break;
				}
				
				if(parts[0].startsWith("0")){
					
				}
				
				
				
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e);
			}
			
		}
		return date;
		
		
		
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
