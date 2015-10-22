package Entity;

import java.sql.*;
import java.util.ArrayList;
import java.util.Locale;

public class DAL {
	
	//Hvilken driver anvendes
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

	//URL
	static final String DB_URL = "jdbc:mysql://localhost/test";
	//Bruger
	static final String USER = "root";
	//Pass
	static final String PASS = "";
	
	Connection conn;
	Statement stmt;
	
	public DAL() {
		try {

			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Forbinder til database");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			stmt = conn.createStatement();
			System.out.println("Forbindelse oprettet");
		} catch (SQLException se) {
			se.printStackTrace();

		} 
		catch (Exception e) {
			e.printStackTrace();

		}
	}
	
	
	public void hello() {
		
		
	}
	
	public boolean insertName(int id, String name) {
		try {
			String sql = String.format(Locale.US, "INSERT INTO test_name VALUES (" + id + ", '" + name + "')");
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			

		} catch(SQLException e) {
			System.out.println("SQL Fejl: " + e.getMessage());
			return false;
		}
		return true;
		
	}
	
	public void printNames() {
		
		try {
			String sql = String.format(Locale.US, "SELECT * FROM test_name");
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				System.out.println(rs.getInt("PersonID") + " " + rs.getString("Name"));
			}
		} catch(SQLException e) {
			System.out.println("SQL Fejl: " + e.getMessage());
		}
	}

}
