package hitproject.spacesheep.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class HighScorePage {
	public JLabel lbImage;
	public JFrame frame;
	String line[] = new String[6];

	public HighScorePage(int width, int height, String title) {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setBounds(170, 90, width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setVisible(true);

		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("HighScoreFile.txt"));
			int i = 0;
			
			while (i < 6) {
				line[i] = br.readLine();
				System.out.println(line[i]);
				i++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		int GovaText = -85;
		int RoJavText = -60;
		int GovaAharon = -20;
		JLabel lblSpaceSheep = new JLabel("HighScore");
		lblSpaceSheep.setBackground(Color.GREEN);
		lblSpaceSheep.setForeground(Color.LIGHT_GRAY);
		lblSpaceSheep.setBounds(width / 2 - 120 + RoJavText, height / 2 - 300 + GovaText, 300, 100);
		lblSpaceSheep.setFont(new Font("Space Sheep", Font.PLAIN, 40));
		frame.getContentPane().add(lblSpaceSheep);

		JLabel user1 = new JLabel(line[1]);
		user1.setForeground(Color.white);
		user1.setBounds(width / 2 - 120 + RoJavText, height / 2 - 100 + GovaText, 300, 100);
		user1.setFont(new Font("", Font.PLAIN, 20));
		user1.setBackground(Color.GREEN);
		frame.getContentPane().add(user1);

		JLabel user2 = new JLabel(line[3]);
		user2.setBackground(Color.GREEN);
		user2.setForeground(Color.white);
		user2.setBounds(width / 2 - 120 + RoJavText, height / 2 + GovaText, 300, 100);
		user2.setFont(new Font("", Font.PLAIN, 20));
		frame.getContentPane().add(user2);

		JLabel user3 = new JLabel(line[5]);
		user3.setBackground(Color.GREEN);
		user3.setForeground(Color.white);
		user3.setBounds(width / 2 - 120 + RoJavText, height / 2 + 100 + GovaAharon + GovaText, 300, 100);
		user3.setFont(new Font("", Font.PLAIN, 20));
		frame.getContentPane().add(user3);

		JLabel user1Score = new JLabel(line[0]);
		user1Score.setBackground(Color.GREEN);
		user1Score.setForeground(Color.white);
		user1Score.setBounds(width / 2 + 120 + RoJavText, height / 2 - 100 + GovaText, 300, 100);
		user1Score.setFont(new Font("", Font.PLAIN, 20));
		frame.getContentPane().add(user1Score);

		JLabel user2Score = new JLabel(line[2]);
		user2Score.setBackground(Color.black);

		user2Score.setForeground(Color.white);
		user2Score.setBounds(width / 2 + 120 + RoJavText, height / 2 - 0 + GovaText, 300, 100);
		user2Score.setFont(new Font("", Font.PLAIN, 20));
		frame.getContentPane().add(user2Score);

		JLabel user3Score = new JLabel(line[4]);
		user3Score.setBackground(Color.GREEN);
		user3Score.setForeground(Color.white);
		user3Score.setBounds(width / 2 + 120 + RoJavText, height / 2 + 100 + GovaAharon + GovaText, 300, 100);
		user3Score.setFont(new Font("", Font.PLAIN, 20));
		frame.getContentPane().add(user3Score);

		JButton killBtn = new JButton("back");
		killBtn.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.setVisible(false); // you can't see me!
				frame.dispose();
				new MenuWindow(1024, 540, "Space Ship");
			}
		});

		killBtn.setBounds(width / 2 - 400, height / 2 + 170, 200, 50);
		killBtn.setContentAreaFilled(false);
		killBtn.setBorder(new RoundedBorder(40));
		killBtn.setBackground(Color.white);
		killBtn.setForeground(Color.white);
		killBtn.setFont(new Font("back", Font.PLAIN, 20));
		frame.getContentPane().add(killBtn);

		lbImage = new JLabel("");
		lbImage.setIcon(new ImageIcon("res\\highscore1.png"));
		lbImage.setBounds(0, 0, width, height);
		frame.getContentPane().add(lbImage);

	}
}
