package com.baduk.main;

import java.awt.Color;
import java.util.ArrayList;

public class Score {
	private Group group;
	private Handler handler;
	private Game game;
	
	private float bscore = 0.0f,wscore = 0.0f;
	
	public Score(Group group, Handler handler, Game game) {
		this.group = group;
		this.handler = handler;
		this.game = game;
		
		
	}
	public void addDeadStone(int x, int y) {
		ArrayList<ArrayList<Integer>> coord = group.getCoord();
		int value = coord.get(x).get(y);
		if(value == 0) {
			return;
		}
		ArrayList<ArrayList<Integer>> g = new ArrayList<>();
		g = group.getGroup(x, y);
		
		for(ArrayList<Integer> t : g) {
			int tx = t.get(0);
			int ty = t.get(1);
			if(value == 1) {
				handler.removeStone(tx,ty);
				handler.opobject.add(new BlackTransparentStone(group.convertCoordToPoint(tx),group.convertCoordToPoint(ty),ID.TRANSPARENTBLACK));
				handler.rectobject.add(new Rect(group.convertCoordToPoint(tx),group.convertCoordToPoint(ty),ID.RECT,Color.white));
			} else if(value == 2) {
				handler.removeStone(tx, ty);
				handler.opobject.add(new WhiteTransparentStone(group.convertCoordToPoint(tx),group.convertCoordToPoint(ty),ID.TRANSPARENTWHITE));
				handler.rectobject.add(new Rect(group.convertCoordToPoint(tx),group.convertCoordToPoint(ty),ID.RECT,Color.black));
			} else if(value == 6) {
				handler.removeTransparentStone(tx, ty);
				handler.removeRectStone(tx, ty);
				handler.object.add(new BlackStone(group.convertCoordToPoint(tx),group.convertCoordToPoint(ty),ID.BLACK,game));
				
				
			} else if(value == 5) {
				handler.removeTransparentStone(tx, ty);
				handler.removeRectStone(tx, ty);
				handler.object.add(new WhiteStone(group.convertCoordToPoint(tx),group.convertCoordToPoint(ty),ID.WHITE,game));
				
			}
			group.setCoord(t.get(0), t.get(1), 7-value);
		}
		
	}
	public void assignTerritory(float komi) {
		int a=0,b=0;
		
		ArrayList<ArrayList<Integer>> coord = group.getCoord();
		for(int x = 0; x<coord.size(); x++) {
			for(int y=0;y<coord.get(0).size();y++){
				int value = coord.get(x).get(y);
				if(value == 1 || value == 5 || value == 2 || value == 6) {
					
					for(int i=0;i<4;i++) {
						if(i>=2) {
							b = i*2 - 5; //b = -1 or 1
							a = 0;
						} else if(i<2){
							a = i*2-1; // a = 1 or -1
							b = 0;
							}
						if(x+a < 0 || x+a > (game.rows-1) || y+b < 0 || y+b > (game.rows-1)) {
							
						}
						else if(coord.get(x+a).get(y+b) == 0) {
							ArrayList<ArrayList<Integer>> g = group.getGroup(x+a,y+b);
							System.out.println("Group - "+g);
							for(ArrayList<Integer> t:g) {
								//if the territory is marked by black then change the value
								if(value % 2 == 1) {
									coord.get(t.get(0)).set(t.get(1), 3);
									handler.rectobject.add(new Rect(group.convertCoordToPoint(t.get(0)),group.convertCoordToPoint(t.get(1)),ID.RECT,Color.black));
								}
								//otherwise if it's marked by white
								else if(value % 2 == 0) {
									coord.get(t.get(0)).set(t.get(1), 4);
									handler.rectobject.add(new Rect(group.convertCoordToPoint(t.get(0)),group.convertCoordToPoint(t.get(1)),ID.RECT,Color.white));
								}
							}
						}
					}
				}
			}
		}
		
	}
	
}
