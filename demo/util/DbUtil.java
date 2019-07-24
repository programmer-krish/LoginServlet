package com.wisdom.demo.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {

	private static Connection connection = null;

	private DbUtil() {

	}

	static {

		String driverClass = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost/wisdom";
		String dbuser = "root";
		String dbpass = "";

		try {
			Class.forName(driverClass);
			connection = DriverManager.getConnection(url, dbuser, dbpass);
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException ......" + e);
		} catch (SQLException e) {
			System.out.println("SQLException in connecting time...." + e);
		}
	}

	public static Connection getConnection() {

		return connection;
	}
}