package com.baduk.main;

import java.awt.Color;
import java.awt.Graphics;

public class Rect extends GameObject{

	public Rect(float x,float  y, ID id) {
		super(x, y, id);
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.black);
		g.fillRect((int)x-6,(int)y-6,12,12);
		
	}


}
