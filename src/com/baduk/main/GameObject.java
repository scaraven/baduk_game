package com.baduk.main;

import java.awt.Graphics;

public abstract class GameObject{
	
	protected float x,y;
	protected ID id;
	protected Game game;
	
	public GameObject(float x, float y, ID id){
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
	
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
		
	}
	public float getX() {
		return x;
	}
	public float getY() {
		return y;
	}
	public ID getID() {
		return id;
	}
	
	

}

