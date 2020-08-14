package com.baduk.main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Window{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1677109779261557547L;
	public Window(Game game) {
		JFrame frame = new JFrame("BADUK");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		frame.add(game);
		frame.pack();
		
		
		frame.setVisible(true);
		//game.start();
		
		
		
	}
}
