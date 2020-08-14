package com.baduk.main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * 
 * 
 * @author Nicolas Schleicher
 *
 *TODO: 
 Add capturing system
 */

public class Game extends JPanel{

	/**
	 * Top left corner is at 15,15
	 * Line x,y difference is 26
	 * 
	 */
	private static final long serialVersionUID = 3992438100352550118L;
	
	private static int WIDTH=500,HEIGHT=500;
	private Handler handler;
	private Liberties lib;
	
	public Game(){
		
		handler = new Handler();
		lib = new Liberties(handler,this);
		new Window(this);
		
		this.addMouseListener(new MouseListen(handler,this));
		this.addMouseMotionListener(new MouseMove(handler,this));
		
		
	}
	public static void main(String[] args) {
		new Game();
	}
	public Dimension getPreferredSize() {
		return new Dimension(500,500);
		
	}
	private void addBackground(Graphics g) {
		try {
			BufferedImage img = ImageIO.read(new File("C:/Users/Nicolas Schleicher/Downloads/baduk_board.png"));
			WIDTH = img.getWidth();
			HEIGHT = img.getHeight();
			
			g.drawImage(img, 0, 0, WIDTH,HEIGHT, this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		addBackground(g);
		render(g);
		
	}
	public void render(Graphics g) {
		handler.render(g);
		
	}
	
}
