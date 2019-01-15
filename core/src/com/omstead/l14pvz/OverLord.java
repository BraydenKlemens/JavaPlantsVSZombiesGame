package com.omstead.l14pvz;

import java.util.ArrayList;
import java.util.Scanner;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class OverLord implements Screen, InputProcessor {
	
	//CONFIG.WIDTH = 800; (in the desktopLauncher)

	public GameLauncher game;

	// ArrayLists
	private ArrayList<Tile> grid = new ArrayList<Tile>();
	private ArrayList<Sun> suns = new ArrayList<Sun>();
	private ArrayList<Pea> bullets = new ArrayList<Pea>();
	private ArrayList<IShootable> zombies = new ArrayList<IShootable>();

	// Sprite
	private Sprite map;
	private Sprite peaIcon, iceIcon, walnutIcon, mineIcon, sunIcon, shovel;
	private Sun sun;

	// variables
	private int width = Gdx.graphics.getWidth();
	private int height = Gdx.graphics.getHeight();

	private long timeA, timeB;

	private int suncount = 50;
	private int type = -1;
	private int wave = 1;

	// Display
	private BitmapFont sunTotal = new BitmapFont();
	private BitmapFont waveTotal = new BitmapFont();
	private BitmapFont size = new BitmapFont();

	public OverLord(GameLauncher game) {
		this.game = game;
	}

	@Override
	public void show() {

		// icons
		peaIcon = new Sprite(new Texture("Plants vs Zombies/peaIcon.png"));
		peaIcon.setPosition(10, 420);
		iceIcon = new Sprite(new Texture("Plants vs Zombies/icePeaIcon.png"));
		iceIcon.setPosition(10, 370);
		walnutIcon = new Sprite(new Texture("Plants vs Zombies/WalnutIcon.png"));
		walnutIcon.setPosition(10, 320);
		sunIcon = new Sprite(new Texture("Plants vs Zombies/SunIcon.png"));
		sunIcon.setPosition(10, 270);
		mineIcon = new Sprite(new Texture("Plants vs Zombies/MineIcon.png"));
		mineIcon.setPosition(10, 220);
		shovel = new Sprite(new Texture("Plants vs Zombies/Shovel.jpg"));
		shovel.setPosition(10, 140);

		// Sprite Creation
		map = new Sprite(new Texture("Plants vs Zombies/PvZMap.jpg"));
		map.setSize(width, height);

		// grid set up
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 9; j++) {
				Tile location = new Tile(200 + j * 65, 25 + i * 80);
				grid.add(location);
			}
		}

		// timers
		timeA = System.currentTimeMillis();
		timeB = System.currentTimeMillis();

		// starts a wave of zombies
		startNewWave();

		// inputProccessor
		Gdx.input.setInputProcessor(this);

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		updateGameTimers();
		produceSuns();
		updateWave();
		movePea();
		updatePlantsAbilities();
		Collide();
		looseGame();
		draw();
	}

	public void looseGame() {
		// lose Game
		for (int i = 0; i < zombies.size(); i++) {
			if (zombies.get(i).getX() <= 180) {
				game.setScreen(new End(game));
			}
		}
	}

	public void produceSuns() {
		// Suns spawn from top of screen
		if (timeA > 10000) {
			timeA = 0;
			sun = new Sun(new Texture("Plants vs Zombies/suns.png"), true, (float) (Math.random() * 700), 450);
			suns.add(sun);
		}
		// if they true direction then float down
		for (int i = 0; i < suns.size(); i++) {
			if (suns.get(i).isDirection()) {
				suns.get(i).translateY(-1);
				if (suns.get(i).getY() <= 0) {
					suns.get(i).setY(0);
				}
			}
		}
	}

	public void updateWave() {
		// waves of zombies
		if (zombies.size() == 0) {
			wave += 1;
			startNewWave();
		}

		// updates zombie movement
		for (int i = 0; i < zombies.size(); i++) {
			zombies.get(i).advance();
		}

	}

	public void updatePlantsAbilities() {
		// plants use abilities
		for (int i = 0; i < grid.size(); i++) {
			if (grid.get(i).hasPlant()) {
				boolean fire = grid.get(i).getPlant().updateTime();
				if (fire) {
					if (grid.get(i).getPlant().getType() == 0) {
						addPea(0, grid.get(i).getPlant().getRectangle().getX() + 20,
								grid.get(i).getPlant().getRectangle().getY() + 30);
					} else if (grid.get(i).getPlant().getType() == 1) {
						addPea(1, grid.get(i).getPlant().getRectangle().getX() + 30,
								grid.get(i).getPlant().getRectangle().getY() + 35);
					} else if (grid.get(i).getPlant().getType() == 2) {
						addSun(grid.get(i));
					}
				}
			}
		}
	}

	public void updateGameTimers() {
		// timers
		timeA += System.currentTimeMillis() - timeB;
		timeB = System.currentTimeMillis();

		waveTotal.getData().setScale(2, 2);
		sunTotal.getData().setScale(2, 2);
	}

	public void draw() {
		game.batch.begin();

		// background
		map.draw(game.batch);
		peaIcon.draw(game.batch);
		iceIcon.draw(game.batch);
		walnutIcon.draw(game.batch);
		sunIcon.draw(game.batch);
		mineIcon.draw(game.batch);
		shovel.draw(game.batch);

		// grid
		for (int i = 0; i < grid.size(); i++) {
			grid.get(i).draw(game.batch);
		}

		// bullets
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).draw(game.batch);
		}
		// zombies
		for (int i = 0; i < zombies.size(); i++) {
			zombies.get(i).draw(game.batch);
		}

		// suns
		for (int i = 0; i < suns.size(); i++) {
			suns.get(i).draw(game.batch);
		}

		sunTotal.draw(game.batch, "Suns: " + suncount, 10, 130);
		waveTotal.draw(game.batch, "Wave: " + wave, 400, 470);

		game.batch.end();
	}

	public void startNewWave() {
		// adds 10 more zombies each wave
		for (int i = 0; i < 10 * wave; i++) {
			Tile tile = grid.get((int) (Math.random() * grid.size()));
			IShootable zombie1 = new Zombie(new Texture("Plants vs Zombies/zombie.png"),
					1000 + (float) Math.random() * 1500, tile.getY(), tile);
			IShootable zombie2 = new BucketZombie(new Texture("Plants vs Zombies/bucketHead.png"),
					1000 + (float) Math.random() * 1500, tile.getY(), tile);
			IShootable zombie3 = new FlagZombie(new Texture("Plants vs Zombies/pylonHead.png"),
					1000 + (float) Math.random() * 1500, tile.getY(), tile);
			int num = (int) (Math.random() * 3);
			if (num == 0) {
				zombies.add(zombie1);
			} else if (num == 1) {
				zombies.add(zombie2);
			} else if (num == 2) {
				zombies.add(zombie3);
			}
		}
	}

	public void Collide() {
		// bullets and zombies
		for (int i = 0; i < zombies.size(); i++) {
			Rectangle zomrect = zombies.get(i).getBoundingRectangle();
			for (int j = 0; j < bullets.size(); j++) {
				if (bullets.get(j).getType() == 1) {
					Rectangle bulrect = bullets.get(j).getBoundingRectangle();
					if (bulrect.overlaps(zomrect)) {
						zombies.get(i).setSpeed(-0.03f);
						zombies.get(i).damage(10);
						bullets.remove(j);
						if (zombies.get(i).getHealth() <= 0) {
							zombies.remove(i);
						}
					}
				} else {
					Rectangle rect = bullets.get(j).getBoundingRectangle();
					if (rect.overlaps(zomrect)) {
						zombies.get(i).damage(10);
						bullets.remove(j);
						if (zombies.get(i).getHealth() <= 0) {
							zombies.remove(i);
						}
					}
				}
			}
		}
		// zombies and plants + (mine - type 4 rectangle)
		for (int i = 0; i < zombies.size(); i++) {
			Rectangle zomrect = zombies.get(i).getBoundingRectangle();
			for (int j = 0; j < grid.size(); j++) {
				if (grid.get(j).hasPlant()) {
					if (grid.get(j).getPlant().getType() == 4) {
						Rectangle rect = grid.get(j).getPlant().getRectangle();
						if (zomrect.overlaps(rect)) {
							zombies.get(i).damage(200);
							grid.get(j).setPlant(null);
							if (zombies.get(i).getHealth() <= 0) {
								zombies.remove(i);
							}
						}
					} else {
						Rectangle rect = grid.get(j).getPlant().getRectangle();
						if (zomrect.overlaps(rect)) {
							grid.get(j).getPlant().damage(1);
							zombies.get(i).setSpeed(0);
							if (grid.get(j).getPlant().getHealth() <= 0) {
								zombies.get(i).setSpeed(-0.2f);
								grid.get(j).setPlant(null);
							}
						}
					}
				}
			}
		}
	}

	/**
	 * @param tile
	 */
	public void addSun(Tile tile) {
		// adds suns to tile location of sun flower
		Sun sun = new Sun(new Texture("Plants vs Zombies/suns.png"), false, tile.getRectangle().getX(),
				tile.getRectangle().getY());
		suns.add(sun);
	}

	/**
	 * @param type
	 * @param x
	 * @param y
	 */
	public void addPea(int type, float x, float y) {
		bullets.add(new Pea(type, x, y));
	}

	public void movePea() {
		// move bullets - delete if edge of lawn
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).translateX(5);
			if (bullets.get(i).getX() >= width - 40) {
				bullets.remove(i);
			}
		}
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// click suns add money
		for (int i = 0; i < suns.size(); i++) {
			Rectangle sun = suns.get(i).getRectangle();
			if (sun.contains(screenX, Gdx.graphics.getHeight() - screenY)) {
				suns.remove(i);
				suncount += 25;
				return true;
			}
		}

		// set type by clicking on the icons
		Rectangle pearect = peaIcon.getBoundingRectangle();
		Rectangle icerect = iceIcon.getBoundingRectangle();
		Rectangle walnutrect = walnutIcon.getBoundingRectangle();
		Rectangle sunrect = sunIcon.getBoundingRectangle();
		Rectangle minerect = mineIcon.getBoundingRectangle();
		Rectangle shovelrect = shovel.getBoundingRectangle();

		if (pearect.contains(screenX, Gdx.graphics.getHeight() - screenY)) {
			type = 0;
		} else if (icerect.contains(screenX, Gdx.graphics.getHeight() - screenY)) {
			type = 1;
		} else if (walnutrect.contains(screenX, Gdx.graphics.getHeight() - screenY)) {
			type = 2;
		} else if (sunrect.contains(screenX, Gdx.graphics.getHeight() - screenY)) {
			type = 3;
		} else if (minerect.contains(screenX, Gdx.graphics.getHeight() - screenY)) {
			type = 4;
		} else if (shovelrect.contains(screenX, Gdx.graphics.getHeight() - screenY)) {
			type = 5;
		}

		// set plants to tile locations using mouse under certain conditions
		// (type,sun count,hasPlant)
		for (int i = 0; i < grid.size(); i++) {
			if (grid.get(i).getRectangle().contains(screenX, Gdx.graphics.getHeight() - screenY)) {
				if (type == 0 && suncount >= 100 && grid.get(i).hasPlant() == false) {
					grid.get(i).setPlant(new PeaShooter(grid.get(i)));
					suncount -= 100;
				} else if (type == 1 && suncount >= 175 && grid.get(i).hasPlant() == false) {
					grid.get(i).setPlant(new IcePeaShooter(grid.get(i)));
					suncount -= 175;
				} else if (type == 3 && suncount >= 50 && grid.get(i).hasPlant() == false) {
					grid.get(i).setPlant(new SunFlower(grid.get(i)));
					suncount -= 50;
				} else if (type == 2 && suncount >= 50 && grid.get(i).hasPlant() == false) {
					grid.get(i).setPlant(new Walnut(grid.get(i)));
					suncount -= 50;
				} else if (type == 4 && suncount >= 25 && grid.get(i).hasPlant() == false) {
					grid.get(i).setPlant(new Mine(grid.get(i)));
					suncount -= 25;
				} else if (type == 5 && grid.get(i).hasPlant()) {
					grid.get(i).setPlant(null);
				}
			}
		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {

		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// shows grid selection red/blue if plants contains a tile or not
		for (int i = 0; i < grid.size(); i++) {
			if (grid.get(i).getRectangle().contains(screenX, Gdx.graphics.getHeight() - screenY)) {
				grid.get(i).setTile(new TileSelect(grid.get(i), new Texture("Plants vs Zombies/TransBlue.png")));
				if (grid.get(i).hasPlant()) {
					grid.get(i).setTile(new TileSelect(grid.get(i), new Texture("Plants vs Zombies/TransRed.png")));
				}
			} else {
				grid.get(i).setTile(null);
			}
		}
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
