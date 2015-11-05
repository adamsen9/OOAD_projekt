package Entity;

import Entity.Dataklasser.Kunde;

import java.sql.*;
import java.util.ArrayList;

public class KundeDAL {
	DAL dal;

	public KundeDAL() {
		dal = new DAL();
	}

	public ArrayList<Kunde> pullAll() {
		String sql = "SELECT * FROM kunde";
		ResultSet rs = dal.pull(sql);
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

	public Kunde pull() {

		return new Kunde();
	}

	public boolean pushNew(Kunde kunde) {
		String sql = "SELECT * FROM kunde";
		ArrayList<Integer> kundeID = new ArrayList<Integer>();
		ResultSet rs = dal.pull(sql);
		// Ledigt ID findes
		try {
			while (rs.next()) {
				kundeID.add(rs.getInt("kunde_id"));
			}
		} catch (SQLException e) {
			System.out.println("SQL Fejl: " + e.getMessage());
			return false;
		}

		int counter = 1;
		boolean inserted = false;
		for (int id : kundeID) {
			if (!(id == counter)) {
				sql = "INSERT INTO kunde VALUES + '" + kunde.getNavn() + "', " + counter + " ,'" + kunde.getTlf()
						+ "')";
				inserted = dal.push(sql);
				return inserted;
			}
			counter++;
		}

		sql = "INSERT INTO kunde VALUES ('" + kunde.getNavn() + "', " + counter + " ,'" + kunde.getTlf() + "')";
		inserted = dal.push(sql);
		return inserted;

	}

	public boolean update(Kunde kunde) {

		return false;
	}
}
