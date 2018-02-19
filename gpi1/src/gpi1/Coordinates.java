package gpi1;

public class Coordinates {
	int x, y;
	/**
	 * a pair holding coordinates
	 * @param x (0-based)
	 * @param y (0-based)
	 */
	public Coordinates(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Coordinates(int[] c) {
		this.x = c[0];
		this.y = c[1];
	}
	
	// getters
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	// setters
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}	
	
	public Coordinates clone() {
		return new Coordinates(x,y);
	}
}
