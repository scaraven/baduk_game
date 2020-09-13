package com.baduk.main;

import java.util.ArrayList;

import javax.swing.JLabel;

public class Capture {
	private Handler handler;
	private Game game;
	private Group group;
	
	private ArrayList<ArrayList<Integer>> woc, c,boc;
	
	private int bcap,wcap = 0;
	private int rows = 0;
	private JLabel JBCap,JWCap;
	
	
	public Capture(Handler handler, Group group, Game game) {
		this.group = group;
		this.handler = handler;
		this.game = game;
		
		this.rows = game.rows;
		
		boc = group.init2DList(rows,rows);
		woc = group.init2DList(rows,rows);
		c = group.init2DList(rows,rows);
		
		JBCap = new JLabel();
		JBCap.setBounds(550,100, 100,25);
		JBCap.setText("Black captures: "+bcap);
		
		JWCap = new JLabel();
		JWCap.setBounds(550,150,100,25);
		JWCap.setText("White captures: "+wcap);
		
		JBCap.setOpaque(true);
		JWCap.setOpaque(true);
		
		game.add(JWCap);
		game.add(JBCap);
	}
	//returns the coord of stone which is in the group to be captured - otherwise it returns null
	public Pair isCaptured(float bx,float by,int value) {
			int a=0,b=0;
			int x = group.convertPointToCoord(bx);
			int y = group.convertPointToCoord(by);
			ArrayList<ArrayList<Integer>> coord = group.getCoord();
			
			for(int i=0;i<4;i++) {
				if(i<2) {
					a = i*2 -1;
					b = 0;
				} else if(i>=2) {
					b = i*2 - 5;
					a = 0;
				}
				if(x+a <0 || x+a > (rows)-1) {
					
				} else if (y+b < 0 || y+b > (rows-1)) {
					
				}//Actual code here
				else if(coord.get(x+a).get(y+b) == (3-value)) {
					if(countLiberties(x+a,y+b,3-value) == 0) {
						return new Pair(x+a,y+b);
					}
				}
			}
			//checks for suicide, if so then return the coord of the stone that has been played
			if(countLiberties(x,y,value) == 0) {
				return new Pair(x,y);
			}
			//nothing happened so null is returned
			return null;
		
	}
	//Counts the liberties of a group provided one stone that is part of the group.
	public int countLiberties(int x, int y,int value) {
		int a = 0,b=0;
		int liberties = 0;
		ArrayList<ArrayList<Integer>> coord = group.getCoord();
		ArrayList<ArrayList<Integer>> dup = new ArrayList<>();
		ArrayList<ArrayList<Integer>> g = group.getGroup(x,y);
		
		System.out.println("Group - "+ g);
		
		for(ArrayList<Integer> temp: g) {
			int tx = temp.get(0);
			int ty = temp.get(1);
			for(int i=0;i<4;i++) {
				if(i<2) {
					a = i*2 -1;
					b = 0;
				} else if(i>=2) {
					b = i*2 - 5;
					a = 0;
				}
				if(tx+a <0 || tx+a > (rows-1)) {
					
				} else if (ty+b < 0 || ty+b > (rows-1)) {
					
				}
				else if(coord.get(tx+a).get(ty+b) == 0) {
					ArrayList<Integer> t = new ArrayList<>();
					t.add(tx+a);
					t.add(ty+b);
					if(!dup.contains(t)) {
						liberties += 1;
						dup.add(t);
					}
				}
				
			}
		}
		return liberties;
	}
	//returns whether it's whites turn or black's
	public boolean kill(Pair p,int x, int y,boolean blackturn, GameObject stone) {
		//the boolean here is opposite of who just played e.g. if blackturn == true: white just played
		if(p == null) {
			if(!blackturn) boc = group.getWritableCoord(boc);
			else woc = group.getWritableCoord(woc);
			return blackturn;
		} 
		else {
			//This checks whether the playre has suicided
			if(p.x == x && p.y == y) {
				System.out.println("Suicide");
				handler.remove(stone);
				JWCap.setText("White captures: "+game.wcap);
				JBCap.setText("Black captures: "+game.bcap);
				return !blackturn;
				}
			else {
				ArrayList<ArrayList<Integer>> g = group.getGroup(p.x, p.y);
				for(ArrayList<Integer> t: g) {
					handler.removeStone(t.get(0), t.get(1));
					if(!blackturn) {
						game.bcap++;
						
					} else {
						game.wcap++;
					}
					c = group.getWritableCoord(c);
					if(woc.equals(c) || boc.equals(c)) {
						float tx = group.convertCoordToPoint(t.get(0));
						float ty = group.convertCoordToPoint(t.get(1));
						//removes illegal stone
						handler.remove(stone);
						
						//this adds back the stone which has been wrongfully captured
						if(!blackturn) {
							handler.add(new WhiteStone(tx,ty,ID.WHITE,game));
							game.wcap--;
						} else {
							handler.add(new BlackStone(tx,ty,ID.BLACK,game));
							game.bcap--;
						}
						System.out.println("KO violation!");
						return !blackturn;
						}
					}
				if(!blackturn) boc = group.getWritableCoord(boc);
				else woc = group.getWritableCoord(woc);
				JWCap.setText("White captures: "+game.wcap);
				JBCap.setText("Black captures: "+game.bcap);
				return blackturn;
				}
			}
		
		}
	
}
