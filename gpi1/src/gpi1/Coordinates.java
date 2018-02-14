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
	
	// move methods
	public void moveUp() {
		this.y--;
	}
	
	public void moveDown() {
		this.y++;
	}
	
	public void moveLeft() {
		this.x--;
	}
	
	public void moveRigth() {
		this.x++;
	}
	
	
	public Coordinates clone() {
		return new Coordinates(x,y);
	}
}
