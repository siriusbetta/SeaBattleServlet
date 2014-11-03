package com.seabattle.database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CoordinatsMapper {
	public Coordinats mapRow(ResultSet rs) throws SQLException{
		
		Coordinats coordinata = new Coordinats();
		coordinata.setId(rs.getInt("id"));
		coordinata.setX(rs.getInt("x"));
		coordinata.setY(rs.getInt("y"));
		return coordinata;
	}
}
