package com.omstead.l14pvz;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class Tile {

	// variables
	private Plants plant;
	private TileSelect select;
	private int x, y;
	private Rectangle rect;

	/**
	 * @param x
	 * @param y
	 */
	public Tile(int x, int y) {
		this.x = x;
		this.y = y;
		this.rect = new Rectangle(x, y, 50, 60);
	}

	/**
	 * @return
	 */
	public int getY() {
		return y;
	}

	/**
	 * @return
	 */
	public Rectangle getRectangle() {
		return rect;
	}

	/**
	 * @return
	 */
	public Plants getPlant() {
		return plant;
	}

	/**
	 * @param plant
	 * @return
	 */
	public Plants setPlant(Plants plant) {
		return this.plant = plant;
	}

	/**
	 * @return
	 */
	public TileSelect getTile() {
		return select;
	}

	/**
	 * @param select
	 * @return
	 */
	public TileSelect setTile(TileSelect select) {
		return this.select = select;
	}

	/**
	 * @return
	 */
	public boolean hasPlant() {
		return plant != null;
	}

	/**
	 * @param batch
	 */
	public void draw(Batch batch) {
		if (plant != null) {
			plant.draw(batch);
		}
		if (select != null) {
			select.draw(batch);
		}
	}
}
