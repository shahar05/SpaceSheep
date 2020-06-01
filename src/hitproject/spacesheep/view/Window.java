package hitproject.spacesheep.view;

import java.awt.Dimension;

import javax.swing.JFrame;

import hitproject.spacesheep.controller.*;


public class Window {
	JFrame frame;
	Game game;

	public Window(int width, int height, String title, Game game) {
		this.game = game;
		frame = new JFrame(title);
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		frame.add(game);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public void stopGame() {
		frame.setVisible(false);
	}
}
