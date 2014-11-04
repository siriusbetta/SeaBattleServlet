package com.seabattle.database;
/***
 * 
 * @author Alexey Konyshev
 * Модель координат корабля. 
 */
public class Coordinats {
	private int id;
	private int x;
	private int y;
	
	public Coordinats() {
	}
	public Coordinats(int x, int y) {
		setX(x);
		setY(y);
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

}
