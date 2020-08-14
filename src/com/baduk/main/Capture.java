package com.baduk.main;

public class Capture {
	private Handler handler;
	private Game game;
	
	
	public Capture(Handler handler,Game game) {
		this.handler = handler;
		this.game = game;
	}
	public boolean isCaptured(float bx,float by,ID id) {
		for(GameObject temp:handler.object) {
			//Find surrounding stones
		}
		return false;
		
	}
	
}
