package com.baduk.main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseMove implements  MouseMotionListener{
	
	private Handler handler;
	private Game g;
	private float ox = 0,oy = 0;
	private Rect r;
	private int i = 0,j=0;
	
	public MouseMove(Handler handler,Game g) {
		this.handler = handler;
		this.g = g;
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		//get Mouse co-ord
		float x = e.getX();
		float y = e.getY();
		
		//Find the closest valid point
		float bx = (Math.round((x-15)/26)*26)+16;
		float by = (Math.round((y-15)/26)*26)+16;
		
		//Checks if a stone already exists on the point
		if(handler.isExists(bx, by)) {
			//Prevents the program from removing an object which might've already been deleted
			if(j==0) {
			handler.remove(r);
			g.repaint();
			}
			j++;
		}
		//Checks if the closest point has changed
		else if(ox != bx || oy != by) {
			if(i!=0) handler.remove(r);
			ox = bx;
			oy = by;
			
			r = new Rect(bx,by,ID.RECT);
			i++;
			handler.add(r);
			g.repaint();
			j = 0;
		} else {
			j = 0;
		}
			
			
			
			
		
		
	}
}
