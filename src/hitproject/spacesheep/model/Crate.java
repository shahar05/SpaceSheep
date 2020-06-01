package hitproject.spacesheep.model;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import hitproject.spacesheep.controller.*;
import hitproject.spacesheep.view.*;

public class Crate extends GameObject {
	private BufferedImage crate_image;
	private Handler handler;

	public Crate(int x, int y, ID id, Handler handler, SpriteSheet ss) {
		super(x, y, id, ss);
		this.handler = handler;
		crate_image = ss.grabImage(6, 1, 32, 32);
	}

	@Override
	public void tick() {
		if (!Game.pause) {
			y += 2;
			if (y >= 490)
				handler.removeObject(this);
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(crate_image, x, y, null);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}
}
