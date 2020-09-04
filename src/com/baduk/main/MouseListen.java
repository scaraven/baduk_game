package com.baduk.main;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MouseListen extends MouseAdapter{
	
	private final int height=12,width=12;
	private Handler handler;
	private Game game;
	private Group lib;
	private boolean blackturn = true;
	private Capture cap;
	
	private ArrayList<ArrayList<Integer>> oc;
	private ArrayList<ArrayList<Integer>> c;
	
	
	
	public MouseListen(Handler handler,Game game,Group lib,Capture cap) {
		this.handler = handler;
		this.game = game;
		this.lib = lib;
		this.cap =cap;
	}
	@Override
	public void mousePressed(MouseEvent e) {
		Pair p;
		
		GameObject stone;
		float x = e.getX();
		float y = e.getY();
		//gets closest valid point
		float bx = (Math.round((x-16)/26)*26)+16;
		float by = (Math.round((y-16)/26)*26)+16;
		//checks if a stone already exists on that point
		if(handler.isExists(bx, by)){
			System.out.println("Exists!");
		}
		else {
			if(blackturn) {
				stone = new BlackStone(bx,by,ID.BLACK,game);
				handler.add(stone);
				blackturn = false;
				p = cap.isCaptured(bx, by, 1);
			} else {
				stone = new WhiteStone(bx,by,ID.WHITE,game);
				handler.add(stone);
				blackturn = true;
				p = cap.isCaptured(bx, by, 2);
			
			}
			
			int tx = lib.convertPointToCoord(bx);
			int ty = lib.convertPointToCoord(by);
			if(cap.kill(p, tx, ty, blackturn, stone)) blackturn = true;
			else blackturn = false;
		}
		game.repaint();
	}
}
