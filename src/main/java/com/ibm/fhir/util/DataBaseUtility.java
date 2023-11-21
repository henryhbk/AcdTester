package com.ibm.fhir.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;

public class DataBaseUtility {
	private static BasicDataSource dataSource;

	private static BasicDataSource getDataSource() {

		if (dataSource == null) {
			BasicDataSource ds = new BasicDataSource();
			ds.setUrl("jdbc:mysql://192.168.1.24:3306/mimiciiiv14?serverTimezone=America/New_York");
			ds.setUsername("credentialuser");
			ds.setPassword("dxbcamel");

			ds.setMinIdle(15);
			ds.setMaxIdle(25);
			ds.setMaxOpenPreparedStatements(100);

			dataSource = ds;
		}
		return dataSource;
	}

	public static void main(String[] args) throws SQLException {

		try (BasicDataSource dataSource = DataBaseUtility.getDataSource();
				Connection connection = dataSource.getConnection();
				PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM Patients");) {
			System.out.println("The Connection Object is of Class: " + connection.getClass());
			try (ResultSet resultSet = pstmt.executeQuery();) {
				while (resultSet.next()) {
					System.out.println(
							resultSet.getString(1) + "," + resultSet.getString(2) + "," + resultSet.getString(3));
				}
			} catch (Exception e) {
				connection.rollback();
				e.printStackTrace();
			}
		}
	}

}