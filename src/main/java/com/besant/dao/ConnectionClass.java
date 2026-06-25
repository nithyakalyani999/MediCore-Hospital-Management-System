package com.besant.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionClass {
	public static Connection GetConnectionDetails() throws ClassNotFoundException, SQLException {
		// step1 load the driver class
		Class.forName(ConfigClass.MYSQL_DRIVER);

		// step2 create the connection object
		Connection con = DriverManager.getConnection(ConfigClass.MYSQL_URL, ConfigClass.MYSQL_USERNAME,
				ConfigClass.MYSQL_PASSWORD);

		return con;
		}
}
