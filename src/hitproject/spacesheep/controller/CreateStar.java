package hitproject.spacesheep.controller;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;
import hitproject.spacesheep.view.*;
import hitproject.spacesheep.model.*;

public class CreateStar extends GameObject {
	private Game game;
	private Handler handler;
	private Random r = new Random();
	private int rStar = 4;
	private int rEnemy = 200;
	private int rShooterEnemy = 500;
	private int rHeart = 1400;
	private int rCrate = 1000;
	private int velX = 73;
	private int rMissle = 1500;
	private int velKamikaza = 2;
	private int scoreLevel = 1000;

	public CreateStar(int x, int y, ID id, Handler handler, Game game, SpriteSheet ss) {
		super(x, y, id, ss);
		this.game = game;
		this.handler = handler;

	}

	
	
	
	@Override
	public void tick() {
	
		if (!Game.pause) {
			x += velX;
			if (Game.score > scoreLevel) {
				scoreLevel += 400;
				rEnemy -= 10;
				if (rEnemy <= 30)
					rEnemy = 30;
				rShooterEnemy -= 50;
				if (rShooterEnemy <= 100)
					rShooterEnemy = 100;
				rHeart -= 80;
				if (rHeart <= 550)
					rHeart = 550;
				rCrate -= 75;
				if (rCrate <= 200)
					rCrate = 200;
				if (rMissle <= 550)
					rMissle = 550;
				rMissle -= 75;
				velKamikaza++;
				if (velKamikaza >= 12)
					velKamikaza = 12;
			}

			if (x <= 32) {
				x = 32;
				velX = 73;
			}

			if (x >= 970) {
				x = 970;
				velX = -73;
			}

			if (r.nextInt(rMissle) == 0) {
				handler.addObject(new MissleShooter(x, y, ID.MissleShooter, handler, game, ss));
			}

			if (r.nextInt(rStar) == 0) {
				handler.addObject(new Star(x, y, ID.Star, handler, ss));

			}
			if (r.nextInt(rEnemy) == 0) {
				handler.addObject(new Enemy(x, y, ID.Enemy, handler, game, ss, velKamikaza));

			}
			if (r.nextInt(rCrate) == 0) {
				handler.addObject(new Crate(x, y, ID.Crate, handler, ss));
			}
			if (r.nextInt(rHeart) == 0) {
				handler.addObject(new Heart(x, y, ID.Heart, handler, ss));
			}
			if (r.nextInt(rShooterEnemy) == 0) {
				handler.addObject(new ShooterEnemy(x, y, ID.ShooterEnemy, handler, game, ss));
			}
		}
	}

	@Override
	public void render(Graphics g) {
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 2, 19);
	}
/********* Getters & setters *********/
	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public Random getR() {
		return r;
	}

	public void setR(Random r) {
		this.r = r;
	}

	public int getrStar() {
		return rStar;
	}

	public void setrStar(int rStar) {
		this.rStar = rStar;
	}

	public int getrEnemy() {
		return rEnemy;
	}

	public void setrEnemy(int rEnemy) {
		this.rEnemy = rEnemy;
	}

	public int getrShooterEnemy() {
		return rShooterEnemy;
	}

	public void setrShooterEnemy(int rShooterEnemy) {
		this.rShooterEnemy = rShooterEnemy;
	}

	public int getrHeart() {
		return rHeart;
	}

	public void setrHeart(int rHeart) {
		this.rHeart = rHeart;
	}

	public int getrCrate() {
		return rCrate;
	}

	public void setrCrate(int rCrate) {
		this.rCrate = rCrate;
	}

	public float getVelX() {
		return velX;
	}

	public void setVelX(int velX) {
		this.velX = velX;
	}

	public int getrMissle() {
		return rMissle;
	}

	public void setrMissle(int rMissle) {
		this.rMissle = rMissle;
	}

	public int getVelKamikaza() {
		return velKamikaza;
	}

	public void setVelKamikaza(int velKamikaza) {
		this.velKamikaza = velKamikaza;
	}

	public int getScoreLevel() {
		return scoreLevel;
	}

	public void setScoreLevel(int scoreLevel) {
		this.scoreLevel = scoreLevel;
	}
/*********End of Getters & setters *********/
}