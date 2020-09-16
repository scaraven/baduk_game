package com.baduk.main;

import java.util.ArrayList;

public class Score {
	private Group group;
	private Handler handler;
	private Game game;
	
	private float bscore = 0.0f,wscore = 0.0f;
	private ArrayList<ArrayList<Integer>> coord = new ArrayList<>();
	//Create uniqe coord list
	
	public Score(Group group, Handler handler, Game game) {
		this.group = group;
		this.handler = handler;
		this.game = game;
		
		
	}
	public void addDeadStone(int x, int y) {
		coord = group.getCoord();
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
			} else if(value == 2) {
				handler.removeStone(tx, ty);
				handler.opobject.add(new WhiteTransparentStone(group.convertCoordToPoint(tx),group.convertCoordToPoint(ty),ID.TRANSPARENTWHITE));
			} else if(value == 6) {
				handler.removeTransparentStone(tx, ty);
				handler.object.add(new BlackStone(group.convertCoordToPoint(tx),group.convertCoordToPoint(ty),ID.BLACK,game));
			} else if(value == 5) {
				handler.removeTransparentStone(tx, ty);
				handler.object.add(new WhiteStone(group.convertCoordToPoint(tx),group.convertCoordToPoint(ty),ID.WHITE,game));
			}
			group.setCoord(t.get(0), t.get(1), 7-value);
		}
		
	}
	
}
