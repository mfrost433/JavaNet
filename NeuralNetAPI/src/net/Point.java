package net;

public class Point {
	int x;
	int y;
	int value;
	public Point() {
		x = (int)(Math.random()*400);
		y = (int)(Math.random()*400);
		
		if(Math.pow(x-200,2)/50 + Math.pow(y-200,2)/50 < 300) {
			value = 1;
		}else {
			value = 0;
		}
	}
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
		
		if(x >= y + 100) {
			value = 1;
		}else {
			value = 0;
		}
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getValue() {
		return value;
	}
}
