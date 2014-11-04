package com.seabattle.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;
/***
 * 
 * @author Alexey Konyshev
 * класс содержит методы переопределенные в интерфейсе DAO
 */
public class CoordinataJDBC implements CoordiantsDAO{
	PreparedStatement ps = null;
	Connection con = null;
	ResultSet rs = null;
	/***
	 * устанавливает соединение с базой данных
	 * @param con Connection 
	 */
	@Override
	public void setDataSource(Connection con) {
		this.con = con; 
	}
	/***
	 * create() вводит в базу данных координаты кораблей
	 * @param shipsCoordinats Лист с координатами кораблей
	 * @throws SQLException выбрасывает SQL исключение
	 */
	@Override
	public void create(List<Coordinats> shipsCoordinats) throws SQLException {
		String SQL = "insert into game_sets(id, time_creation) values (?,?)";
		ps = (PreparedStatement) con.prepareStatement(SQL);
		ps.setObject(1, null);
		ps.setObject(2, null);
		//ps.setBoolean(2, (Boolean) null);
		ps.execute();
		SQL = "insert into coordinats(id, x, y) values (LAST_INSERT_ID(), ?, ?)";
		ps = (PreparedStatement)con.prepareStatement(SQL);
		List<Coordinats> ships = shipsCoordinats; 
		Iterator<Coordinats> iter = ships.iterator();
		while(iter.hasNext()){
			Coordinats coordinats = iter.next();
			ps.setInt(1, coordinats.getX());
			ps.setInt(2, coordinats.getY());
			ps.execute();
		}
		
	}
	/***
	 * getCoordiants() извлекает из базы данных координаты в соответсвии с id
	 * @param id игрового поля с установленными координатами
	 * @return List с координатами кораблей
	 * @throws SQLException выбрасывает SQL исключение
	 */
	@Override
	public List<Coordinats> getCoordinats(int id) throws SQLException {
		String SQL = "select id, x, y from coordinats where id = ?";
		List<Coordinats> coordinats = new ArrayList<>();
		ps = (PreparedStatement) con.prepareStatement(SQL);
		ps.setInt(1, id);
		rs = ps.executeQuery();
		while(rs.next()){
			Coordinats coor = new Coordinats(rs.getInt("x"), rs.getInt("y"));
			coordinats.add(coor);
		}
		return coordinats;
	}

}
