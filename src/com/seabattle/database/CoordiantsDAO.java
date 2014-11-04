package com.seabattle.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
/***
 * 
 * @author Alexey Konyshev
 * интерфейс, содержит заголовки методов, переписанные JDBC классе
 */
public interface CoordiantsDAO {
	/***
	 * устанавливает соединение с базой данных
	 * @param con Connection 
	 */
	public void setDataSource(Connection con);
	/***
	 * create() вводит в базу данных координаты кораблей
	 * @param shipsCoordinats Лист с координатами кораблей
	 * @throws SQLException выбрасывает SQL исключение
	 */
	public void create(List<Coordinats> shipsCoordinats) throws SQLException;
	/***
	 * getCoordiants() извлекает из базы данных координаты в соответсвии с id
	 * @param id игрового поля с установленными координатами
	 * @return List с координатами кораблей
	 * @throws SQLException выбрасывает SQL исключение
	 */
	public List<Coordinats> getCoordinats(int id) throws SQLException;
	
}
