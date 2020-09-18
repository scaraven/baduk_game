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
				handler.rectobject.add(new Rect(group.convertCoordToPoint(tx),group.convertCoordToPoint(ty),ID.RECT,Color.black));
			} else if(value == 2) {
				handler.removeStone(tx, ty);
				handler.opobject.add(new WhiteTransparentStone(group.convertCoordToPoint(tx),group.convertCoordToPoint(ty),ID.TRANSPARENTWHITE));
				handler.rectobject.add(new Rect(group.convertCoordToPoint(tx),group.convertCoordToPoint(ty),ID.RECT,Color.white));
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
	public void calcScore(float komi) {
		int bscore = 0;
		int wscore = 0;
		bscore += game.bcap;
		wscore += game.wcap;
		
		boolean isfinished = false;
		
		ArrayList<ArrayList<Integer>> coord = group.getCoord();
		
		for(ArrayList<Integer> t: coord) {
			for(int i=0;i<t.size();i++){
				if(t.get(i) == 1 || t.get(i) == 2) {
					
					
				}
			}
		}
		
	}
	
}
