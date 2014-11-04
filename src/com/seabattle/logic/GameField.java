package com.seabattle.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.seabattle.database.Coordinats;
import com.seabattle.model.BattleShip;
import com.seabattle.model.Cruiser;
import com.seabattle.model.Destroyer;
import com.seabattle.model.GunBoat;
import com.seabattle.model.Ship;
import com.seabattle.model.Water;
/***
 * 
 * @author Aleksey Konyshev
 * <p>
 * Класс управляет всеми действиями на игровом поле.
 * раставляет корабли, проверяет положение таким образом,
 * чтобы они касались друг друга.
 * The ships can not touch each other.
 * </p>
 *
 */
public class GameField {
	Map<Integer, Water> gameField;
	
	Ship battleShip;
	Ship cruiser;
	Ship destroyer;
	Ship gunboat;
	/***
	 * Конструктор класса
	 * @param gameField Игровое поле
	 */
	public GameField() {
		gameField = new HashMap<Integer, Water>();
	}
	/***
	 * makeNewField() создает новое игровое поле с раставленными кораблями
	 * создается объект корабля и проверяется можно ли его установить на поле
	 * если условия игры совпадают, то корабль устанавлиется в нужной позиции
	 * игрового поля
	 * @param battleShip	4-х клеточный корабль, 1 шт.
	 * @param cruiser		3-х клеточный корабль, 2 шт.
	 * @param destroyer		2-х клоточный корабль, 3 шт.
	 * @param gunboat		1-х клеточный корабль, 4 шт.
	 */
	void makeNewField(){
		createClearField();
		battleShip = new BattleShip();
		while(!checkPosition(battleShip)){
			battleShip = new BattleShip();
		}
		addShip(battleShip);
		setGreyWater(battleShip);
		cruiser = new Cruiser();
		for(int i = 0; i < 2; i++){
			while(!checkPosition(cruiser)){
				cruiser = new Cruiser();
			}
			addShip(cruiser);
			setGreyWater(cruiser);
		}
		destroyer = new Destroyer();
		for(int i = 0; i < 3; i++){
			while(!checkPosition(destroyer)){
				destroyer = new Destroyer();
			}
			addShip(destroyer);
			setGreyWater(destroyer);
		}
		gunboat = new GunBoat();
		for(int i = 0; i < 4; i++){
			while(!checkPosition(gunboat)){
				gunboat = new GunBoat();
			}
			addShip(gunboat);
			setGreyWater(gunboat);
		}
	}
	/***
	 * checkPosition () проверяет не занято ли пространство для нового созданного
	 * корабля, если занято то возвращается false и объект корабля создатся вновь
	 * и до тех пор пока не найдется свободно место под нужный корабль
	 * @param ship объект корабля
	 * @return boolean сигнализирует о занятости или нет корабля
	 */
	boolean checkPosition(Ship ship){
		if(ship.orientOnField == 1){
				for(int i = 0; i < ship.lengthOfShip; i++){
					int dangerWater = gameField.get(ship.x * 10 + (ship.y + i)).getDangerWater();
					if(dangerWater != 0){
						ship = null;
						return false;
					}
				}
		}
		if(ship.orientOnField == -1){
			for(int i = 0; i < ship.lengthOfShip; i++){
				int dangerWater = gameField.get((ship.x + i) * 10 + ship.y).getDangerWater();
				if(dangerWater != 0){
					ship = null;
					return false;
				}
			}
		}
		if(ship.orientOnField == 0){
				int dangerWater = gameField.get((ship.x) * 10 + ship.y).getDangerWater();
				if(dangerWater != 0){
					ship = null;
					return false;
				}
			}
		return true;
	}
	/***
	 * Устанавливает в клетках игрового поля вокруг клеток занятых кораблями зону в
	 * одну клетку, которую корабли не могут занимать, чтобы не касаться друг друга.
	 * @param ship объект корабля
	 */
	void setGreyWater(Ship ship){
		
		//Vertical position
		//Mark water around ship, the ships 
		if(ship.orientOnField == 1){
			for(int i = 0; i < ship.lengthOfShip; i++){
				int X = ship.x;
				int Y = ship.y + i;
				if(!(X + 1 > 9)){
					int key = (X + 1) * 10 + Y;
					Water water = gameField.get(key);
					water.setDangerWater(-1);
					gameField.put(key, water);
				}
				
				if(!(X - 1 < 0)){
					int key = (X - 1) * 10 + Y;
					Water water = gameField.get(key);
					water.setDangerWater(-1);
					gameField.put(key, water);
				}
			}
			int start = 0;
			int end = 3;
			if(ship.x == 0){
				start = 1;
			}
			if(ship.x + 1 > 9){
				end = 2;
			}
			
			for(int i = start; i < end; i++){
				int X = ship.x +i;
				int Y = ship.y;
				if(!(Y - 1 < 0 )){
					int key = (X - 1) * 10 + (Y - 1);
					Water water = gameField.get(key);
					water.setDangerWater(-1);
					gameField.put(key, water);
				}
				if(!(Y + ship.lengthOfShip > 9)){
					int key = (X - 1) * 10 + (Y+ ship.lengthOfShip);
					Water water = gameField.get(key);
					water.setDangerWater(-1);
					gameField.put(key, water);
				}
				
			}
		}
		
		//Horizontal position
		
		if(ship.orientOnField == -1){
			for(int i = 0; i < ship.lengthOfShip; i++){
				int X = ship.x + i;
				int Y = ship.y;
				
				if(!(Y + 1 > 9)){
					int key = (X) * 10 + Y+1;
					Water water = gameField.get(key);
					water.setDangerWater(-1);
					gameField.put(key, water);
				}
				
				if(!(Y - 1 < 0)){
					int key = (X) * 10 + Y-1;
					Water water = gameField.get(key);
					water.setDangerWater(-1);
					gameField.put(key, water);
				}
			}
			int start = 0;
			int end = 3;
			if(ship.y == 0){
				start = 1;
			}
			if(ship.y + 1 > 9){
				end = 2;
			}
			
			for(int i = start; i < end; i++){
				int X = ship.x;
				int Y = ship.y+i;
				if(!(X - 1 < 0 )){
					int key = (X - 1) * 10 + (Y - 1);
					Water water = gameField.get(key);
					water.setDangerWater(-1);
					gameField.put(key, water);
				}
				if(!(X + ship.lengthOfShip > 9)){
					int key = (X + ship.lengthOfShip) * 10 + (Y-1);
					Water water = gameField.get(key);
					water.setDangerWater(-1);
					gameField.put(key, water);
				}
				
			}
		}
		
	}
	/***
	 * addShip() устанавливает на нужную позицию игрового поля корабль.
	 * @param ship объект корабля
	 */
	void addShip(Ship ship){
		if(ship.orientOnField == 1){
			for(int i = 0; i < ship.lengthOfShip; i++){
				Water water = gameField.get(ship.x * 10 + (ship.y + i));
				water.setDangerWater(1);
				gameField.put(ship.x * 10 + (ship.y + i), water);
			}
		}
		if(ship.orientOnField == -1){
			for(int i = 0; i < ship.lengthOfShip; i++){
				Water water = gameField.get((ship.x + i)* 10 + (ship.y));
				water.setDangerWater(1);
				gameField.put((ship.x + i) * 10 + (ship.y), water);
			}
		}
		if(ship.orientOnField == 0){
				Water water = gameField.get(ship.x * 10 + (ship.y));
				water.setDangerWater(1);
				gameField.put(ship.x * 10 + ship.y, water);
		 }
	}
	/***
	 * создает чистое игровое поле, заполняет его клетками Water.
	 */
	void createClearField(){
		for(int i = 0; i < 10; i++){
			for(int j = 0; j< 10; j++){
				gameField.put(i*10 + j, new Water(i, j));
			}
			
		}
	}
	
	/***
	 * getGameField() возвращает полностью подготовленное поле.
	 * @return Map gameField
	 */
	public Map<Integer, Water> getGameField(){
		makeNewField();
		return gameField;
	}
	/***
	 * переопределенное поле toString
	 */
	public String toString(){
		String out = "";
		
		for(int i = 0; i < 10; i++){
			for(int j = 0; j < 10; j++){
				if(gameField.get(j * 10 + i).getDangerWater() == 1){
					out += "|x|";
				}
				else{
					out += "| |";
				}
			}
			out += "\n";
		}
		return out;
	}
	/***
	 * mapToList() конвертирует Map игровое поле в List 
	 * @param map игровое поле
	 * @return water List игровое поле
	 */
	public List<Water> mapToList(Map<Integer, Water> map){
		List<Water> water = new ArrayList<Water>();
		for(int i = 0; i < 10; i++){
			for(int j = 0; j < 10; j++){
				water.add(i * 10 + j, map.get(i * 10 + j));
			}
		}
		return water;
	}
	/***
	 * ShipsCoordinants выводит из игрового поля координаты кораблей. 
	 * @param gameField Map игровое поле
	 * @return shipCoordinants List
	 */
	public List<Coordinats> ShipsCoordinats(Map<Integer, Water> gameField){
		List<Coordinats> shipCoordinates = new ArrayList<>();
		for(int i = 0; i < 10; i++){
			for(int j = 0; j < 10; j++){
				Water water = gameField.get(i * 10 + j);
				if(water.getDangerWater() == 1){
					shipCoordinates.add(new Coordinats(water.getX(), water.getY()));
				}
			}
		}
		return shipCoordinates;
	}
	/***
	 * placeShips() располагает корабли на карте в соответствии с координатами
	 * полученными из списка 
	 * @param coordinats Coordinats координаты кораблей во входящий список
	 * @return
	 */
	public Map<Integer, Water> placeShips(List<Coordinats> coordinats){
		Map<Integer, Water> gameField = new HashMap<>();
		for(int i = 0; i < 10; i++){
			for(int j = 0; j < 10; j++){
				gameField.put(i * 10 + j, new Water(i, j));
			}
		}
		Iterator<Coordinats> iter = coordinats.iterator();
		while(iter.hasNext()){
			Coordinats coordinata = iter.next();
			Water water = gameField.get(coordinata.getX() * 10 + coordinata.getY());
			water.setDangerWater(1);
			gameField.put(coordinata.getX() * 10 + coordinata.getY(), water);
		}
		return gameField;
	}
	
}
