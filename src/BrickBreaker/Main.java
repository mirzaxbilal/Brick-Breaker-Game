package BrickBreaker;

import javax.swing.JFrame;


public class Main {

	// loads the game to JFrame.
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		Gameplay gamePlay = new Gameplay();
		frame.setResizable(false);
		frame.setBounds(0, 0, 708, 610);
		frame.setTitle("Brick Breaker");
		frame.add(gamePlay);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}
}