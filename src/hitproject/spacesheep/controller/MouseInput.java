package hitproject.spacesheep.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import hitproject.spacesheep.model.*;
import hitproject.spacesheep.view.*;

public class MouseInput extends MouseAdapter {
	private Handler handler;
	private Camera camera;
	private Game game;
	private SpriteSheet ss;

	public MouseInput(Handler handler, Camera camera, Game game, SpriteSheet ss) {
		this.handler = handler;
		this.camera = camera;
		this.game = game;
		this.ss = ss;
	}

	public void mousePressed(MouseEvent e) {
		int my = (int) (e.getY() + camera.getY());

		if (e.getX() > 200 && e.getX() < 250 && e.getY() < 50) {
			Game.pause = true;
			new QuitDialog(800, 480, "Pause", game);
		}

		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getId() == ID.Player) {
				handler.addObject(
						new Bullet(tempObject.getX() + 16, tempObject.getY() + 24, ID.Bullet, handler, 0, my, ss));
			}
		}
	}
}
