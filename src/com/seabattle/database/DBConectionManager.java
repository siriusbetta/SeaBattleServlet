package com.seabattle.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConectionManager {
	private Connection connection;
	
	public DBConectionManager(String url, String user, String pass) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		this.connection = DriverManager.getConnection(url, user, pass);
	}
	
	public Connection getConnection(){
		return this.connection;
	}
}
