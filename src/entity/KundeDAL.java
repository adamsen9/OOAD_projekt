package entity;

import java.sql.*;
import java.util.ArrayList;

import entity.dataklasser.Kunde;

public class KundeDAL {
	
	public static Kunde pull(int id){
		return pull(Integer.toString(id),3).get(0);
	}

	public static ArrayList<Kunde> pull(String tekst, int valg) {
		String sql = "SELECT * FROM kunde WHERE ";
		
		switch (valg) {
		//Navn
		case 1:
			sql += "kunde_navn = '" + tekst + "'";
			break;
		//tlf
		case 2:
			sql += "tlf = '" + tekst + "'";
			break;
		//id
		case 3:
			sql += "kunde_id = '" + tekst + "'";
			break;
		}
		
		ResultSet rs = DAL.pull(sql);
		ArrayList<Kunde> kundeList = new ArrayList<Kunde>();
		try {
			while (rs.next()) {
				kundeList.add(new Kunde(rs.getString("kunde_navn"), rs.getInt("kunde_id"), rs.getString("tlf")));
			}
		} catch (SQLException e) {
			System.out.println("SQL Fejl: " + e.getMessage());
			e.printStackTrace();
			return null;
		}
		return kundeList;
	}

	
	public static ArrayList<Kunde> pullAll() {
		String sql = "SELECT * FROM kunde";
		ResultSet rs = DAL.pull(sql);
		ArrayList<Kunde> kundeList = new ArrayList<Kunde>();
		try {
			while (rs.next()) {
				kundeList.add(new Kunde(rs.getString("kunde_navn"), rs.getInt("kunde_id"), rs.getString("tlf")));
			}
		} catch (SQLException e) {
			System.out.println("SQL Fejl: " + e.getMessage());
			e.printStackTrace();
			return null;
		}
		return kundeList;
	}

	public static int pushNew(Kunde kunde) {
		String sql = "SELECT * FROM kunde ORDER BY kunde_id;";
		ResultSet rs = DAL.pull(sql);

		// Ledigt ID findes
		int counter = 1;
		try {
			while (rs.next()) {
				if (rs.getInt("kunde_id") != counter) {
					break;
				}
				counter++;
			}
		} catch (SQLException e) {
			System.out.println("SQL Fejl: " + e.getMessage());
			return 0;
		}

		sql = "INSERT INTO kunde VALUES ('" + kunde.getNavn() + "', " + counter + " ,'" + kunde.getTlf() + "')";
		if(DAL.push(sql)) {
			return counter;
			
		} else {
			return 0;
			
		}

	}

	public static boolean update(Kunde kunde) {
		// TODO
		return false;
	}
}
