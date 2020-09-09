package com.baduk.main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * 
 * @author Nicolas Schleicher
 *
 *TODO: 
 Add capturing system
 */

public class Game extends JPanel implements ActionListener{

	/**
	 * Top left corner is at 16,16
	 * Line x,y difference is 26
	 * 
	 */
	private static final long serialVersionUID = 3992438100352550118L;
	
	private static int WIDTH=500,HEIGHT=500;
	private Handler handler;
	private Group group;
	private Capture cap;
	private MouseListen mouse;
	
	private JButton pbutton;
	private JLabel JBCap,JWCap;
	
	public int pass_count = 0;
	
	//bcap = number of black stones captured by white
	public int bcap = 0 ,wcap = 0;
	
	
	public Game(){
		bcap = 0;
		wcap = 0;
		
		
		pbutton = new JButton("Pass");
		pbutton.setBounds(525,50,100,25);
		pbutton.setActionCommand("pass");
		
		
		
		group = new Group();
		handler = new Handler(group);
		cap = new Capture(handler,group, this);
		
		new Window(this);
		this.add(pbutton);
		System.out.println(this.getWidth());
		
		mouse = new MouseListen(handler,this,group,cap);
		this.addMouseListener(mouse);
		pbutton.addActionListener(this);
		
		//TODO: Add new handler class for the rectangle and make it more efficient
		//this.addMouseMotionListener(new MouseMove(handler,this));
		
		
	}
	public static void main(String[] args) {
		new Game();
	}
	public Dimension getPreferredSize() {
		return new Dimension(750,500);
		
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
	public void actionPerformed(ActionEvent e) {
		pass_count += 1;
		if(pass_count == 2) {
			System.out.println("Game finished!");
			System.exit(1);
		} else {
			boolean turn = !mouse.getTurn();
			mouse.setTurn(turn);
		}
	}
	
}
