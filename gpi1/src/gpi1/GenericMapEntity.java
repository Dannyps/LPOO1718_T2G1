/**
 * 
 */
package gpi1;

/**
 * @author daniel
 *
 */
public class GenericMapEntity {

	private Coordinates coordinates;
	private String type;
	
	/**
	 * @param x the x position of the entity
	 * @param y the y position of the entity
	 * @param type the type of the entity (wall, hero, guard, etc.)
	 */
	public GenericMapEntity(int x, int y, String type, Map map) {
		this.coordinates = new Coordinates(x, y);
		this.type=type;
	}
	
	
	public Coordinates getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
}
