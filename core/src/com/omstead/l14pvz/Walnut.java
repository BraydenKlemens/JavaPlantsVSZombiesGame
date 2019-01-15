package com.omstead.l14pvz;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Walnut extends Plants {

	/**
	 * @param tile
	 */
	public Walnut(Tile tile) {
		super(tile, new Texture("Plants vs Zombies/walnut.png"), 750);
	}

	// abstract methods
	@Override
	public boolean updateTime() {
		// not used
		return false;
	}

	@Override
	public int getType() {
		return 3;
	}

}
