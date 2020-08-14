package com.baduk.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BlackStone extends GameObject{

	public BlackStone(float x,float y, ID id,Game game) {
		super(x, y, id);
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		BufferedImage img;
		try {
			img = ImageIO.read(new File("C:/Users/Nicolas Schleicher/Documents/black_stone.png"));
			g.drawImage(img,(int)x-13,(int) y-13, 25,25,game);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public Dimension getPreferredSize() {
		return new Dimension(10,10);
		
	}
	public Point getLocation() {
		return new Point(10,10);
		
	}
	public void paint(Graphics g) {
		g.setColor(Color.black);
		g.drawString("HI",0,0);
	}



}
