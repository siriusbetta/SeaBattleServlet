package com.seabattle.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface CoordiantsDAO {
	public void setDataSource(Connection con);
	public void create(List<Coordinats> shipsCoordinats) throws SQLException;
	public List<Coordinats> getCoordinats(int id) throws SQLException;
	public void delete(Integer id);
	
}
