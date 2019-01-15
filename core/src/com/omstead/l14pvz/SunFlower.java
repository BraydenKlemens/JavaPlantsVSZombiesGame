package com.omstead.l14pvz;

import com.badlogic.gdx.graphics.Texture;

public class SunFlower extends Plants {

	/**
	 * @param tile
	 */
	public SunFlower (Tile tile) {
		super(tile, new Texture("Plants vs Zombies/sunFlower.png"), 100);
	}
	//abstract methods
	
	public boolean updateTime() {
		long timeA = System.currentTimeMillis() - getTime();
		if (timeA > 25000) {
			startTime();
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int getType() {
		return 2;
	}
	

}
