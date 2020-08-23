package com.webapp;

import java.sql.*;

import java.util.HashMap;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



/*Class.forName("com.mysql.jdbc.Driver") ;
Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/DBNAME", "usrname", "pswd") ;
Statement stmt = conn.createStatement() ;
String query = "select columnname from tablename ;" ;
ResultSet rs = stmt.executeQuery(query) ;*/

public class ConditionService {
	// private DataSource dataSource;
	private static final String url = "jdbc:mysql://localhost:3306/app_prog";
	private static final String username = "root";
	private static final String password = "root";
	
	public HashMap<String, Integer> getPatientTypes(String condition) throws SQLException, ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		HashMap<String,Integer> patientTypes = new HashMap<String, Integer>();
		String query;
		try {
			connection = DriverManager.getConnection(url, username, password);
						
			query = "SELECT enrollment_type, sum(enrollment_number) " +
					"FROM allxml " +
					"WHERE mesh_terms like " + "'%" + condition + "%' " +
					"GROUP BY enrollment_type";
			statement = connection.prepareStatement(query);
			rs = statement.executeQuery();
			// get types - # patients
			while (rs.next()) {
				patientTypes.put(rs.getString(1), rs.getInt(2));
			}
			// get total studies referenced
			if (rs != null) rs.close();
			if (statement != null) statement.close();
			
			query = "SELECT count(*) from allxml where mesh_terms like "+
					"'%" + condition + "%'";
			statement = connection.prepareStatement(query);
			rs = statement.executeQuery();
			while (rs.next()) {
				patientTypes.put("Total_studies", rs.getInt(1));
			}
		}
		finally {
			if (rs != null) rs.close();
			if (statement != null) statement.close();
			if (connection != null) connection.close();
		}
		return patientTypes;
	}
}
