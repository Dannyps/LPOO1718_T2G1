package logic;

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
	
	public boolean equals(Coordinates c) {
		return (this.x == c.x && this.y == c.y);
	}
	
	public String toString() {
		return "{"+x+","+y+"}";
	}
}
