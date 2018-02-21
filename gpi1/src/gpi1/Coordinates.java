package gpi1;

public class Coordinates {
	public int x, y;
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
	
	
	public Coordinates clone() {
		return new Coordinates(x,y);
	}
	
	public boolean equals(Coordinates c) {
		return (this.x == c.x && this.y == c.y);
	}
}
