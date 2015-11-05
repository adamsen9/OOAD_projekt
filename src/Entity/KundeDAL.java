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
				kundeList.add(new Kunde(rs.getString("kunde_navn"),rs.getInt("kunde_id"),rs.getString("tlf")));
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
		String sql = "INSERT INTO kunde values ";
		
		
		return true;
	}
	
	public boolean update(Kunde kunde) {
		
		
		
		return false;
	}
}
