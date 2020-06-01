package hitproject.spacesheep.model;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;
import hitproject.spacesheep.controller.*;
import hitproject.spacesheep.view.*;

public class Enemy extends GameObject {
	public	Game game;
	public	Handler handler;
	public	Random r = new Random();
	public	int choose = 0;
	protected	int hp = 100;
	protected int Velocity = 2;
	private BufferedImage enemy_image;

	public Enemy(int x, int y, ID id, Handler handler, Game game, SpriteSheet ss, int Velocity) {
		super(x, y, id, ss);
		this.handler = handler;
		this.game = game;
		this.Velocity = Velocity;
		enemy_image = ss.grabImage(4, 1, 32, 32);
	}

	@Override
	public void tick() {
		if (!Game.pause) {
			y += Velocity;
			choose = r.nextInt(10);

			for (int i = 0; i < handler.object.size(); i++) {
				GameObject tempObject = handler.object.get(i);

				if (tempObject.getId() == ID.Block) {
					if (getBoundsBig().intersects(tempObject.getBounds())) {
						handler.removeObject(tempObject);
					}
				}

				if (tempObject.getId() == ID.Bullet) {
					if (getBounds().intersects(tempObject.getBounds())) {
						hp -= 50;
						handler.removeObject(tempObject);
					}
				}
			}

			if (hp <= 0) {
				handler.removeObject(this);
				Game.score += 100;
			}
			if (y > 480)
				handler.removeObject(this);
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(enemy_image, x, y, null);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}

	public Rectangle getBoundsBig() {
		// ===== make bigger number for better collisions enemy =========
		return new Rectangle(x - 16, y - 16, 64, 64);
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getVelocity() {
		return Velocity;
	}

	public void setVelocity(int velocity) {
		Velocity = velocity;
	}
}
