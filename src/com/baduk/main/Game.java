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
	 * Top left corner is at 16,16
	 * Line x,y difference is 26
	 * 
	 */
	private static final long serialVersionUID = 3992438100352550118L;
	
	private static int WIDTH=500,HEIGHT=500;
	private Handler handler;
	private Group group;
	
	public Game(){
		group = new Group();
		handler = new Handler(group);
		
		new Window(this);
		
		this.addMouseListener(new MouseListen(handler,this,group));
		
		//TODO: Add new handler class for the rectangle and make it more efficient
		//this.addMouseMotionListener(new MouseMove(handler,this));
		
		for(int i=3;i<7;i++) {
			handler.add(new BlackStone(3*26+16,i*26+16,ID.BLACK,this));
		}
		
		
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
