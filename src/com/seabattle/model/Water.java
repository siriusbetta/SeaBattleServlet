package com.seabattle.model;
/***
 * 
 * @author Alexey Konyshev
 *	Модель ячейки игрового поля.
 */
public class Water{
	/***
	 * определяет занятость клетки игрового поля: 1 - занято, -1 - не занято, но находится
	 * в непосредственной близости с кораблем и запрещенно занимать, 0  - свободно для 
	 * размещения
	 */
	private int dangerWater;
	
	private int x;
	private int y;
	
	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public Water(int x, int y) {
		this.x = x;
		this.y = y;
		setDangerWater(0);
	}

	public int getDangerWater() {
		return dangerWater;
	}

	public void setDangerWater(int dangerWater) {
		this.dangerWater = dangerWater;
	}

	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + dangerWater;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Water other = (Water) obj;
		if (dangerWater != other.dangerWater)
			return false;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	public String toString(){
		return String.valueOf(x) + String.valueOf(y) ;
	}
	
}
