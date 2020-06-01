package hitproject.spacesheep.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import hitproject.spacesheep.controller.*;
import hitproject.spacesheep.view.*;

public class Star extends GameObject {
	private Handler handler;
	private int velY = 5;

	public Star(int x, int y, ID id, Handler handler, SpriteSheet ss) {
		super(x, y, id, ss);
		this.handler = handler;
	}

	@Override
	public void tick() {
		y += velY;
		for (int i = 0; i < handler.object.size(); i++) {
			if (y >= 495)
				handler.removeObject(this);
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillOval(x, y, 2, 15);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 2, 25);
	}
}
