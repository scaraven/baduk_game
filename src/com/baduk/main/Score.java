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
		System.out.println("HERE!");
		System.out.println(value);
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
		int bscore = 0;
		int wscore = 0;
		
		int a = 0,b = 0;
		bscore += game.bcap;
		wscore += game.wcap;
		
		boolean isfinished = false;
		
		ArrayList<ArrayList<Integer>> coord = group.getCoord();
		
		for(int x = 0; x<coord.size(); x++) {
			for(int y=0;y<coord.get(0).size();y++){
				int value = coord.get(x).get(y);
				
				//this makes sure we're not checking a neutral point or an empty one
				if(value < 7
						&& value > 0) {
					for(int i=0;i<4;i++) {
						if(i<2) {
							a = i*2 -1;
							b = 0;
						} else if(i>=2) {
							b = i*2 - 5;
							a = 0;
						}
						//makes sure the bounds are within the board
						if(x+a < 0 || x+a > (game.rows-1) || y+b < 0 || y+b > (game.rows-1)) {
						
							
						//is this an empty spot that can be claimed
						} else if(coord.get(x+a).get(y+b) == 0) {
							//if the point is occupied by black
							if(value % 2 == 1) {
								group.setCoord(a+x, y+b, 3);
							//otherwise the point is occupied by white
							} else {
								group.setCoord(a+x, y+b, 4);
							}
							
							coord = group.getCoord();
							if(value % 2 == 1) {
							handler.rectobject.add(new Rect(group.convertCoordToPoint(x+a),group.convertCoordToPoint(y+b),ID.RECT,Color.black));
							} else if(value % 2 == 0) {
								handler.rectobject.add(new Rect(group.convertCoordToPoint(x+a),group.convertCoordToPoint(y+b),ID.RECT,Color.white));
							}
						//Checks if enemy territory has been added next to your stone which turns it into territory
						} else if(coord.get(x+a).get(y+b) == (5-value)) {
							ArrayList<ArrayList<Integer>> g = group.getGroup(x+a, y+b);
							for(ArrayList<Integer> t: g) {
								coord.get(t.get(0)).set(t.get(1), 7);
							}
						}
					}
				}
			}
		}
		
	}
	
}
