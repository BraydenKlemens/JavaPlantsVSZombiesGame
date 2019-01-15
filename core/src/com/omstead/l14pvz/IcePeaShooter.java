package com.omstead.l14pvz;

import com.badlogic.gdx.graphics.Texture;

public class IcePeaShooter extends Plants {

	/**
	 * @param tile
	 */
	public IcePeaShooter(Tile tile) {
		super(tile, new Texture("Plants vs Zombies/icePeaShooter.png"), 300);
	}

	public boolean updateTime() {
		//Abstract method
		long timeA = System.currentTimeMillis() - getTime();
		if (timeA > 2000) {
			startTime();
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int getType() {
		return 1;
	}
	
}
