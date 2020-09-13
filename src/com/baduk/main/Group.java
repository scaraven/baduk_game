package com.baduk.main;

import java.util.ArrayList;

public class Group {
	
	
	private int rows = 9;
	private ArrayList<ArrayList<Integer>> coord = new ArrayList<>(rows);
	private ArrayList<ArrayList<Integer>>keeper;
	
	public Group() {
		coord = init2DList(rows,rows);
	}
	//returns group given a co-ord of one stone;
	public ArrayList<ArrayList<Integer>> getGroup(int x, int y){
		//transform point to coord
		int nx = x,ny = y;
		int a =0,b=0;
		
		boolean notfinished = true;
		boolean stone1 = true;
		boolean end = true;
		
		ArrayList<ArrayList<Integer>>group = new ArrayList<>();
		
		keeper = new ArrayList<>();
		
		int value = coord.get(x).get(y);
		ArrayList<Integer> d = new ArrayList<>();
		d.add(x);
		d.add(y);
		group.add(d);
		while(notfinished) {
			//checks surroundings
			for(int i=0;i<4;i++) {
				if(i>=2) {
					b = i*2 - 5; //b = -1 or 1
					a = 0;
				} else if(i<2){
					a = i*2-1; // a = 1 or -1
					b = 0;
					}
				if(x+a < 0 || x+a > (rows-1) || y+b < 0 || y+b > (rows-1)) {
					
				}
				else if(coord.get(x+a).get(y+b) == value) {
					//if the stone is not in the group
					if(!checkGroup(x+a,y+b,group)) {
						//this boolean means that a stone not part of the group array has been found
						end = true;
						
						
						if(!stone1) {
							ArrayList<Integer> temp = new ArrayList<>();
							temp.add(x+a);
							temp.add(y+b);
							keeper.add(temp);
						
							//Is this the first stone we found?
						} else {
							
							//sets the co-ordinates for the next stone
							nx = x+a;
							ny = y+b;
							ArrayList<Integer> temp = new ArrayList<>();
							temp.add(nx);
							temp.add(ny);
							group.add(temp);
							stone1 = false;
						}
					}
				}
			}
			if(end) {
				end  = false;
			} else {
				//No more stones to check
				int size = keeper.size();
				if(size == 0) {
					return group;
				}
				nx = keeper.get(size-1).get(0);
				ny = keeper.get(size-1).get(1);
				end = false;
				if(!checkGroup(x,y,group)) {	
					ArrayList<Integer> temp = new ArrayList<>();
					temp.add(nx);
					temp.add(ny);
					group.add(temp);
				}
				keeper.remove(size-1);
			}
			
			stone1 = true;
			x = nx;
			y = ny;
			
		}
		return group;
			
		
	}
	//This method checks if the corresponding x and y points are in the group array
	private boolean checkGroup(int x, int y, ArrayList<ArrayList<Integer>>group) {
		//First we need to sort the group list
		//We are sorting first on x then on y
		ArrayList<ArrayList<Integer>>sorted = new ArrayList<>();
		sorted = sort2DList(group);
		int index = sorted.size()/2;
		int top = sorted.size() - 1;
		int oi = index;
		int ooi = index;
		int bot = 0;
		while(true) {
			if(sorted.get(index).get(0) == x) {
				if(sorted.get(index).get(1) == y) {
					return true;
				} else if(sorted.get(index).get(1) > y){

					top = index;
					index  = (int) Math.floor((bot+index)/2f);
				} else {
					bot = index;
					index = (int) Math.ceil((top+index)/2f);
					}
			}
			else if(sorted.get(index).get(0) > x) {
					top = index;
					index  = (int) Math.floor((bot+index)/2f);
				} else if(sorted.get(index).get(0) < x) {
					bot = index;
					index = (int) Math.ceil((top+index)/2f);
				}
			if(oi == index || ooi == index) {
				return false;
			} else {
				ooi = oi;
				oi = index;
			}
		}
		
		
		
	}
	//Merge sort
	private ArrayList<ArrayList<Integer>> sort2DList(ArrayList<ArrayList<Integer>> u){
		
		ArrayList<ArrayList<Integer>> t1 = new ArrayList<>();
		ArrayList<ArrayList<Integer>> t2 = new ArrayList<>();
		
		t1 = init2DList(u.size(),u.get(0).size());
		t2 = init2DList(u.size(),u.get(0).size());
		if(u.size()== 1) {
			return u;
		}
		
		boolean notsorted = true;
		boolean sorting = true;
		int arraylen =1;
		
		while (notsorted) {
			for(int offset=0;offset<u.size();offset+=(arraylen*2)) {
				
				//This takes two subarrays of length *arraylen* and puts them into t1 and  t2
				for(int i=0;i<arraylen;i++) {
					try {
					t1.get(i).set(0,u.get(offset+i).get(0));
					t1.get(i).set(1,u.get(offset+i).get(1));
					} catch(Exception e) {
						//if the subarrays aren't equal then pad the smaller one with -1s
						t1.get(i).set(0, -1);
						t1.get(i).set(1, -1);
					}
					try {
						t2.get(i).set(0,u.get(offset+i+arraylen).get(0));
						t2.get(i).set(1,u.get(offset+i+arraylen).get(1));
					} catch (Exception e) {
						//if the subarrays aren't equal then pad the smaller one with -1s
						t2.get(i).set(0, -1);
						t2.get(i).set(1, -1);
					}
				}
				//System.out.println("Arraylen - "+arraylen);
				//System.out.println("T1 - "+t1);
				//System.out.println("T2 - "+t2);
				int a=0,b=0,i=0;
				while(sorting) {
					//Checks if both subarrays have been sorted
						if((a == arraylen && b == arraylen)  || (t1.get(a).get(0) == -1  && t2.get(b).get(0) == -1) || (a == arraylen && t2.get(b).get(0)== -1) || (t1.get(a).get(0) == -1 && b == arraylen)) {
							sorting = false;
						}
						//If one array has been fully merge while the other one hasn't then: 
						else if ((t1.get(a).get(0) == -1 || t2.get(b).get(0) == -1) || (a==arraylen || b == arraylen)) {
							if(t2.get(b).get(0) == -1 || b == arraylen) {
								u.get(i+offset).set(0, t1.get(a).get(0));
								u.get(i+offset).set(1, t1.get(a).get(1));
								a+=1;
								i+=1;
							} else if(t1.get(a).get(0) == -1 || a == arraylen) {
								u.get(i+offset).set(0, t2.get(b).get(0));
								u.get(i+offset).set(1, t2.get(b).get(1));
								b+=1;
								i+=1;
							}
						}
						//If the number at a in the first array is bigger of equal
						else if(t1.get(a).get(0) <= t2.get(b).get(0)) {
							
							//If the x values are equal then compare the y values
							if(t1.get(a).get(0) == t2.get(b).get(0)) {
								if(t1.get(a).get(1) <= t2.get(b).get(1)){
									u.get(i+offset).set(0, t1.get(a).get(0));
									u.get(i+offset).set(1, t1.get(a).get(1));
									a+=1;
									i+=1;
								} else {
									u.get(i+offset).set(0, t2.get(b).get(0));
									u.get(i+offset).set(1, t2.get(b).get(1));
									b+=1;
									i+=1;
								}
							} 
							else {
							u.get(i+offset).set(0, t1.get(a).get(0));
							u.get(i+offset).set(1, t1.get(a).get(1));
							a+=1;
							i+=1;
							}
							
						//If the number at b in secondsubarray is smaller
						} else if(t1.get(a).get(0)> t2.get(b).get(0)) {
							u.get(i+offset).set(0, t2.get(b).get(0));
							u.get(i+offset).set(1, t2.get(b).get(1));
							b+=1;
							i+=1;
						}
				}
				sorting = true;
				
				//t1.clear();
				//t2.clear();
				
			}
			//After all subarrays have been merged the double the size of the subarrays to be merged
			arraylen*=2;
			if(arraylen >= u.size()) {
				notsorted = false;
			}
			
			
		}
		
		return u;
		
	}
	public ArrayList<ArrayList<Integer>> init2DList(int num1,int num2) {
		ArrayList<ArrayList<Integer>> coord = new ArrayList<>();
		for(int i=0;i<num1;i++) {
			coord.add(new ArrayList<Integer>());
			for(int j=0;j<num2;j++) coord.get(i).add(0);
		}
		return coord;
	}
	public void addCoord(float bx,float by, int value) {
		int x = convertPointToCoord(bx);
		int y = convertPointToCoord(by);
		coord.get(x).set(y, value);
		
	}
	public ArrayList<ArrayList<Integer>> getCoord(){
		return coord;
	}
	public void removeCoord(float bx, float by) {
		int x = convertPointToCoord(bx);
		int y = convertPointToCoord(by);
		coord.get(x).set(y,0);
	}
	public int convertPointToCoord(float bx) {
		int x  = Math.round((bx-16)/26);
		return x;
	}
	public float convertCoordToPoint(int x) {
		float bx = (x*26)+16;
		return bx;
	}
	public ArrayList<ArrayList<Integer>> getWritableCoord(ArrayList<ArrayList<Integer>> a ){
		for(int i=0;i<coord.size();i++) {
			for(int j=0;j<coord.get(0).size();j++) {
				a.get(i).set(j, coord.get(i).get(j));
			}
		}
		return a;
	}
}
