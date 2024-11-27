package com.itec;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbTest {
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/ses-sys";
		String username = "root";
		String password = "root";

		try {
			Connection connection = DriverManager.getConnection(url, username, password);
			System.out.println("Database connection successful!");
			connection.close();
		} catch (SQLException e) {
			System.err.println("Database connection failed. Error message: " + e.getMessage());
		}
	}
}
