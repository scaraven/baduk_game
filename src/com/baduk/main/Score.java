package com.baduk.main;

import java.util.ArrayList;

public class Score {
	private Group group;
	private Handler handler;
	
	private float bscore = 0.0f,wscore = 0.0f;
	private ArrayList<ArrayList<Integer>> coord = new ArrayList<>();
	//Create uniqe coord list
	
	public Score(Group group, Handler handler) {
		this.group = group;
		this.handler = handler;
		
		
		coord = group.init2DList(19, 19);
		coord = group.getWritableCoord(coord);
		
		
	}
	public void addDeadStone(int x, int y) {
		coord = group.getWritableCoord(coord);
		int value = coord.get(x).get(y);
		System.out.println("HERE!");
		if(value == 0) {
			return;
		}
		ArrayList<ArrayList<Integer>> g = new ArrayList<>();
		g = group.getGroup(x, y);
		
		for(ArrayList<Integer> t : g) {
			coord.get(t.get(0)).set(t.get(1),7-value);
			int tx = t.get(0);
			int ty = t.get(1);
			if(value == 1) {
				handler.removeStone(tx,ty);
				handler.opobject.add(new BlackTransparentStone(group.convertCoordToPoint(tx),group.convertCoordToPoint(ty),ID.TRANSPARENTBLACK));
			} else if(value == 2) {
				handler.removeStone(tx, ty);
				handler.opobject.add(new WhiteTransparentStone(group.convertCoordToPoint(tx),group.convertCoordToPoint(ty),ID.TRANSPARENTWHITE));
			}
		}
	}
	
}
