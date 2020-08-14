package com.baduk.main;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
	LinkedList<GameObject>  object= new LinkedList<GameObject>(); 
	public void add(GameObject tempObject) {
		object.add(tempObject);
	}
	public void remove(GameObject tempObject) {
		object.remove(tempObject);
	}
	public void render(Graphics g) {
		for(GameObject temp: object) {
			temp.render(g);
		}
	}
	
	/* This is using a linear search 
	 * TODO: Incorporate a binary search instead
	 * */
	public boolean isExists(float x,float y) {
		for(GameObject temp:object) {
			if(temp.getID() == ID.BLACK || temp.getID() == ID.WHITE) {
				if(temp.getX() == x && temp.getY() == y) {
					return true;
				}
			}
		}
		return false;
		
	}
}
