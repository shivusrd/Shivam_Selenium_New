package com.model.launch;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseInteraction {

	private static final String JDBC_DRIVER = "org.postgresql.Driver";
	private static final String DB_URL = "jdbc:postgresql://keng03-dev01-ins06-wfm76-dbs-01.int.dev.mykronos.com:5444/ppas_sdams1902";
	private static final String USER = "tkcsowner";
	private static final String PASS = "tkcsowner";
	private static final String QUERY = "select * from pc_u_fruit0_nonprd_01.person order by personid desc ;";

	public List<String> runSelectQuery(String query) throws SQLException, ClassNotFoundException {
		List<String> results = new ArrayList<>();
		Class.forName(JDBC_DRIVER);
		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {

			while (rs.next()) {
				int id = rs.getInt("personid");
				String propertyname = rs.getString("lastnm");
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
				"SELECT firstnm FROM pc_u_fruit0_nonprd_01.person WHERE personid = (SELECT MAX(personid) FROM pc_u_fruit0_nonprd_01.person);");
		System.out.println("Single Value: " + singleValue);

		// Example using executeUpdateQuery
		db.executeUpdateQuery(
				"UPDATE pc_u_fruit0_nonprd_01.person SET firstnm = 'Shivam' WHERE personid = (SELECT MAX(personid) FROM pc_u_fruit0_nonprd_01.person);");
		System.out.println("Update query executed.");
		
		//Verify the update.
        String updatedName = db.getSingleValueFromDB("SELECT firstnm FROM pc_u_fruit0_nonprd_01.person WHERE personid = (SELECT MAX(personid) FROM pc_u_fruit0_nonprd_01.person);");
        System.out.println("Updated Name: " + updatedName);

	}
}
