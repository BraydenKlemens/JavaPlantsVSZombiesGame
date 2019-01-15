package com.omstead.l14pvz;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public interface IShootable {

	public void advance();

	/**
	 * @param d
	 * @return
	 */
	public int damage(int d);

	/**
	 * @param batch
	 */
	public void draw(SpriteBatch batch);

	/**
	 * @return
	 */
	public Rectangle getBoundingRectangle();

	/**
	 * @return
	 */
	public int getHealth();

	/**
	 * @param speed
	 * @return
	 */
	public float setSpeed(float speed);

	public float getX();
}
