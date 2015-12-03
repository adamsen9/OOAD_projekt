package entity;

import java.sql.*;
import java.util.Locale;

public class DAL {
	
	
	//Hvilken driver anvendes
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

	//URL
	static final String DB_URL = "jdbc:mysql://localhost/costakalundborg";
	
	//Bruger
	static final String USER = "root";
	//Pass
	static final String PASS = "password";

	//Pull
	public static ResultSet pull(String sql) {
		try {
			sql = String.format(Locale.US, sql);
			
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			return rs;
		} catch(SQLException e) {
			System.out.println("SQL Fejl: " + e.getMessage());
			e.printStackTrace(); 
			return null;
		}
	}
	
	
	//Push
	public static boolean push(String sql) {
		try {
			sql = String.format(Locale.US, sql);
			
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);

			return true;
		} catch(SQLException e) {
			System.out.println("SQL Fejl: " + e.getMessage());
			e.printStackTrace(); 
			return false;
		}
	}
}
