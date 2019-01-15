package com.omstead.l14pvz;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameLauncher extends Game {
	//launches game
	public SpriteBatch batch;

	@Override
	public void create() {
		batch = new SpriteBatch();
		setScreen(new Start(this));
	}

	@Override
	public void render() {
		super.render();
	}
}
