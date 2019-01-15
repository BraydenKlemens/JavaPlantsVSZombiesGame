package com.omstead.l14pvz;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Mine extends Plants {

	/**
	 * @param tile
	 */
	public Mine(Tile tile) {
		super(tile, new Texture("Plants vs Zombies/minePlant.png"),30);
	}
	//abstract methods updateTime not needed
	@Override
	public boolean updateTime() {
		//not used
		return false;
	}

	@Override
	public int getType() {
		return 4;
	}

}
