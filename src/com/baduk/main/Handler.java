package com.baduk.main;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
	LinkedList<GameObject>  object= new LinkedList<GameObject>(); 
	
	private Group lib;
	
	public Handler(Group lib) {
		this.lib = lib;
	}
	public void add(GameObject tempObject) {
		object.add(tempObject);
		if(tempObject.getID() == ID.BLACK) {
			lib.addCoord(tempObject.getX(), tempObject.getY(), 1);
		} 
		else if(tempObject.getID() == ID.WHITE) {
			lib.addCoord(tempObject.getX(), tempObject.getY(), 2);
		}
		
	}
	public void remove(GameObject tempObject) {
		object.remove(tempObject);
		if(tempObject.getID() == ID.BLACK) {
			lib.removeCoord(tempObject.getX(), tempObject.getY(), 1);
		} 
		else if(tempObject.getID() == ID.WHITE) {
			lib.removeCoord(tempObject.getX(), tempObject.getY(), 2);
		}
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
