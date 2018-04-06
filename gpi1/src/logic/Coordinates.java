package logic;

/**
 * A class to represent coordinates in a cartesian system
 */
public class Coordinates {

	public int x, y;

	/**
	 * Constructor
	 * 
	 * @param x (0-based)
	 * @param y (0-based)
	 */
	public Coordinates(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * @return True if the object is equal, i.e. the coordinates are equal
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinates other = (Coordinates) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	
	/**
	 * @param c 
	 * @return True if the coordinates are equal, False otherwise 
	 */
	public boolean equals(Coordinates c) {
		return (this.x == c.x && this.y == c.y);
	}
	
	/**
	 * A string representation of the coordinates {x,y}
	 */
	public String toString() {
		return "{" + x + "," + y + "}";
	}
	
	/**
	 * Calculates the distance between two coordinates on the cartesian system
	 * @param c1
	 * @param c2
	 * @return The distance
	 */
	static public double distance(Coordinates c1, Coordinates c2) {
	    return  Math.hypot(c1.x-c2.x, c1.y-c2.y);		
	}
}
