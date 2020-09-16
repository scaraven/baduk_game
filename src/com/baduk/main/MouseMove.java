package com.baduk.main;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.LinkedList;

public class MouseMove implements  MouseMotionListener{
	
	private Game game;
	private MouseListen mouse;
	private Group group;
	private GameObject r;
	private Handler handler;
	
	ArrayList<ArrayList<Integer>> coord;
	
	
	private int ox = 0,oy = 0;
	private int i = 0,j=0;
	private int rows;
	
	public MouseMove(Game game, MouseListen mouse, Group group, Handler handler) {
		this.game = game;
		this.group = group;
		this.mouse = mouse;
		this.handler = handler;
		
		this.rows = game.rows;
		coord = new ArrayList<>();
		
		
		coord = group.init2DList(rows,rows);
	}
	

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if(game.state == STATE.BEGIN) {
			//get Mouse co-ord
			float ex = e.getX();
			float ey = e.getY();
			
			//Find the closest valid point
			float bx = (Math.round((ex-15)/26)*26)+16;
			float by = (Math.round((ey-15)/26)*26)+16;
			
			int x = group.convertPointToCoord(bx);
			int y = group.convertPointToCoord(by);
			
			if(x > (rows-1)
				|| x < 0 
				|| y > (rows-1)
				|| y < 0) {
				handler.opobject.remove(r);
				return;
			}
			
			
			coord = group.getWritableCoord(coord);
			
			//Checks if the position is an empty space
			if(coord.get(x).get(y) == 0) {
				
				if(ox != x || oy != y) {
					try {
						handler.opobject.remove(r);
					} catch(Exception error) {
						
					}
					
					if(mouse.getTurn() == true) {
						r = new BlackTransparentStone(bx,by,ID.TRANSPARENTBLACK);
					} else {
						r = new WhiteTransparentStone(bx,by,ID.TRANSPARENTWHITE);
					}
					
					handler.opobject.add(r);
					ox = x;
					oy = y;
					
				} 
			} else {
				handler.opobject.remove(r);
			}
		game.repaint();
		}
			
			
			
		
		
	}
}
