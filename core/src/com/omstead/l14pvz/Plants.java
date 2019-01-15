package com.omstead.l14pvz;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public abstract class Plants {

	// variables
	private Sprite sprite;
	private int health;
	private long time;

	/**
	 * @param tile
	 * @param text
	 * @param health
	 */
	public Plants(Tile tile, Texture text, int health) {
		sprite = new Sprite(text);
		sprite.setSize(tile.getRectangle().getWidth(), tile.getRectangle().getHeight());
		this.health = health;
		float x = tile.getRectangle().getX();
		float y = tile.getRectangle().getY();
		sprite.setPosition(x, y);
		startTime();

	}

	/**
	 * @return
	 */
	public long getTime() {
		return time;
	}

	/**
	 * @return
	 */
	public Rectangle getRectangle() {
		return sprite.getBoundingRectangle();
	}

	/**
	 * @return
	 */
	public float getX() {
		return sprite.getX();
	}

	/**
	 * @return
	 */
	public float getY() {
		return sprite.getY();
	}

	/**
	 * @param batch
	 */
	public void draw(Batch batch) {
		sprite.draw(batch);
	}

	public void startTime() {
		time = System.currentTimeMillis();
	}

	/**
	 * @return
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * @param health
	 */
	public void setHealth(int health) {
		this.health = health;
	}

	/**
	 * @param health
	 */
	public void damage(int health) {
		this.health -= health;
	}

	// abstract methods for plant types
	public abstract boolean updateTime();

	public abstract int getType();

}
