package Entity;

import Entity.Dataklasser.Kunde;

import java.sql.*;
import java.util.ArrayList;

public class KundeDAL {
	
	public static Kunde pull(int id){
		return pull(Integer.toString(id),3).get(0);
	}

	public static ArrayList<Kunde> pull(String valg, int i) {
		String sql = "SELECT * FROM kunde WHERE ";
		
		//Navn
		if(i == 1) {
			sql += "kunde_navn = '" + valg + "'";
		}

		//tlf
		if(i == 2) {
			sql += "tlf = '" + valg + "'";
		}

		//id
		if(i == 3) {
			sql += "kunde_id = '" + valg + "'";
		}
		
		ResultSet rs = DAL.pull(sql);
		ArrayList<Kunde> kundeList = new ArrayList<Kunde>();
		try {
			while (rs.next()) {
				kundeList.add(new Kunde(rs.getString("kunde_navn"), rs.getInt("kunde_id"), rs.getString("tlf")));
			}
		} catch (SQLException e) {
			System.out.println("SQL Fejl: " + e.getMessage());
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
			return null;
		}
		return kundeList;
	}

	public static int pushNew(Kunde kunde) {
		String sql = "SELECT * FROM kunde";
		ArrayList<Integer> kundeID = new ArrayList<Integer>();
		ResultSet rs = DAL.pull(sql);
		// Ledigt ID findes
		try {
			while (rs.next()) {
				kundeID.add(rs.getInt("kunde_id"));
			}
		} catch (SQLException e) {
			System.out.println("SQL Fejl: " + e.getMessage());
			return 0;
		}

		int counter = 1;
		for (int id : kundeID) {
			if (!(id == counter)) {
				sql = "INSERT INTO kunde VALUES ('" + kunde.getNavn() + "', " + counter + " ,'" + kunde.getTlf()
						+ "');";
				if(DAL.push(sql))
					return counter;
				
				return -1;
			}
			counter++;
		}

		sql = "INSERT INTO kunde VALUES ('" + kunde.getNavn() + "', " + counter + " ,'" + kunde.getTlf() + "')";
		if(DAL.push(sql)) {
			return counter;
			
		} else {
			return 0;
		}

	}

	public static boolean update(Kunde kunde) {
		// TODO implement this
		return false;
	}
}
