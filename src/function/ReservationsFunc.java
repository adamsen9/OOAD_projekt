package function;

import java.util.ArrayList;
import java.time.LocalDate;

import entity.ReservationsDAL;
import entity.dataklasser.Hytte;
import entity.dataklasser.HyttePlads;
import entity.dataklasser.Reservation;

public class ReservationsFunc {
	
	public static boolean tjekInd(ArrayList<Reservation> reservationer) {
		boolean succes = true;
		for (Reservation res : reservationer){
			res.setStatus(Reservation.STATUS_I_BRUG);
			if(!ReservationsDAL.updateReservation(res))
				succes = false;
		}
		return succes;
	}

	public static Reservation nyFraArray(String[] attributter) throws FunctionException {
		/*		"Dato for ankomst (yyyy-mm-dd) (intet valg betyder dags dato)", 
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
			if(attributter[0].equals(""))
				start = LocalDate.now();
			else
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
			if (attributter[2].equals(""))
				attributter[2] = "" + Reservation.STANDARD_VOKSNE;
			voksne = Integer.parseInt(attributter[2]);
			if (voksne < 0){
				fejlbeskedder.add("Antal voksne er et negativt tal");
			}
		} catch (NumberFormatException e){
			fejlbeskedder.add("Antal voksne er ikke et tal");
		}
		
		try {
			if (attributter[3].equals(""))
				attributter[3] = "" + Reservation.STANDARD_BØRN;
			børn = Integer.parseInt(attributter[3]);
			if (børn < 0){
				fejlbeskedder.add("Antal børn er et negativt tal");
			}
		} catch (NumberFormatException e){
			fejlbeskedder.add("Antal børn er ikke et tal");
		}

		try {
			if (attributter[4].equals(""))
				attributter[4] = "" + Reservation.STANDARD_HUND;
			hunde = Integer.parseInt(attributter[4]);
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
		
		// tjeck om den specifikke plads er reserveret
		if(hyttePlads.getId() != 0){
			for (Reservation res: reservationer){
				if (res.getPlads_id() == hyttePlads.getId())
					return 0;
			}			
		}
		
		ArrayList<IntervalDate> dates = new ArrayList<IntervalDate>();
		for (Reservation res: reservationer){
			dates.add(new IntervalDate(res.getStart_dato(), true));
			dates.add(new IntervalDate(res.getSlut_dato(), false));
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
		return ReservationsDAL.getReservationerForKunde(kundeId);
	}

	public static ArrayList<Reservation> getReservationerForKunde(int kundeId, int status) {
		return ReservationsDAL.getReservationerForKunde(kundeId, status);
	}

	public static int opretReservation(Reservation nyReservation) {
		return ReservationsDAL.opretReservation(nyReservation);
	}

	public static Reservation getReservation(int id) {
		return ReservationsDAL.getReservation(id);
	}

}
