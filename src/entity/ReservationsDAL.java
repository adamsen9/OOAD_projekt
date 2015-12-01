
package entity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import entity.dataklasser.Reservation;

public class ReservationsDAL {
	
	public static Reservation getReservation(int id){
		String sql = "SELECT * FROM reservation WHERE res_id =" + id;
		sql += ";";

		Reservation resservation = null;
		ResultSet rs = DAL.pull(sql);
	
		try {
			if(rs.next()){
				resservation = new Reservation(
						rs.getInt("res_id"),
						rs.getInt("status"),
						rs.getInt("start_el"),
						rs.getInt("slut_el"),
						rs.getInt("plads_type"), 
						rs.getInt("plads_id"), 
						rs.getInt("kunde_id"),
						rs.getInt("antal_voksne"),
						rs.getInt("antal_børn"),
						rs.getInt("antal_hunde"),
						rs.getDate("start_dato").toLocalDate(),
						rs.getDate("slut_dato").toLocalDate()
						);
			}
		} catch (SQLException e) {
			System.err.println("SQL fejl:" + e.getMessage());
			e.printStackTrace();
		}
		
		return resservation;
	}

	public static ArrayList<Reservation> getReservationer(int status){
		return getReservationer(-1, null,null, status);
	}
	
	public static ArrayList<Reservation> getReservationer(int type, LocalDate fra, LocalDate til){
		return getReservationer(type, fra, til, -1);
	}
	
	public static ArrayList<Reservation> getReservationer(int type, int status){
		return getReservationer(type, null, null, status);
	}
	
	public static ArrayList<Reservation> getReservationer(int type, LocalDate fra, LocalDate til, int status){
		String sql = "SELECT * FROM reservation";
		boolean first = true;
		if (type != -1){
			sql += " WHERE plads_type =" + type;
			first = false;
		}
		if (fra != null){
			if(first){
				sql += " WHERE slut_dato >= '" + fra + "'";
				first = false;
			} else
				sql += " AND slut_dato >= '" + fra + "'";
		}
		if (til != null){
			if(first){
				sql += " WHERE start_dato <= '" + til + "'";
				first = false;
			} else
				sql += " AND start_dato <= '" + til + "'";
		}
		if (status != -1){
			if(first){
				sql += " WHERE status <= '" + status + "'";
				first = false;
			} else
				sql += " AND status <= '" + status + "'";
		}
		sql += ";";
		
		ArrayList<Reservation> returnList = new ArrayList<Reservation>();
		ResultSet rs = DAL.pull(sql);
		try {
			while(rs.next()){
				returnList.add(new Reservation(
						rs.getInt("res_id"),
						rs.getInt("status"),
						rs.getInt("start_el"),
						rs.getInt("slut_el"),
						rs.getInt("plads_type"), 
						rs.getInt("plads_id"), 
						rs.getInt("kunde_id"),
						rs.getInt("antal_voksne"),
						rs.getInt("antal_børn"),
						rs.getInt("antal_hunde"),
						rs.getDate("start_dato").toLocalDate(),
						rs.getDate("slut_dato").toLocalDate()
						));
			}
		} catch (SQLException e) {
			System.err.println("SQL fejl:" + e.getMessage());
			e.printStackTrace();
		}
		
		return returnList;
	}
	
	public static int opretReservation(Reservation ny) {
		String sql = "SELECT * FROM reservation ORDER BY res_id;";
		ResultSet rs = DAL.pull(sql);

		// Ledigt ID findes
		int counter = 1;
		try {
			while (rs.next()) {
				if (rs.getInt("res_id") != counter) {
					break;
				}
				counter++;
			}
		} catch (SQLException e) {
			System.out.println("SQL Fejl: " + e.getMessage());
			return 0;
		}

		sql ="INSERT INTO reservation ("
				+ "res_id,"
				+ "status,"
				+ "start_dato,"
				+ "slut_dato,"
				+ "plads_type,"
				+ "plads_id,"
				+ "kunde_id,"
				+ "antal_voksne,"
				+ "antal_børn,"
				+ "antal_hunde,"
				+ "start_el,"
				+ "slut_el)"
				+ "VALUES ("
				+ counter + ","
				+ ny.getStatus() + ","
				+ "'" + ny.getStart_dato() + "',"
				+ "'" + ny.getSlut_dato() + "',"
				+ ny.getPlads_type() + ","
				+ ny.getPlads_id() + ","
				+ ny.getKunde_id() + ","
				+ ny.getAntal_voksne() + ","
				+ ny.getAntal_børn() + ","
				+ ny.getAntal_hunde() + ","
				+ ny.getStart_el() + ","
				+ ny.getSlut_el() + ");";

		if(DAL.push(sql))
			return counter;

		return 0;
	}

	public static ArrayList<Reservation> getHytteReservationer(String hytteType, LocalDate fra, LocalDate til) {
		String sql = "SELECT * FROM reservation NATURAL JOIN hytterpladser NATURAL JOIN hytter";
		sql += " WHERE hytte_type = '" + hytteType + "'";
		sql += " AND slut_dato >= '" + fra + "'";
		sql += " AND start_dato <= '" + til + "'";
		sql += ";";

		ArrayList<Reservation> returnList = new ArrayList<Reservation>();
		ResultSet rs = DAL.pull(sql);

		try {
			while(rs.next()){
				returnList.add(new Reservation(
						rs.getInt("res_id"),
						rs.getInt("status"),
						rs.getInt("start_el"),
						rs.getInt("slut_el"),
						rs.getInt("plads_type"), 
						rs.getInt("plads_id"), 
						rs.getInt("kunde_id"),
						rs.getInt("antal_voksne"),
						rs.getInt("antal_børn"),
						rs.getInt("antal_hunde"),
						rs.getDate("start_dato").toLocalDate(),
						rs.getDate("slut_dato").toLocalDate()
						));
			}
		} catch (SQLException e) {
			System.err.println("SQL fejl:" + e.getMessage());
			e.printStackTrace();
		}
		
		return returnList;
	}

	public static ArrayList<Reservation> getReservationerForKunde(int kundeId) {
		String sql = "SELECT * FROM reservation WHERE kunde_id =" + kundeId;
		sql += ";";
	
		ArrayList<Reservation> returnList = new ArrayList<Reservation>();
		ResultSet rs = DAL.pull(sql);
	
		try {
			while(rs.next()){
				returnList.add(new Reservation(
						rs.getInt("res_id"),
						rs.getInt("status"),
						rs.getInt("start_el"),
						rs.getInt("slut_el"),
						rs.getInt("plads_type"), 
						rs.getInt("plads_id"), 
						rs.getInt("kunde_id"),
						rs.getInt("antal_voksne"),
						rs.getInt("antal_børn"),
						rs.getInt("antal_hunde"),
						rs.getDate("start_dato").toLocalDate(),
						rs.getDate("slut_dato").toLocalDate()
						));
			}
		} catch (SQLException e) {
			System.err.println("SQL fejl:" + e.getMessage());
			e.printStackTrace();
		}
		
		return returnList;
	}

	public static boolean updateReservation(Reservation res) {
		String sql = "UPDATE reservation SET "
				+ "status =" + res.getStatus()
				+ ",start_dato ='" + res.getStart_dato() + "'"
				+ ",slut_dato='" + res.getSlut_dato() + "'"
				+ ",plads_type=" + res.getPlads_type()
				+ ",plads_id=" + res.getPlads_id()
				+ ",kunde_id=" + res.getKunde_id()
				+ ",antal_voksne=" + res.getAntal_voksne()
				+ ",antal_børn=" + res.getAntal_børn()
				+ ",antal_hunde=" + res.getAntal_hunde()
				+ ",start_el=" + res.getStart_el()
				+ ",slut_el=" + res.getSlut_el();
		sql += " WHERE + res_id =" + res.getId() + ";";
		return DAL.push(sql);
	}	
}
	
	
	

	
	

