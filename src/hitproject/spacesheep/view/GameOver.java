package hitproject.spacesheep.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Integer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import hitproject.spacesheep.controller.*;

public class GameOver {
	JTextField textField;
	public JLabel lbImage;
	public JFrame frame;

	public GameOver(int width, int height, String title) {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setBounds(275, 120, width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setVisible(true);

		JButton highScoreBtn = new JButton("Submit");

		highScoreBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				if (textField.getText().toString().equals("")) {
					JOptionPane.showMessageDialog(null, "My Goodness, Type Your Name");
				} else {
					if (textField.getText().length() > 10) {
						JOptionPane.showMessageDialog(null, "Name contain 1 to 10 characters");
					} else {
						updtaeScore();

						frame.setVisible(false);
						frame.dispose();

						new MenuWindow(1024, 540, "Space Ship");
					}

				}
			}
		});
		highScoreBtn.setBounds(width / 2 - 110, height - 150, 200, 100);
		highScoreBtn.setContentAreaFilled(false);
		highScoreBtn.setBorder(new RoundedBorder(40));
		highScoreBtn.setBackground(Color.lightGray);
		highScoreBtn.setForeground(Color.white);
		highScoreBtn.setFont(new Font("HighScore", Font.PLAIN, 20));
		frame.getContentPane().add(highScoreBtn);

		JLabel lblSpaceSheep = new JLabel("Game Over");

		lblSpaceSheep.setForeground(Color.white);
		lblSpaceSheep.setBounds(width / 2 - 120, height / 2 - 270, 500, 200);

		lblSpaceSheep.setFont(new Font("Space Sheep", Font.PLAIN, 40));
		frame.getContentPane().add(lblSpaceSheep);

		JLabel scoreLb = new JLabel("your score is:" + Game.score);
		scoreLb.setForeground(Color.white);
		scoreLb.setBounds(width / 2 - 140, height / 2 - 200, 500, 200);
		scoreLb.setFont(new Font("Space Sheep", Font.PLAIN, 40));
		frame.getContentPane().add(scoreLb);

		JLabel nameLb = new JLabel("Name:");
		nameLb.setForeground(Color.white);
		nameLb.setBounds(width / 2 - 120, height - 220, 100, 30);
		nameLb.setFont(new Font("Space Sheep", Font.PLAIN, 20));
		frame.getContentPane().add(nameLb);

		textField = new JTextField();
		textField.setBounds(width / 2 - 50, height - 220, 150, 30);
		textField.setBackground(Color.cyan);
		frame.getContentPane().add(textField);

		lbImage = new JLabel("");
		lbImage.setIcon(new ImageIcon("res\\space_sheep_image.jpg"));
		lbImage.setBounds(0, 0, width, height);
		frame.getContentPane().add(lbImage);

		Game.window.stopGame();
	}

	private void updtaeScore() {
		String lines[] = new String[6];
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("HighScoreFile.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < 6; i++) {
			try {
				lines[i] = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		int a[] = new int[3];
		a[0] = letsConvert(lines[0]);
		a[1] = letsConvert(lines[2]);
		a[2] = letsConvert(lines[4]);

		for (int i = 0; i < 3; i++) {
			if (Game.score > a[i]) {
				File file = new File("HighScoreFile.txt");
				PrintWriter pw = null;
				try {
					pw = new PrintWriter(file);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				if (i == 0) {
					pw.println(Game.score);
					pw.println(textField.getText().toString());
					pw.println(lines[0]);
					pw.println(lines[1]);
					pw.println(lines[2]);
					pw.println(lines[3]);
					pw.close();
					i = 6;
				} else if (i == 1) {
					pw.println(lines[0]);
					pw.println(lines[1]);
					pw.println(Game.score);
					pw.println(textField.getText().toString());
					pw.println(lines[2]);
					pw.println(lines[3]);
					pw.close();
					i = 6;

				} else {// i == 2
					pw.println(lines[0]);
					pw.println(lines[1]);
					pw.println(lines[2]);
					pw.println(lines[3]);
					pw.println(Game.score);
					pw.println(textField.getText().toString());
					pw.close();
					i = 6;
				}
			}
		}
	}

	public int letsConvert(String str) {
		String digits = str.replaceAll("[^0-9.]", "");
		return Integer.parseInt(digits);
	}
}
