package hitproject.spacesheep.model;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;
import hitproject.spacesheep.controller.*;
import hitproject.spacesheep.view.*;

public class ShooterEnemy extends GameObject {
	Game game;
	Handler handler;
	Random r = new Random();
	int choose = 0;
	int hp = 100;
	private BufferedImage enemy_image;
	int swing = 150;
	boolean flag = true;

	public ShooterEnemy(int x, int y, ID id, Handler handler, Game game, SpriteSheet ss) {
		super(x, y, id, ss);
		this.handler = handler;
		this.game = game;
		enemy_image = ss.grabImage(2, 1, 32, 48);
		velX = 2;
	}

	@Override
	public void tick() {
		if (!Game.pause) {
			y += 1;
			swing--;

			if (swing != 0) {
				if (flag) {
					x += velX;
					if (x >= 980)
						x = 980;
				} else {
					x -= velX;
					if (x <= 0)
						x = 0;
				}

			} else {
				swing = 150;
				flag = !flag;
			}
			choose = r.nextInt(100);
			if (choose == 0)
				handler.addObject(new Bullet(this.getX() + 5, this.getY() + 60, ID.EnemyBullet, handler, 1, 0, ss));

			for (int i = 0; i < handler.object.size(); i++) {
				GameObject tempObject = handler.object.get(i);

				if (tempObject.getId() == ID.Bullet) {
					if (getBounds().intersects(tempObject.getBounds())) {
						hp -= 25;
						handler.removeObject(tempObject);
					}
				}
			}
			if (hp <= 0) {
				handler.removeObject(this);
				game.score += 200;
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
}
