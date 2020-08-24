package com.baduk.main;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseListen extends MouseAdapter{
	
	private final int height=12,width=12;
	private Handler handler;
	private Game game;
	private Group lib;
	private boolean blackturn = true;
	
	
	
	public MouseListen(Handler handler,Game game,Group lib) {
		this.handler = handler;
		this.game = game;
		this.lib = lib;
	}
	@Override
	public void mousePressed(MouseEvent e) {
		int value = 0;
		
		float x = e.getX();
		float y = e.getY();
		//gets closest valid point
		float bx = (Math.round((x-16)/26)*26)+16;
		float by = (Math.round((y-16)/26)*26)+16;
		//checks if a stone already exists on that point
		if(handler.isExists(bx, by)){
			System.out.println("Exists!");
		}
		
		
		else if(blackturn) {
			handler.add(new BlackStone(bx,by,ID.BLACK,game));
			blackturn = false;
		} else {
			handler.add(new WhiteStone(bx,by,ID.BLACK,game));
			blackturn = true;
		}
		game.repaint();
	}
}
