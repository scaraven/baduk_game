package com.baduk.main;

import java.awt.Color;
import java.awt.Graphics;

public class Rect extends GameObject{

	private Color color;
		
	public Rect(float x,float  y, ID id, Color color) {
		super(x, y, id);
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(color);
		g.fillRect((int)x-6,(int)y-6,12,12);
		
	}
	public ID getID() {
		return id;
	}


}
