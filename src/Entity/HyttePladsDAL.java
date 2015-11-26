package Entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Entity.Dataklasser.HyttePlads;

public class HyttePladsDAL {

	public static ArrayList<HyttePlads> getHyttePladser() {
		String sql = "SELECT * FROM HyttePlads";
		ArrayList<HyttePlads> hyttePladsList = new ArrayList<HyttePlads>();
		
		ResultSet rs = DAL.pull(sql);
		try {

			while (rs.next()) {
				hyttePladsList.add(new HyttePlads(
						rs.getInt("plads_id"),
						rs.getInt("type"),
						rs.getInt("lavseason"),
						rs.getInt("hoejseason"),
						rs.getInt("iStatus"),
						rs.getInt("maaler_status"),
						rs.getString("sStatus")));
			}
		} catch (SQLException e) {
			System.out.println("SQL Fejl: " + e.getMessage());
			return null;
		}
		return hyttePladsList;
	}

	public static HyttePlads getHyttePlads(int id) {
		String sql = "SELECT * FROM HyttePlads WHERE plads_id = ";
		ResultSet rs = DAL.pull(sql);
		HyttePlads hyttePlads;
		try {
			;
			hyttePlads = new HyttePlads(
					rs.getInt("plads_id"),
					rs.getInt("type"),
					rs.getInt("lavseason"),
					rs.getInt("hoejseason"),
					rs.getInt("iStatus"),
					rs.getInt("maaler_status"),
					rs.getString("sStatus"));

		} catch (SQLException e) {
			System.out.println("SQL Fejl: " + e.getMessage());
			return null;
		}
		return hyttePlads;

	}

	public static void createHyttePlads(HyttePlads nyHyttePlads) {
		// TODO Auto-generated method stub

	}

	public static void updateHyttePlads(HyttePlads nyHP) {
		// TODO Auto-generated method stub

	}

	public static void deleteHyttePlads(int id) {
		// TODO Auto-generated method stub

	}

}
