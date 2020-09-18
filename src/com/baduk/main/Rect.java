package com.baduk.main;

import java.awt.Color;
import java.awt.Graphics;

public class Rect extends GameObject{

	private Color color;
		
	public Rect(float x,float  y, ID id, Color color) {
		super(x, y, id);
		this.color = color;
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(color);
		g.fillRect((int)x-4,(int)y-4,8,8);
		
	}
	public ID getID() {
		return id;
	}


}
