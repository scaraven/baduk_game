package com.baduk.main;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
	LinkedList<GameObject>  object= new LinkedList<GameObject>();
	LinkedList<GameObject>  opobject = new LinkedList<GameObject>();
	
	private Group group;
	
	public Handler(Group lib) {
		this.group = lib;
	}
	public void add(GameObject tempObject) {
		object.add(tempObject);
		if(tempObject.getID() == ID.BLACK) {
			group.addCoord(tempObject.getX(), tempObject.getY(), 1);
		} 
		else if(tempObject.getID() == ID.WHITE) {
			group.addCoord(tempObject.getX(), tempObject.getY(), 2);
		}
		
	}
	public void remove(GameObject tempObject) {
		object.remove(tempObject);
		if(tempObject.getID() == ID.BLACK) {
			group.removeCoord(tempObject.getX(), tempObject.getY());
		} 
		else if(tempObject.getID() == ID.WHITE) {
			group.removeCoord(tempObject.getX(), tempObject.getY());
		}
	}
	//TODO: Add binary search instead of linear
	public void removeStone(int x, int y) {
		
		for(GameObject temp: object) {
			int tx = group.convertPointToCoord(temp.getX());
			int ty = group.convertPointToCoord(temp.getY());
			if(tx == x && ty == y) {
				remove(temp);
				return;
			}
		}
	}
	public void render(Graphics g) {
		for(GameObject temp: object) {
			temp.render(g);
		}
		for(GameObject temp: opobject) {
			temp.render(g);
		}
	}
	
	/* This is using a linear search 
	 * TODO: Incorporate a binary search instead
	 * */
	/* obseleted due to coord[] in group class*/
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
