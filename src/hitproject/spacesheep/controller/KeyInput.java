package hitproject.spacesheep.controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import hitproject.spacesheep.model.*;
import hitproject.spacesheep.view.*;

public class KeyInput extends KeyAdapter {
	Handler handler;
	Game game;

	public KeyInput(Handler handler, Game game) {
		this.handler = handler;
		this.game = game;
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if ((key == KeyEvent.VK_ENTER) && game.gameOver) {
			new GameOver(800, 400, "GameOver");
			game.retryAll();
		} else if (!game.gameOver && !Game.pause && game.hp > 0) {
			for (int i = 0; i < handler.object.size(); i++) {
				GameObject tempObject = handler.object.get(i);
				if (tempObject.id == ID.Player && !game.gameOver && !Game.pause) {
					if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP)
						handler.setUp(true);
					if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN)
						handler.setDown(true);
					if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT)
						handler.setLeft(true);
					if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT)
						handler.setRight(true);
					if (key == KeyEvent.VK_SPACE)
						handler.setSpace(true);
				}
			}
		} else {
			if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP)
				handler.setUp(false);
			if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN)
				handler.setDown(false);
			if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT)
				handler.setLeft(false);
			if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT)
				handler.setRight(false);
			else {
				handler.setUp(false);
				handler.setDown(false);
				handler.setLeft(false);
				handler.setRight(false);
			}
		}
	}

	public void keyReleased(KeyEvent e) {

		int key = e.getKeyCode();

		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.id == ID.Player && !game.gameOver) {
				if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP)
					handler.setUp(false);
				if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN)
					handler.setDown(false);
				if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT)
					handler.setLeft(false);
				if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT)
					handler.setRight(false);
				if (key == KeyEvent.VK_SPACE) {
					handler.setSpace(false);
				}
			}
		}
	}
}
