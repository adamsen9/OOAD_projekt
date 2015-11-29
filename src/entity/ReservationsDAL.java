
package entity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import entity.dataklasser.Reservation;

public class ReservationsDAL {

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
		// TODO Auto-generated method stub
		return -1;
	}

	public static ArrayList<Reservation> getHytteReservationer(String hytteType, LocalDate fra, LocalDate til) {
		String sql = "SELECT * FROM reservation NATURAL JOIN hyttepladser NATURAL JOIN hytter";
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
	
}
	
	
	

	
	

