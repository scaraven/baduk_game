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
	
	//INCORRECT
	private static int WIDTH=500,HEIGHT=500;
	
	private static float komi = 7.5f;
	
	
	private Handler handler;
	private Group group;
	private Capture cap;
	private MouseListen mouse;
	public Score score;
	
	private JButton pbutton;
	private JButton Jendstone;
	private JButton Jcancel;
	
	public int rows = 9;
	
	public int pass_count = 0;
	//bcap = number of black stones captured by white
	public int bcap = 0 ,wcap = 0;
	
	public STATE state = STATE.BEGIN;
	
	
	public Game(){
		bcap = 0;
		wcap = 0;
		
		
		pbutton = new JButton("Pass");
		pbutton.setBounds(525,25,125,25);
		pbutton.setActionCommand("pass");
		
		
		
		group = new Group();
		handler = new Handler(group);
		cap = new Capture(handler,group, this);
		score = new Score(group,handler, this);
		
		new Window(this);
		this.add(pbutton);
		System.out.println(this.getWidth());
		
		mouse = new MouseListen(handler,this,group,cap,score);
		this.addMouseListener(mouse);
		pbutton.addActionListener(this);
		
		//TODO: Add new handler class for the rectangle and make it more efficient
		// 2. Replace black square with opaque version of stone (colour depends on who is playing)
		this.addMouseMotionListener(new MouseMove(this,mouse,group, handler));
		
		
	}
	public static void main(String[] args) {
		new Game();
	}
	public Dimension getPreferredSize() {
		return new Dimension(750,500);
		
	}
	private void addBackground(Graphics g) {
		try {
			BufferedImage img = ImageIO.read(new File("C:/Users/Nicolas Schleicher/Documents/baduk_board-9x9.png"));
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
		if(state == STATE.BEGIN) {
			if("pass".equals(e.getActionCommand())) {
				pass_count += 1;
				if(pass_count == 2) {
					System.out.println("Game finished!");
					pbutton.setVisible(false);
					System.out.println("Please click on dead stones.");
					
					Jendstone = new JButton("Accept Dead Stones");
					Jendstone.setActionCommand("acceptdead");
					Jendstone.setBounds(525,25,200,25);
					
					
					Jcancel = new JButton("Cancel and play");
					Jcancel.setActionCommand("cancel");
					Jcancel.setBounds(525,55,200,25);
					
					add(Jendstone);
					add(Jcancel);
					Jendstone.addActionListener(this);
					Jcancel.addActionListener(this);
					repaint();
					
					state = STATE.DEADSTONE;
				} else {
					boolean turn = !mouse.getTurn();
					mouse.setTurn(turn);
				}
			}
		} else if(state == STATE.DEADSTONE) {
			if("acceptdead".equals(e.getActionCommand())){
				state = STATE.END;
				Jendstone.setVisible(false);
				Jcancel.setVisible(false);
				score.assignTerritory();
				repaint();
				score.countScore(komi);
			} else if("cancel".equals(e.getActionCommand())) {
				state = STATE.BEGIN;
				pbutton.setVisible(true);
				Jendstone.setVisible(false);
				Jcancel.setVisible(false);
				
			}
		} else if(state == STATE.END) {
		}
	}
	public void setSTATE(STATE state) {
		this.state = state;
	}
	public STATE getSTATE() {
		return state;
	}
	
}
