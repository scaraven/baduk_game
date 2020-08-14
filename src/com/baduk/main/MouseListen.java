package com.baduk.main;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseListen extends MouseAdapter{
	
	private final int height=12,width=12;
	private Handler handler;
	private Game game;
	private boolean blackturn = true;
	
	
	public MouseListen(Handler handler,Game game) {
		this.handler = handler;
		this.game = game;
	}
	@Override
	public void mousePressed(MouseEvent e) {
		float x = e.getX();
		float y = e.getY();
		//gets closest valid point
		float bx = (Math.round((x-15)/26)*26)+16;
		float by = (Math.round((y-15)/26)*26)+16;
		
		//checks if a stone already exists on that point
		if(handler.isExists(bx, by)){
			System.out.println("Exists!");
		}
		
		else if(blackturn) {
			handler.add(new BlackStone(bx,by,ID.BLACK,game));
			System.out.println("BX - "+bx+" BY - "+by );
			blackturn = false;
		} else {
			handler.add(new WhiteStone(bx,by,ID.BLACK,game));
			System.out.println("BX - "+bx+" BY - "+by );
			blackturn = true;
		}
		game.repaint();
	}
}
