package com.baduk.main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MouseListen extends MouseAdapter{
	

	private Handler handler;
	private Game game;
	private Group lib;
	private boolean blackturn = true;
	private Capture cap;
	private Score score;
	
	
	
	public MouseListen(Handler handler,Game game,Group lib,Capture cap) {
		this.handler = handler;
		this.game = game;
		this.lib = lib;
		this.cap =cap;
		
		score = new Score(lib,handler,game);
		
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		float x = e.getX();
		float y = e.getY();
		//gets closest valid point
		float bx = (Math.round((x-16)/26)*26)+16;
		float by = (Math.round((y-16)/26)*26)+16;
		
		if(x > 238 || x < 0 || y > 238 || y < 0) return;
		
		if(game.getSTATE() == STATE.BEGIN) {
			gameCapture(e,bx,by);
		} else if(game.getSTATE() == STATE.DEADSTONE){
			ArrayList<ArrayList<Integer>> coord = new ArrayList<>();
			coord = lib.getCoord();
			int tx = lib.convertPointToCoord(bx);
			int ty = lib.convertPointToCoord(by);
			score.addDeadStone(tx, ty);
			game.repaint();
		}
	}
	public boolean getTurn() {
		return blackturn;
	}
	public void setTurn(boolean turn) {
		blackturn = turn;
	}
	private void gameCapture(MouseEvent e,float bx,float by) {
		Pair p;
		
		GameObject stone;
		
		
		//checks if a stone already exists on that point
		if(handler.isExists(bx, by)){
			System.out.println("Exists!");
		}
		else {
			game.pass_count = 0;
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
