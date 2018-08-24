package test;

/**
 * A class used for plotting points in 2d space. These can be used to test a neural network (ie. categorize which side of a line a point falls)
 * 
 * @author matt frost
 *
 */
public class Point {
	private int x;
	private int y;
	private int value;
	/**
	 * create random point
	 */
	public Point() {
		x = (int)(Math.random()*400);
		y = (int)(Math.random()*400);

		if(Math.pow(x-200, 2)/50 + Math.pow(y-200, 2)/50 > 200) {
			value = 1;
		}else {
			value = 0;
		}
	}
	/**
	 * 
	 * @param x
	 * @param y
	 */
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
