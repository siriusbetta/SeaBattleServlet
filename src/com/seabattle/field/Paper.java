package com.seabattle.field;

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
 * The class define rules how could be build the ships field
 * The ships can not touch each other.
 * </p>
 *
 */
public class Paper {
	Map<Integer, Water> gameField;
	
	Ship battleShip;
	Ship cruiser;
	Ship destroyer;
	Ship gunboat;
	public Paper() {
		gameField = new HashMap<Integer, Water>();
		//makeNewField();
	}
	
	void makeNewField(){
		createClearField();
		
		//add battleship into the game
		battleShip = new BattleShip();
		while(!cheackPosition(battleShip)){
			battleShip = new BattleShip();
		}
		addShip(battleShip);
		setGreyWater(battleShip);
		cruiser = new Cruiser();
		for(int i = 0; i < 2; i++){
			while(!cheackPosition(cruiser)){
				cruiser = new Cruiser();
			}
			addShip(cruiser);
			setGreyWater(cruiser);
		}
		destroyer = new Destroyer();
		for(int i = 0; i < 3; i++){
			while(!cheackPosition(destroyer)){
				destroyer = new Destroyer();
			}
			addShip(destroyer);
			setGreyWater(destroyer);
		}
		gunboat = new GunBoat();
		for(int i = 0; i < 4; i++){
			while(!cheackPosition(gunboat)){
				gunboat = new GunBoat();
			}
			addShip(gunboat);
			setGreyWater(gunboat);
		}
	}
	
	boolean cheackPosition(Ship ship){
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
	
	void createClearField(){
		for(int i = 0; i < 10; i++){
			for(int j = 0; j< 10; j++){
				gameField.put(i*10 + j, new Water(i, j));
			}
			
		}
	}
	
	public Map<Integer, Water> getGameField(){
		makeNewField();
		return gameField;
	}
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
	
	public List<Water> mapToList(Map<Integer, Water> map){
		List<Water> water = new ArrayList<Water>();
		for(int i = 0; i < 10; i++){
			for(int j = 0; j < 10; j++){
				water.add(i * 10 + j, map.get(i * 10 + j));
			}
		}
		return water;
	}
	
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
	
	public Map<Integer, Water> placeShips(List<Coordinats> coor){
		Map<Integer, Water> gameField = new HashMap<>();
		for(int i = 0; i < 10; i++){
			for(int j = 0; j < 10; j++){
				gameField.put(i * 10 + j, new Water(i, j));
			}
		}
		Iterator<Coordinats> iter = coor.iterator();
		while(iter.hasNext()){
			Coordinats coordinata = iter.next();
			Water water = gameField.get(coordinata.getX() * 10 + coordinata.getY());
			water.setDangerWater(1);
			gameField.put(coordinata.getX() * 10 + coordinata.getY(), water);
		}
		return gameField;
	}
	
}
