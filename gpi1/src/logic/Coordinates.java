package logic;

public class Coordinates {
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

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
	
	public String toString() {
		return "{"+x+","+y+"}";
	}
}
