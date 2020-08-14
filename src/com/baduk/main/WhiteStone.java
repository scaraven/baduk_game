package com.baduk.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class WhiteStone extends GameObject{

	public WhiteStone(float x,float  y, ID id,Game game) {
		super(x, y, id);
		
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		BufferedImage img;
		try {
			img = ImageIO.read(new File("C:/Users/Nicolas Schleicher/Documents/white_stone.png"));
			g.drawImage(img,(int)x-13,(int) y-13, 25,25,game);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


}
