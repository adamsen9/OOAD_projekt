
package Entity;
import Entity.Dataklasser.Kunde;
import Entity.Dataklasser.Reservation;

import java.sql.*;
import java.util.ArrayList;

public class ReservationsDAL {
	static DAL dal;
	ArrayList<Integer> cabinLuxWT = new ArrayList<Integer>();
	ArrayList<Integer> cabinLux = new ArrayList<Integer>();
	ArrayList<Integer> cabin4Pers = new ArrayList<Integer>();
	ArrayList<Integer> cabin2Pers = new ArrayList<Integer>();
	ArrayList<Integer> cabin2PersSmall = new ArrayList<Integer>();
	
	
	
	public ReservationsDAL() {
		dal = new DAL();	
		}



//	public static ArrayList<Reservation> showCabinReservation(int valg) {
//		String sql = "SELECT * FROM HytterPladser WHERE ";
//		
//		if(valg == 1) {
//			sql += "type = '" + valg + "'";
//			
//		}
//		
//		if(valg == 2) {
//			sql += "type = '" + valg + "'";
//			
//		}
//		
//		if(valg == 3) {
//			sql += "type = '" + valg + "'";
//		}
//		
//		if(valg == 4) {
//			sql += "type = '" + valg + "'";
//		}
//		
//		if(valg == 5) {
//			sql += "type = '" + valg + "'";
//		}
//		
//		ResultSet rs = dal.pull(sql);
//		ArrayList<Reservation> ReservList = new ArrayList<Reservation>();
//		
//		try {
//			while (rs.next()) {
//				ReservList.add(new Reservation(rs.getInt("plads_id"), rs.getInt("type"), rs.getInt("iStatus"),rs.getString("sStatus"),rs.getInt("elmaaler_id"),rs.getInt("maaler_status")));
//			}
//		} catch (SQLException e) {
//			System.out.println("SQL Fejl: " + e.getMessage());
//			return null;
//		}
//		
//		return null;
//	}
	
	
	}
	
	
	

