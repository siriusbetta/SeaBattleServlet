package com.seabattle.model;

import java.util.Random;
/***
 * 
 * @author Alexey Konyshev
 * Модель корабля, линкор. Занимает 2 клетки.
 */
public class Destroyer extends Ship{
	private String shipName;
	
	Random rnd;
	/***
	 * Конструктор- определяет имя, размер и координаты.
	 * Положение: горизонтальное или вертикальное.
	 * Координаты задаются случайным образом. Поле 10 клеток, корабль 2 клетки, 
	 * координаты задаются от 0 до 9, чтобы хвост корабля не вышел за пределы
	 * игрового поля
	 */
	public Destroyer() {
		shipName = "Destroyer";
		lengthOfShip = 2;
		rnd = new Random();
		x = rnd.nextInt(9);
	    y = rnd.nextInt(9);
	    if(rnd.nextBoolean()){
			orientOnField = 1;
		}else{
			orientOnField = -1;
		}
		
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((shipName == null) ? 0 : shipName.hashCode());
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
		Destroyer other = (Destroyer) obj;
		if (shipName == null) {
			if (other.shipName != null)
				return false;
		} else if (!shipName.equals(other.shipName))
			return false;
		return true;
	}
	public String toString(){
		return shipName;
	}
}
