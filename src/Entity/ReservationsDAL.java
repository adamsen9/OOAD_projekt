
package Entity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Entity.Dataklasser.Reservation;

public class ReservationsDAL {

	public static ArrayList<Reservation> getHytteReservationsByType(int valg) {
		String sql = "SELECT res_id,state,start_dato,slut_dato,slut_el,plads_id,kunde_id,antal_voksne,antal_børn FROM Reservation NATURAL JOIN HytterPladser WHERE HytterPladser.type = " + valg;
		ArrayList<Reservation> TypeList = new ArrayList<Reservation>();
		
		ResultSet rs = DAL.pull(sql);
		try {

			while (rs.next()) {
				TypeList.add(new Reservation(
						rs.getInt("res_id"),
						rs.getInt("state"),
						rs.getString("start_dato"),
						rs.getString("slut_dato"),
						rs.getInt("slut_el"),
						rs.getInt("plads_id"),
						rs.getInt("kunde_id"),
						rs.getInt("antal_voksne"),
						rs.getInt("antal_børn")
						));
			}
		} catch (SQLException e) {
			System.out.println("SQL Fejl: " + e.getMessage());
			return null;
		}
		return TypeList;
	}
	
	}
	
	
	

	
	

