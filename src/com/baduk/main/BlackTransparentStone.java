package com.baduk.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BlackTransparentStone extends GameObject{

	public BlackTransparentStone(float x, float y, ID id) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		BufferedImage img;
		try {
			img = ImageIO.read(new File("C:/Users/Nicolas Schleicher/Documents/black_stone2_opaque.png"));
			g.drawImage(img,(int)x-12,(int) y-12, 25,25,game);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
