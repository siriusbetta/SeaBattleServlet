package com.seabattle.model;

/***
 * 
 * @author Alexey Konyshev
 * Абстрактный файл корабля, все корабли являются его потомками. 
 *
 */
public abstract class Ship {
	/***
	 * shipName название корабля
	 */
	String shipName;
	/***
	 * shipName размеры корабля
	 */
	public int lengthOfShip;
	/***
	 * orientOnField ориентация корабля: вертикальное или горизонтальное
	 */
	public int orientOnField = 0;
	public int x;
	public int y;
}
