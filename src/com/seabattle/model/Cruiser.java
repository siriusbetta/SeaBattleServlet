package com.seabattle.model;

import java.util.Random;
/***
 * 
 * @author Alexey Konyshev
 * Модель корабля, крейсер. Занимает 3 клетки.
 */
public class Cruiser extends Ship{
	
	Random rnd;
	/***
	 * Конструктор- определяет имя, размер и координаты.
	 * Положение: горизонтальное или вертикальное.
	 * Координаты задаются случайным образом. Поле 10 клеток, корабль 3 клетки, 
	 * координаты задаются от 0 до 8, чтобы хвост корабля не вышел за пределы
	 * игрового поля
	 */
	public Cruiser() {
		shipName = "Cruiser";
		lengthOfShip = 3;
		rnd = new Random();
		this.x = rnd.nextInt(8);
	    this.y = rnd.nextInt(8);
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
		Cruiser other = (Cruiser) obj;
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
