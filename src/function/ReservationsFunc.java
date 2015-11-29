package function;

import java.util.ArrayList;
import java.time.LocalDate;

import entity.ReservationsDAL;
import entity.dataklasser.Hytte;
import entity.dataklasser.HyttePlads;
import entity.dataklasser.Reservation;

public class ReservationsFunc {
	
	public static void tjekInd(ArrayList<Reservation> reservation) {
		// TODO Auto-generated method stub
		
	}

	public static Reservation nyFraArray(String[] attributter) throws FunctionException {
		/*		"Dato for ankomst (yyyy-mm-dd)", 
				"Dato for afrejse (yyyy-mm-dd) eller skriv antal overnatninger",
				"Antal voksne (Intet valg = "+ Reservation.STANDARD_VOKSNE +")",
				"Antal børn (Intet valg = "+ Reservation.STANDARD_BØRN +")",
				"Antal hunde (Intet valg = "+ Reservation.STANDARD_HUND +")", */
		 
		LocalDate start = null;
		LocalDate slut = null;
		int voksne = 0;
		int børn = 0;
		int hunde = 0;
		
		ArrayList<String> fejlbeskedder = new ArrayList<String>();
		Reservation nyReservation = new Reservation();
		
		try {
			start = LocalDate.parse(attributter[0]);
		} catch (Exception e) {
			fejlbeskedder.add("Start dato er ikke korrekt format");
		} 
		
		try {
			int days = Integer.parseInt(attributter[1]);
			if (start != null){
				slut = start.plusDays(days);
			}
		} catch (Exception e) {
			try {
				slut = LocalDate.parse(attributter[1]);
			} catch (Exception e2) {
				fejlbeskedder.add("Slut dato er ikke korrekt format");
			}
		}
		
		if (start != null && slut != null && !slut.isAfter(start)){
			fejlbeskedder.add("Slutdato er ikke efter startdato");
		}
		
		try {
			voksne = Integer.parseInt(attributter[2]);
			if (voksne < 0){
				fejlbeskedder.add("Antal voksne er et negativt tal");
			}
		} catch (NumberFormatException e){
			fejlbeskedder.add("Antal voksne er ikke et tal");
		}
		
		try {
			børn = Integer.parseInt(attributter[2]);
			if (børn < 0){
				fejlbeskedder.add("Antal børn er et negativt tal");
			}
		} catch (NumberFormatException e){
			fejlbeskedder.add("Antal børn er ikke et tal");
		}

		try {
			hunde = Integer.parseInt(attributter[2]);
			if (hunde < 0){
				fejlbeskedder.add("Antal hunde er et negativt tal");
			}
		} catch (NumberFormatException e){
			fejlbeskedder.add("Antal hunde er ikke et tal");
		}
		
		if (!fejlbeskedder.isEmpty())
			throw new FunctionException(fejlbeskedder);

		nyReservation.setStart_dato(start);
		nyReservation.setSlut_dato(slut);
		nyReservation.setAntal_voksne(voksne);
		nyReservation.setAntal_børn(børn);
		nyReservation.setAntal_hunde(hunde);
		
		return nyReservation;
	}

	public static int tjekLedige(HyttePlads hyttePlads, LocalDate fra, LocalDate til) {
		ArrayList<Reservation> reservationer;
		if(hyttePlads instanceof Hytte){
			Hytte hytte = (Hytte)hyttePlads;
			reservationer = ReservationsDAL.getHytteReservationer(hytte.getHytteType(), fra, til);
		} else
			reservationer = ReservationsDAL.getReservationer(hyttePlads.getType(), fra, til);
		
		ArrayList<IntervalDate> dates = new ArrayList<IntervalDate>();
		for (Reservation res: reservationer){
			dates.add(new IntervalDate(res.getStart_dato(), true));
			dates.add(new IntervalDate(res.getStart_dato(), false));
		}
		dates.sort(null);
		int overlap = 0;
		int maxOverlap = 0;
		for (IntervalDate date : dates){
			if(date.isStart){
				overlap++;
				if (overlap > maxOverlap)
					maxOverlap = overlap;
			} else
				overlap--;
		}
		
		if(hyttePlads instanceof Hytte){
			Hytte hytte = (Hytte)hyttePlads;
			ArrayList<Hytte> hytter = AdministrationsFunc.getHytterAfType(hytte.getHytteType());
			return hytter.size() - maxOverlap;
		} else {
			ArrayList<HyttePlads> hyttepladser = AdministrationsFunc.getHyttePladsAfType(hyttePlads.getType());
			return hyttepladser.size() - maxOverlap;
		}
	}

	public static ArrayList<Reservation> getReservationerForKunde(int kundeId) {
		// TODO Auto-generated method stub
		return null;
	}

}
