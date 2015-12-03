package entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.dataklasser.Hytte;
import entity.dataklasser.HyttePlads;

public class HyttePladsDAL {

	public static ArrayList<HyttePlads> getHyttePladser() {
		String sql = "SELECT * FROM hytterpladser;";
		ArrayList<HyttePlads> hyttePladsList = new ArrayList<HyttePlads>();

		ResultSet rs = DAL.pull(sql);
		try {

			while (rs.next()) {
				hyttePladsList.add(new HyttePlads(
						rs.getInt("plads_id"),
						rs.getInt("type"),
						rs.getInt("iStatus"),
						rs.getInt("elmaaler_id"),
						rs.getInt("maaler_status"),
						rs.getString("sStatus")));
			}
		} catch (SQLException e) {
			System.out.println("SQL Fejl: " + e.getMessage());
			e.printStackTrace();
			return null;
		}
		return hyttePladsList;
	}

	public static HyttePlads getHyttePlads(int id) {
		String sql = "SELECT * FROM hytterpladser WHERE plads_id = " + id + ";";
		ResultSet rs = DAL.pull(sql);
		HyttePlads hyttePlads;
		try {
			rs.next();
			hyttePlads = new HyttePlads(
					rs.getInt("plads_id"),
					rs.getInt("type"),
					rs.getInt("iStatus"),
					rs.getInt("elmaaler_id"),
					rs.getInt("maaler_status"),
					rs.getString("sStatus"));
		} catch (SQLException e) {
			System.out.println("SQL Fejl: " + e.getMessage());
			e.printStackTrace();
			return null;
		}
		return hyttePlads;

	}

	public static void createHyttePlads(HyttePlads nyHyttePlads) {
		// TODO

	}

	public static void updateHyttePlads(HyttePlads nyHP) {
		// TODO

	}

	public static void deleteHyttePlads(int id) {
		// TODO

	}

	public static ArrayList<? extends HyttePlads> getHyttePladserByType(int type) {
		String sql;
		ArrayList<? extends HyttePlads> returnList;
		
		try {
			if (type == HyttePlads.HYTTE) {
				sql = "SELECT * FROM hytterpladser NATURAL JOIN hytter";
				sql += " WHERE type=" + type;
				sql += ";";
	
				ArrayList<Hytte> hytteList = new ArrayList<Hytte>();
				ResultSet rs = DAL.pull(sql);
				while (rs.next()) {
					hytteList.add( new Hytte(
						rs.getInt("plads_id"),
						rs.getInt("type"),
						rs.getInt("iStatus"),
						rs.getInt("elmaaler_id"),
						rs.getInt("maaler_status"),
						rs.getString("sStatus"),
						rs.getInt("antal_personer"),
						rs.getInt("størelse"),
						rs.getInt("hytte_nummer"),
						rs.getString("hytte_type")
					));
				}
				returnList = hytteList;
			} else {
				sql = "SELECT * FROM hytterpladser";
				sql += " WHERE type=" + type;
				sql += ";";
	
				ArrayList<HyttePlads> hyttePladsList = new ArrayList<HyttePlads>();
				ResultSet rs = DAL.pull(sql);
				while (rs.next()) {
					hyttePladsList.add(	new HyttePlads(
						rs.getInt("plads_id"),
						rs.getInt("type"),
						rs.getInt("iStatus"),
						rs.getInt("elmaaler_id"),
						rs.getInt("maaler_status"),
						rs.getString("sStatus")
					));
				}
				returnList = hyttePladsList;
			}
		} catch (SQLException e) {
			System.out.println("SQL Fejl: " + e.getMessage());
			e.printStackTrace();
			return null;
		}
		return returnList;
	}

	public static Hytte getHytte(int id) {
		String sql = "SELECT * FROM hytterpladser NATURAL JOIN hytter WHERE plads_id = " + id + ";";
		ResultSet rs = DAL.pull(sql);
		Hytte hytte = null;
		try {
			if (rs.next()){
			hytte = new Hytte(
					rs.getInt("plads_id"),
					rs.getInt("type"),
					rs.getInt("iStatus"),
					rs.getInt("elmaaler_id"),
					rs.getInt("maaler_status"),
					rs.getString("sStatus"),
					rs.getInt("antal_personer"),
					rs.getInt("størelse"),
					rs.getInt("hytte_nummer"),
					rs.getString("hytte_type")
				);
			}
		} catch (SQLException e) {
			System.out.println("SQL Fejl: " + e.getMessage());
			e.printStackTrace();
			return null;
		}
		return hytte;
	}

	public static ArrayList<Hytte> getHytterAfType(String hytteType) {
		String sql;
		
		sql = "SELECT * FROM hytterpladser NATURAL JOIN hytter";
		sql += " WHERE hytte_type='" + hytteType + "';";

		ArrayList<Hytte> hytteList = new ArrayList<Hytte>();
		ResultSet rs = DAL.pull(sql);
		try{
			while (rs.next()) {
				hytteList.add( new Hytte(
					rs.getInt("plads_id"),
					rs.getInt("type"),
					rs.getInt("iStatus"),
					rs.getInt("elmaaler_id"),
					rs.getInt("maaler_status"),
					rs.getString("sStatus"),
					rs.getInt("antal_personer"),
					rs.getInt("størelse"),
					rs.getInt("hytte_nummer"),
					rs.getString("hytte_type")
				));
			}
		} catch (SQLException e) {
			System.out.println("SQL Fejl: " + e.getMessage());
			e.printStackTrace();
			return null;
		}
		return hytteList;
	}
}
