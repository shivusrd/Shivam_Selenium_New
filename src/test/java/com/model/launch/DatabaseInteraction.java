package com.model.launch;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseInteraction {

	private static final String JDBC_DRIVER = "org.sqlite.JDBC";
	private static final String DB_URL = "jdbc:sqlite:C:\\Users\\dubey\\AppData\\Roaming\\DBeaverData\\workspace6\\.metadata\\sample-database-sqlite-1\\Chinook.db";
	private static final String USER = "tkcsowner";
	private static final String PASS = "tkcsowner";
	private static final String QUERY = "Select * from Artist a ;";

	public List<String> runSelectQuery(String query) throws SQLException, ClassNotFoundException {
		List<String> results = new ArrayList<>();
		Class.forName(JDBC_DRIVER);
		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {

			while (rs.next()) {
				int id = rs.getInt("ArtistId");
				String propertyname = rs.getString("Name");
				results.add("ID: " + id + ", LastName: " + propertyname);
			}
		}
		return results;
	}

	public String getSingleValueFromDB(String query) throws SQLException, ClassNotFoundException {
		String result = null;
		Class.forName(JDBC_DRIVER);
		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {

			if (rs.next()) {
				result = rs.getString(1); // get first column of first row
			}
		}
		return result;
	}

	public void executeUpdateQuery(String query) throws SQLException, ClassNotFoundException {
		Class.forName(JDBC_DRIVER);
		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				Statement stmt = conn.createStatement()) {
			stmt.executeUpdate(query);
		}
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		DatabaseInteraction db = new DatabaseInteraction();

		// Example using runSelectQuery
		List<String> results = db.runSelectQuery(QUERY);
		for (String result : results) {

			System.out.println(result);
		}

		// Example using getSingleValueFromDB
		String singleValue = db.getSingleValueFromDB(
				"SELECT Name FROM Artist WHERE ArtistId = (SELECT MAX(ArtistID) FROM Artist);");
		System.out.println("Single Value: " + singleValue);

		// Example using executeUpdateQuery
		db.executeUpdateQuery(
				"UPDATE Artist SET Name = 'Shivam' WHERE ArtistID = (SELECT MAX(ArtistID) FROM Artist);");
		System.out.println("Update query executed.");
		
		//Verify the update.
        String updatedName = db.getSingleValueFromDB("SELECT Name FROM Artist WHERE ArtistId = (SELECT MAX(ArtistID) FROM Artist);");
        System.out.println("Updated Name: " + updatedName);

	}
}
