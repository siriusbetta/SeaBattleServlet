package com.seabattle.model;

import java.util.Random;
/***
 * 
 * @author Alexey Konyshev
 * <h1>BattleShip</h1>
 * <p>Model of ship
 * occupies 4 cell on the field
 * </p>
 */
public class BattleShip extends Ship{
	
	Random rnd;
	public BattleShip() {
		shipName = "Battleship";
		lengthOfShip = 4;
		rnd = new Random();
		
		this.x = rnd.nextInt(7);
	    this.y = rnd.nextInt(7);
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
		BattleShip other = (BattleShip) obj;
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
	
	public int getLength(){
		return lengthOfShip;
	}
	
}
