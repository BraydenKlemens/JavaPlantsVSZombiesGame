package com.omstead.l14pvz;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class Sun {

	// variables
	private Sprite sprite;
	private boolean direction;

	/**
	 * @param text
	 * @param direction
	 * @param x
	 * @param y
	 */
	public Sun(Texture text, boolean direction, float x, float y) {
		this.direction = direction;
		sprite = new Sprite(text);
		sprite.setX(x);
		sprite.setY(y);
	}

	/**
	 * @return
	 */
	public boolean isDirection() {
		return direction;
	}

	/**
	 * @return
	 */
	public float getY() {
		return sprite.getY();
	}

	/**
	 * @param y
	 */
	public void setY(float y) {
		sprite.setY(y);
	}

	/**
	 * @param f
	 */
	public void translateY(float f) {
		sprite.translateY(f);
	}

	/**
	 * @return
	 */
	public Rectangle getRectangle() {
		return sprite.getBoundingRectangle();
	}

	/**
	 * @param batch
	 */
	public void draw(Batch batch) {
		sprite.draw(batch);
	}

}
