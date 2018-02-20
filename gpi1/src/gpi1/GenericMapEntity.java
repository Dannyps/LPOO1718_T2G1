/**
 * 
 */
package gpi1;

/**
 *
 */
public class GenericMapEntity {

	private Coordinates coordinates;
	protected Map map; // reference to game's map
	
	/**
	 * @param x the x position of the entity
	 * @param y the y position of the entity
	 * @param map a reference to the main map.
	 */
	public GenericMapEntity(int x, int y, Map map) {
		this.coordinates = new Coordinates(x, y);
		this.map=map;
	}
	
	public Coordinates getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}
	public void setCoordinates(int[] c) {
		this.coordinates = new Coordinates(c);
	}

		
	// move methods
	public void moveUp() {
		this.coordinates.y--;
	}
	
	public void moveDown() {
		this.coordinates.y++;
	}
	
	public void moveLeft() {
		this.coordinates.x--;
	}
	
	public void moveRigth() {
		this.coordinates.x++;
	}
	
	public String toString() {
		return null;
	}
	public boolean tick() {
		return true;
	}
}
