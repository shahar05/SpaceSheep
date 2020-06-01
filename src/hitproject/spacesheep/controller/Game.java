package hitproject.spacesheep.controller;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import hitproject.spacesheep.model.*;
import hitproject.spacesheep.view.*;

public class Game extends Canvas implements Runnable {
	public static boolean pause = false;
	public static Window window;
	// Serial ID for No warning
	private static final long serialVersionUID = 1L;

	private Boolean isRunning = false;
	private Thread thread;
	private Handler handler;
	private Camera camera;
	private SpriteSheet ss;
	private static Game game = null;
	private KeyInput keyInput;
	private BufferedImage floor = null;
	private BufferedImage floor2 = null;
	private BufferedImage level = null;
	private BufferedImage sprite_sheet = null;
	public int hp = 100;
	public int superAmmo = 100;
	public static int score = 0;
	public boolean gameOver = false;

	/*
	 * Name:Game Constructor Desc: Initialize all Images Initialize Array Off All
	 * Object Input: Output:
	 */
	private Game() {
		window = new Window(1024, 563, "Space Sheep", this);
		start();
		handler = new Handler();
		camera = new Camera(0, 0);
		callKeyInput();
		BufferImageLoader loader = new BufferImageLoader();
		level = loader.loadImage("/Wizard_level.png");
		sprite_sheet = loader.loadImage("/sprite_sheet.png");
		ss = new SpriteSheet(sprite_sheet);
		floor = ss.grabImage(4, 2, 32, 32);
		floor2 = ss.grabImage(5, 2, 32, 32);
		this.addMouseListener(new MouseInput(handler, camera, this, ss));
		loadLevel(level);

	}

	
	// singleton Design Pattern For Game Which Create only once And Recycle It!!
	public static Game getInstance() {
		if (game == null)
			game = new Game();
		return game;
	}

	
	
	private void start() {
		isRunning = true;
		thread = new Thread(this);
		thread.start();

	}

	public void stop() {
		isRunning = false;

		try {
			thread.join();
		} catch (InterruptedException e) {

			e.printStackTrace();
		}

	}

	/*
	 * Name: The Timer Logic And Frame While Game is Runnig Keep This Logic 
	 */
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while (isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				if (!pause)
					tick();
				delta--;
			}

			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frames = 0;
			}
		}
		stop();
	}

	/**
	 * Method invoked at every game tick. It updates all game objects first. Then it
	 * adds a bullet if the player is firing. Afterwards it checks all objects for
	 * collisions and removes the destroyed objects. Finally the game tick counter
	 * is updated and a new Object is spawn upon every time is generate a number
	 * that equal to zero
	 */
	public void tick() {

		if (!gameOver) {
			for (int i = 0; i < handler.object.size(); i++) {

				if (handler.object.get(i).getId() == ID.Player)
					camera.tick(handler.object.get(i));
			}
			handler.tick();

			if (hp <= 0) {
				Game.pause = true;
				gameOver = true;
			}
		}
	}
	
	// Method invoked at every game frame. It display all game objects on the SCREEN

	public void render() {

		if (!gameOver) {
			BufferStrategy bs = this.getBufferStrategy();
			if (bs == null) {
				this.createBufferStrategy(3);
				return;
			}

			Graphics g = bs.getDrawGraphics();
			Graphics2D g2d = (Graphics2D) g;

			g2d.translate(-camera.getX(), -camera.getY());// Before

			for (int xx = 0; xx < 32 * 32; xx += 32) {
				for (int yy = 0; yy < 18 * 32; yy += 32) {

					if (false || (xx == 32 * 2 && yy == 32 * 15) || (xx == 32 * 5 && yy == 32 * 17)
							|| (xx == 32 * 23 && yy == 32 * 11) || (xx == 32 * 13 && yy == 32 * 6)
							|| (xx == 32 * 27 && yy == 32 * 8) || (xx == 32 * 12 && yy == 32 * 12)
							|| (xx == 32 * 5 && yy == 32 * 5) || (xx == 32 * 15 && yy == 32 * 4)
							|| (xx == 32 * 7 && yy == 32 * 6 || (xx == 32 * 4 && yy == 32 * 10)
									|| (xx == 32 * 10 && yy == 32 * 3) || (xx == 32 * 20 && yy == 32 * 7)
									|| (xx == 32 * 23 && yy == 32 * 5) || (xx == 32 * 27 && yy == 32 * 2)
									|| (xx == 32 * 25 && yy == 32 * 1) || (xx == 32 * 22 && yy == 32 * 3))) {
						g.drawImage(floor, xx, yy, null);
					} else {
						g.drawImage(floor2, xx, yy, null);
					}
				}
			}
			handler.render(g);// Between Getting Translated

			g2d.translate(camera.getX(), camera.getY());// After

			g.setColor(Color.DARK_GRAY);
			g.fillOval(210, 5, 35, 35);
			g.setColor(Color.white);
			g.fillRect(220, 12, 5, 20);
			g.fillRect(230, 12, 5, 20);
			g.setColor(Color.gray);
			g.fillRect(5, 5, 200, 32);
			g.setColor(Color.green);
			g.fillRect(5, 5, hp * 2, 32);
			g.setColor(Color.black);
			g.drawRect(5, 5, 200, 32);

			g.setColor(Color.white);
			g.drawString("Super Ammo: " + superAmmo, 5, 50);

			g.setColor(Color.white);
			g.drawString("score: " + score, 5, 65);

			//////////////////////

			g.dispose();
			bs.show();

		} else {

			BufferStrategy bs = this.getBufferStrategy();
			if (bs == null) {
				this.createBufferStrategy(3);
				return;
			}

			Graphics g = bs.getDrawGraphics();

			g.setColor(Color.red);
			g.setFont(new Font("Game Over", Font.PLAIN, 100));
			g.drawString("Game Over", 200, 150);

			g.setFont(new Font("Press Enter", Font.PLAIN, 60));
			g.drawString("Press Enter", 220, 250);

			g.dispose();
			bs.show();
		}

	}

	
	// Attach Key Listener Input When Game is Over
	public void callKeyInput() {
		keyInput = new KeyInput(handler, this);
		this.addKeyListener(keyInput);
	}

	// Retry All position Of ObejctGame When Restart The Game
	public void retryAll() {
		handler.object.clear();
		loadLevel(level);
		this.removeKeyListener(keyInput);
		superAmmo = 100;
		for (int i = 0; i < handler.object.size(); i++) {

			GameObject tempObject = handler.object.get(i);

			if (tempObject.getId() == ID.Player) {
				hp = 100;
				tempObject.setX(32 * 15);
				tempObject.setY(400);
			if(tempObject.getId() == ID.CreateStar) {
				 CreateStar cs = (CreateStar)tempObject;
				int	rEnemy = 200,
					rShooterEnemy = 500,
					rHeart = 1400,
					rCrate = 1000,
					rMissle = 1500,
					scoreLevel = 1000;
				cs.setrCrate(rCrate);
				cs.setrEnemy(rEnemy);
				cs.setrHeart(rHeart);
				cs.setrShooterEnemy(rShooterEnemy);
				cs.setrMissle(rMissle);
				cs.setScoreLevel(scoreLevel);
				
			}
			}
		}

	}

	

	// ======== Loading The Level ========

	private void loadLevel(BufferedImage image) {

		int w = image.getWidth();
		int h = image.getHeight();

		for (int xx = 0; xx < w; xx++) {
			for (int yy = 0; yy < h; yy++) {

				int pixel = image.getRGB(xx, yy);
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;

				if (blue == 255 && green == 0)
					handler.addObject(Sheep.getInstance(xx * 32, yy * 32, ID.Player, handler, this, ss));
				if (green == 255 & blue == 0)
					handler.addObject(new Enemy(xx * 32, yy * 32, ID.Enemy, handler, this, ss, 2));
				if (green == 255 && blue == 255)
					handler.addObject(new CreateStar(xx * 32, yy * 32, ID.CreateStar, handler, this, ss));

			}
		}

	}

	public static boolean isPause() {
		return pause;
	}

	/********* Getters & setters *********/
	public static void setPause(boolean pause) {
		Game.pause = pause;
	}

	public static Window getWindow() {
		return window;
	}

	public static void setWindow(Window window) {
		Game.window = window;
	}

	public Boolean getIsRunning() {
		return isRunning;
	}

	public void setIsRunning(Boolean isRunning) {
		this.isRunning = isRunning;
	}

	public Thread getThread() {
		return thread;
	}

	public void setThread(Thread thread) {
		this.thread = thread;
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public Camera getCamera() {
		return camera;
	}

	public void setCamera(Camera camera) {
		this.camera = camera;
	}

	public SpriteSheet getSs() {
		return ss;
	}

	public void setSs(SpriteSheet ss) {
		this.ss = ss;
	}

	public static Game getGame() {
		return game;
	}

	public static void setGame(Game game) {
		Game.game = game;
	}

	public KeyInput getKeyInput() {
		return keyInput;
	}

	public void setKeyInput(KeyInput keyInput) {
		this.keyInput = keyInput;
	}

	public BufferedImage getFloor() {
		return floor;
	}

	public void setFloor(BufferedImage floor) {
		this.floor = floor;
	}

	public BufferedImage getFloor2() {
		return floor2;
	}

	public void setFloor2(BufferedImage floor2) {
		this.floor2 = floor2;
	}

	public BufferedImage getLevel() {
		return level;
	}

	public void setLevel(BufferedImage level) {
		this.level = level;
	}

	public BufferedImage getSprite_sheet() {
		return sprite_sheet;
	}

	public void setSprite_sheet(BufferedImage sprite_sheet) {
		this.sprite_sheet = sprite_sheet;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getSuperAmmo() {
		return superAmmo;
	}

	public void setSuperAmmo(int superAmmo) {
		this.superAmmo = superAmmo;
	}

	public static int getScore() {
		return score;
	}

	public static void setScore(int score) {
		Game.score = score;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/********* End of Getters & setters *********/

	public boolean isGameOver() {
		return gameOver;
	}

	public static void main(String args[]) {
		MenuWindow window = new MenuWindow(1024, 540, "Space Ship");
		window.frame.setBounds(170, 90, 1024, 563);
	}
}
