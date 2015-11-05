package Entity;

import java.sql.*;
import java.util.Locale;

import Entity.Dataklasser.HyttePlads;

public class DAL {
	
	
	//Hvilken driver anvendes
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

	//URL
	static final String DB_URL = "jdbc:mysql://localhost/costakalundborg";
	//Bruger
	static final String USER = "root";
	//Pass
	static final String PASS = "";

	Connection conn;
	Statement stmt;
	ResultSet rs;
	
	//Pull
	public ResultSet pull(String sql) {
		try {
			System.out.println("Issued pull statement");
			sql = String.format(Locale.US, sql);
			
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

		} catch(SQLException e) {
			System.out.println("SQL Fejl: " + e.getMessage());
		}
		System.out.println("Returning rs");
		return rs;
	}
	
	
	//Pull
	
}
