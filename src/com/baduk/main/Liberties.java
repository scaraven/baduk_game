package com.baduk.main;

import java.awt.List;
import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class Liberties {
	
	private Handler handler;
	private Game game;
	private Random r =  new Random();
	
	private int rows = 19;
	private ArrayList<ArrayList<Integer>> coord = new ArrayList<>(rows);
	private ArrayList<ArrayList<Integer>>keeper;
	
	public Liberties( Handler handler,Game game) {
		this.handler = handler;
		this.game = game;
		coord = init2DList(rows,rows,coord);
		ArrayList<ArrayList<Integer>> a = new ArrayList<>();
		
		for(int i=0;i<5;i++) {
			ArrayList<Integer> b = new ArrayList<>();
			b.add(r.nextInt(10));
			b.add(r.nextInt(10));
			a.add(b);
			
		}
		System.out.println("A - "+a);
		checkGroup(0,0,a);
	}
	public ArrayList<ArrayList<Integer>> getGroup(float bx,float by){
		//transform point to coord
		int x = (int) ((bx - 16)/26);
		int y = (int) ((bx-16)/26);
		
		
		
		ArrayList<ArrayList<Integer>>group = new ArrayList<>();
		keeper = new ArrayList<>();
		
		int value = coord.get(x).get(y);
		int surroundingstones = 0;
		
		
		boolean notfinished = true;
		
		//checks for blank space
		if(coord.get(x).get(y) == 0) {
			return null;
		//checks for black stone
		} else if(value == 1) {
			ArrayList<Integer> a = new ArrayList<>();
			a.add(x);
			a.add(y);
			
			
			group.add(a);
			
			while(notfinished) {
			
			}
			
		//checks for white stone
		} else if(value == 2){
			
		}
		return null;
		
	}
	//This counts the number of surrounding stones which aren't already in the array
	private int getNumStones(ArrayList<ArrayList<Integer>>group, int x, int y) {
		return rows;
		
	}
	//This method checks if the corresponding x and y points are in the group array
	private boolean checkGroup(int x, int y, ArrayList<ArrayList<Integer>>group) {
		ArrayList<ArrayList<Integer>> sorted = new ArrayList<>();
		//First we need to sort the group list
		//We are sorting first on x then on y
		
		
		
		sorted = sort2DList(group);
		System.out.println("Sorted - "+sorted);
		return false;
		
		
		
	}
	//Merge sort
	private ArrayList<ArrayList<Integer>> sort2DList(ArrayList<ArrayList<Integer>> u){
		ArrayList<ArrayList<Integer>> s = new ArrayList<>();
		init2DList(u.size(),u.get(0).size(), s);
		int a,b;
		System.out.println("HERE");
		for(int len=2;len<=u.size()/2;len*=2) {
		
			for(int i=0;i<u.size();i+=len) {
				for(int j=0;j<len;j++) {
					//Takes sub array length of len from offset i
					s.get(j).set(0, u.get(i+j).get(0));
				}
				System.out.println("S -"+s);
				int index = len/2;
				for(int j=0;j<index;j++) {
					a = s.get(0+j).get(0);
					b = s.get(index+j).get(0);
					if(a<=b) {
						u.get(i+j).set(0, a);
						u.get(i+j+1).set(0, b);
					} else {
						u.get(i+j).set(0, b);
						u.get(i+j+1).set(0, a);
					}
				}
			}
			
		}
		
		return u;
		
	}
	private ArrayList<ArrayList<Integer>> init2DList(int num1,int num2, ArrayList<ArrayList<Integer>> coord) {
		for(int i=0;i<num1;i++) {
			coord.add(new ArrayList<Integer>());
			for(int j=0;j<num2;j++) coord.get(i).add(0);
		}
		return coord;
	}
}
