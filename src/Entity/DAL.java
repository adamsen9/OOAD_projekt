package Entity;

import java.sql.*;
import java.util.Locale;

import Entity.Dataklasser.HyttePlads;

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
	ResultSet rs;
	
	//Pull
	public ResultSet pull(String sql) {
		try {
			sql = String.format(Locale.US, sql);
			
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			stmt = conn.createStatement();
			
			stmt.executeUpdate(sql);
			return rs = stmt.executeQuery(sql);

		} catch(SQLException e) {
			System.out.println("SQL Fejl: " + e.getMessage());
		}
		return rs;
	}
	
	
	//Pull
	
}
