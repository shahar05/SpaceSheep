package hitproject.spacesheep.view;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import hitproject.spacesheep.controller.*;
import hitproject.spacesheep.model.*;

public class Sheep extends GameObject {

	Handler handler;
	Game game;
	Boolean SAflag = false;
	private BufferedImage sheep_image;
	private static Sheep sheep = null;

	private Sheep(int x, int y, ID id, Handler handler, Game game, SpriteSheet ss) {
		super(x, y, id, ss);
		this.handler = handler;
		this.game = game;
		sheep_image = ss.grabImage(1, 1, 32, 48);

	}

	public static Sheep getInstance(int x, int y, ID id, Handler handler, Game game, SpriteSheet ss) {
		if (sheep == null)
			sheep = new Sheep(x, y, id, handler, game, ss);
		return sheep;
	}

	@Override
	public void tick() {

		if (!Game.pause) {

			x += velX;
			y += velY;

			collision();

			if (handler.getUp())
				velY = -5;
			else if (!handler.getDown())
				velY = 0;

			if (handler.getDown())
				velY = 5;
			else if (!handler.getUp())
				velY = 0;

			if (handler.getRight())
				velX = 5;
			else if (!handler.getLeft())
				velX = 0;

			if (handler.getLeft())
				velX = -5;
			else if (!handler.getRight())
				velX = 0;

			outOfBoundary();

			if (game.superAmmo <= 0) {
				SAflag = false;
				game.superAmmo = 0;
			}

			else {
				SAflag = true;
			}

			if (handler.getSpace() && !SAflag) {

				for (int i = 0; i < handler.object.size(); i++) {

					GameObject tempObject = handler.object.get(i);

					if (tempObject.getId() == ID.Player) {
						handler.addObject(new Bullet(tempObject.getX() + 10, tempObject.getY() + 5, ID.Bullet, handler,
								0, 0, ss));

						handler.setSpace(false);
					}

				}
			}

			if (handler.getSpace() && SAflag) {

				for (int i = 0; i < handler.object.size(); i++) {

					GameObject tempObject = handler.object.get(i);

					if (tempObject.getId() == ID.Player && game.superAmmo >= 1) {

						handler.addObject(new Bullet(tempObject.getX() + 10, tempObject.getY() + 5, ID.Bullet, handler,
								0, 0, ss));
						handler.addObject(new Bullet(tempObject.getX() + 10, tempObject.getY() + 5, ID.Bullet, handler,
								2, 0, ss));
						handler.addObject(new Bullet(tempObject.getX() + 10, tempObject.getY() + 5, ID.Bullet, handler,
								3, 0, ss));
						game.superAmmo--;
						handler.setSpace(false);
					}

				}
			}

		}
	}

	public Boolean getFlag() {
		return SAflag;
	}

	public void setFlag(Boolean flag) {
		this.SAflag = flag;
	}

	public void outOfBoundary() {
		if (y > 490)
			y = 490;
		if (y <= 0)
			y = 0;
		if (x <= 0)
			x = 0;
		if (x >= 980)
			x = 980;
	}

	private void collision() {

		for (int i = 0; i < handler.object.size(); i++) {

			GameObject tempObject = handler.object.get(i);

			if (tempObject.getId() == ID.Crate)
				if (getBounds().intersects(tempObject.getBounds())) {
					game.superAmmo += 50;
					handler.removeObject(tempObject);
				}
			if (tempObject.getId() == ID.MissleShooter) {
				if (getBounds().intersects(tempObject.getBounds())) {
					game.hp -= 5;

					if (game.hp <= 0)
						game.hp = 0;
				}
			}
			if (tempObject.getId() == ID.Missile) {
				if (getBounds().intersects(tempObject.getBounds())) {
					game.hp -= 20;
					handler.removeObject(tempObject);
					if (game.hp <= 0)
						game.hp = 0;
				}
			}

			if (tempObject.getId() == ID.EnemyBullet)
				if (getBounds().intersects(tempObject.getBounds())) {
					game.hp -= 10;
					handler.removeObject(tempObject);
					if (game.hp <= 0)
						game.hp = 0;
				}

			if (tempObject.getId() == ID.Enemy || tempObject.getId() == ID.ShooterEnemy)
				if (getBounds().intersects(tempObject.getBounds())) {
					game.hp--;
					if (game.hp <= 0)
						game.hp = 0;
				}
			if (tempObject.getId() == ID.Heart)
				if (getBounds().intersects(tempObject.getBounds())) {
					if ((game.hp + 25) >= 100)
						game.hp = 100;
					else
						game.hp += 25;
					handler.removeObject(tempObject);
				}
		}

	}

	@Override
	public void render(Graphics g) {
		g.drawImage(sheep_image, x, y, null);

	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 48);
	}
}
