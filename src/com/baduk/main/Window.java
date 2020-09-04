package com.baduk.main;

import javax.swing.JFrame;

public class Window{
	public Window(Game game) {
		JFrame frame = new JFrame("BADUK");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		game.setLayout(null);
		frame.add(game);
		
		
		frame.pack();
		
		frame.setVisible(true);
		//game.start();
		
		
		
	}
}
