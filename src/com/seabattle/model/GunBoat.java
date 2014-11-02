package com.seabattle.model;

import java.util.Random;
/***
 * 
 * @author Alexey Konyshev
 * <h1>Gunboat</h1>
 * The model of ship. Occupies one cell on the field.
 *
 */
public class GunBoat extends Ship{
	private String shipName;
	
	Random rnd;
	public GunBoat() {
		shipName = "Gunboat";
		rnd = new Random();
		lengthOfShip = 1;
		x = rnd.nextInt(10);
	    y = rnd.nextInt(10);
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
		GunBoat other = (GunBoat) obj;
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
