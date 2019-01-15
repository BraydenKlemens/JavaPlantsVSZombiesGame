package com.omstead.l14pvz;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Pea {
	
	//variables
	private Sprite sprite;
	private int type;

	/**
	 * @param type
	 * @param x
	 * @param y
	 */
	public Pea(int type, float x, float y) {
		this.type = type;
		switch (type) {
		case 0:
			sprite = new Sprite(new Texture("Plants vs Zombies/greenBulletg.png"));
			break;
		case 1:
			sprite = new Sprite(new Texture("Plants vs Zombies/blueBullet.png"));
			break;
		default:
			sprite = new Sprite(new Texture("Plants vs Zombies/greenBullet.png"));
			break;
		}

		sprite.setX(x);
		sprite.setY(y);
	}
	
	/**
	 * @return
	 */
	public int getType(){
		return type;
	}

	/**
	 * @param f
	 * @return
	 */
	public float translateX(float f) {
		sprite.translateX(f);
		return f;
	}

	/**
	 * @return
	 */
	public Rectangle getBoundingRectangle() {
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
	public void draw(SpriteBatch batch) {
		sprite.draw(batch);

	}

}
