package com.seabattle.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/***
 * 
 * @author Alexey Konyshev
 * Драйвер базы данных MySQL
 */
public class DBConectionManager {
	private Connection connection;
	/***
	 * Конструктор, создает соединение
	 * @param url путь к серверу
	 * @param user имя пользовователя
	 * @param pass пароль
	 * @throws ClassNotFoundException выбрасывает исключение если файл не найдет
	 * @throws SQLException выбрасывает SQL исключение 
	 */
	public DBConectionManager(String url, String user, String pass) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		this.connection = DriverManager.getConnection(url, user, pass);
	}
	
	public Connection getConnection(){
		return this.connection;
	}
}
