package hitproject.spacesheep.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import hitproject.spacesheep.controller.*;

import hitproject.spacesheep.view.*;

public class Bullet extends GameObject {
	private Handler handler;

	public Bullet(int x, int y, ID id, Handler handler, int mx, int my, SpriteSheet ss) {
		super(x, y, id, ss);
		this.handler = handler;

		if (id == ID.Missile) {
			velY = (my - y) / 60;
			velX = (mx - x) / 60;

			if (velY > -3 && velY < 3) {
				if (velY < 0)
					velY -= 2;
				else {
					velY += 2;
				}
			}
			if (velX > -3 && velX < 3) {
				if (velX < 0)
					velX -= 2;
				else {
					velX += 2;
				}
			}

		} else {
			if (mx == 1) {
				velY = 4;
				velX = 0;
			} else if (mx == 0) {
				velY = -10;
				velX = 0;
			} else if (mx == 2) {
				velX = 2;
				velY = -10;
			} else if (mx == 3) {
				velX = -2;
				velY = -10;
			}
		}
	}

	@Override
	public void tick() {
		if (!Game.pause) {
			x += velX;
			y += velY;

			if (y < 0 || y > 500 || x < 0 || x > 1000)
				handler.removeObject(this);
		}
	}

	@Override
	public void render(Graphics g) {
		if (id == ID.Missile) {
			g.setColor(Color.cyan);
			g.fillOval(x, y, 12, 12);
		} else if (id == ID.EnemyBullet) {
			g.setColor(Color.red);
			g.fillOval(x, y, 16, 16);
		} else {
			g.setColor(Color.green);
			g.fillOval(x, y, 8, 8);
		}
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 8, 8);
	}
}
