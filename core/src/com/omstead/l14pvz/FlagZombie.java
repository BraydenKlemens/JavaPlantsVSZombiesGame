package com.omstead.l14pvz;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class FlagZombie implements IShootable {

	// variables
	private Sprite sprite;
	private int health = 300;
	private float speed = -0.2f;

	/**
	 * @param text
	 * @param x
	 * @param y
	 * @param tile
	 */
	public FlagZombie(Texture text, float x, float y, Tile tile) {
		sprite = new Sprite(text);
		sprite.setSize(tile.getRectangle().getWidth(), tile.getRectangle().getHeight() + 10);
		sprite.setX(x);
		sprite.setY(y);
	}

	// All Implementations of IShootable - straight forward headers
	@Override
	public void advance() {
		sprite.translateX(speed);
	}

	@Override
	public int damage(int d) {
		health -= d;
		return health;
	}

	@Override
	public Rectangle getBoundingRectangle() {
		return sprite.getBoundingRectangle();
	}

	@Override
	public int getHealth() {
		return health;
	}

	@Override
	public void draw(SpriteBatch batch) {
		sprite.draw(batch);
	}

	@Override
	public float setSpeed(float speed) {
		return this.speed = speed;
	}

	@Override
	public float getX() {
		return sprite.getX();

	}

}
