package hitproject.spacesheep.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import hitproject.spacesheep.controller.*;

public class MenuWindow {
	public JLabel lbImage;
	public JFrame frame;
	static int numOfGames = 0;
	private Game game = null;

	public MenuWindow(int width, int height, String title) {
		File file = new File("HighScoreFile.txt");
		try {
			if (!file.exists()) {
				file.createNewFile();
				PrintWriter pw = null;
				try {
					pw = new PrintWriter(file);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}

				pw.println(0);
				pw.println("Sheep1");
				pw.println(0);
				pw.println("Sheep2");
				pw.println(0);
				pw.println("Sheep3");
				pw.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		frame = new JFrame();
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setBounds(170, 90, width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setVisible(true);

		JButton btnNewGame = new JButton("new game");
		btnNewGame.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {

				Game.score = 0;
				Game.pause = false;
				game = Game.getInstance();
				game.gameOver = false;
				game.retryAll();
				game.callKeyInput();
				Game.window.frame.setVisible(true);
				frame.setVisible(false);
				frame.dispose();
			}
		});

		btnNewGame.setBounds(width / 2 - 100, height / 2 - 50, 200, 100);

		btnNewGame.setContentAreaFilled(false);
		btnNewGame.setBorder(new RoundedBorder(40));
		btnNewGame.setBackground(Color.lightGray);
		btnNewGame.setForeground(Color.white);
		btnNewGame.setFont(new Font("Start Game", Font.PLAIN, 20));
		frame.getContentPane().add(btnNewGame);

		JButton highScoreBtn = new JButton("HighScore");

		highScoreBtn.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				new HighScorePage(width, 563, "HighScore");

			}
		});
		highScoreBtn.setBounds(width / 2 - 100, height / 2 + 90, 200, 100);
		highScoreBtn.setContentAreaFilled(false);
		highScoreBtn.setBorder(new RoundedBorder(40));
		highScoreBtn.setBackground(Color.lightGray);
		highScoreBtn.setForeground(Color.white);
		highScoreBtn.setFont(new Font("HighScore", Font.PLAIN, 20));
		frame.getContentPane().add(highScoreBtn);

		JLabel lblSpaceSheep = new JLabel("Space Sheep");
		lblSpaceSheep.setBackground(Color.GREEN);
		lblSpaceSheep.setForeground(Color.LIGHT_GRAY);
		lblSpaceSheep.setBounds(width / 2 - 120, height / 2 - 300, 300, 200);

		lblSpaceSheep.setFont(new Font("Space Sheep", Font.PLAIN, 40));
		frame.getContentPane().add(lblSpaceSheep);

		lbImage = new JLabel("");
		lbImage.setIcon(new ImageIcon("res//cartoon_image.png"));
		lbImage.setBounds(0, 0, width, height);
		frame.getContentPane().add(lbImage);
	}

	public static void openGameOver() {
		new GameOver(800, 400, "Game Over");
	}
}
