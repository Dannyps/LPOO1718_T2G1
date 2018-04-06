package logic;

public class Coordinates {

	public int x, y;

	/**
	 * a pair holding coordinates
	 * 
	 * @param x (0-based)
	 * @param y (0-based)
	 */
	public Coordinates(int x, int y) {
		this.x = x;
		this.y = y;
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

	public boolean equals(Coordinates c) {
		return (this.x == c.x && this.y == c.y);
	}

	public String toString() {
		return "{" + x + "," + y + "}";
	}
}
