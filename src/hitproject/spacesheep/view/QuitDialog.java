package hitproject.spacesheep.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import hitproject.spacesheep.controller.*;

public class QuitDialog {

	public JLabel lbImage;
	public JFrame frame;
	private Game game;

	public QuitDialog(int width, int height, String title, Game game) {
		this.game = game;
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setBounds(275, 120, width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setVisible(true);

		JButton btnNewGame = new JButton("Play");
		btnNewGame.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				Game.pause = false;
				frame.setVisible(false); // you can't see me!
				frame.dispose();
			}
		});

		btnNewGame.setBounds(width - 300, height - 150, 200, 100);
		btnNewGame.setContentAreaFilled(false);
		btnNewGame.setBorder(new RoundedBorder(40));
		btnNewGame.setBackground(Color.lightGray);
		btnNewGame.setForeground(Color.white);
		btnNewGame.setFont(new Font("Play", Font.PLAIN, 20));
		frame.getContentPane().add(btnNewGame);

		JButton highScoreBtn = new JButton("Quit");

		highScoreBtn.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				Game.window.stopGame();
				game.retryAll();
				frame.setVisible(false);
				frame.dispose();
				new MenuWindow(1024, 540, "Space Ship");
			}
		});
		highScoreBtn.setBounds(100, height - 150, 200, 100);

		highScoreBtn.setContentAreaFilled(false);
		highScoreBtn.setBorder(new RoundedBorder(40));
		highScoreBtn.setBackground(Color.lightGray);
		highScoreBtn.setForeground(Color.white);
		highScoreBtn.setFont(new Font("HighScore", Font.PLAIN, 20));
		frame.getContentPane().add(highScoreBtn);

		JLabel lblSpaceSheep = new JLabel("Are You Sure");

		lblSpaceSheep.setForeground(Color.white);
		lblSpaceSheep.setBounds(width / 2 - 120, height / 2 - 270, 500, 200);

		lblSpaceSheep.setFont(new Font("Space Sheep", Font.PLAIN, 40));
		frame.getContentPane().add(lblSpaceSheep);

		lbImage = new JLabel("");
		lbImage.setIcon(new ImageIcon("res\\space_sheep_image.jpg"));
		lbImage.setBounds(0, 0, width, height);
		frame.getContentPane().add(lbImage);
	}
}
