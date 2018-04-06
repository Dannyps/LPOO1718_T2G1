/**
 * 
 */
package logic.MapEntities;

import java.util.Random;

import logic.Coordinates;
import logic.Direction;
import logic.Levels.Map;

/**
 * All entities in the map descent from this one.
 */
public abstract class GenericMapEntity {

	private Coordinates coordinates;
	protected Map map; // reference to game's map
	
	/**
	 * @param x The x position of the entity on the map
	 * @param y The y position of the entity on the map
	 * @param map A reference to the main map.
	 */
	public GenericMapEntity(int x, int y, Map map) {
		this.coordinates = new Coordinates(x, y);
		this.map=map;
	}
	
	/**
	 * For a valid character representing the entity next move direction, it returns a reference to the entity on that future position 
	 * @param c A char representing the move direction ('w', 's', 'a', 'd')
	 * @return A reference to the entity to be overlapped
	 */
	protected GenericMapEntity getFuturePosOccupier(char c) {
		GenericMapEntity futurePos; // the desired position's current occupier
		switch (c) {
		case 'w':
			futurePos = this.getNeighbor(Direction.TOP);
			break;
		case 's':
			futurePos = this.getNeighbor(Direction.BOTTOM);
			break;
		case 'a':
			futurePos = this.getNeighbor(Direction.LEFT);
			break;
		case 'd':
			futurePos = this.getNeighbor(Direction.RIGHT);
			break;
		default:
			futurePos = null;
		}
		return futurePos;
	}
	
	/**
	 * Same as {@link getFuturePosOccupier(char c)}, but the move direction is random
	 * @return A reference to the entity to be overlapped (random move direction)
	 */
	public GenericMapEntity getRandomFuturePosOccupier() {
		int i = new Random().nextInt(4);
		return getFuturePosOccupier((new char[] {'w', 's', 'a', 'd'})[i]);
		
	}
	
	/**
	 * Returns the neighbor at some direction
	 * @param d Direction to return
	 * @return the neighbor on the specified direction, or null if neighbor is outside of the map. 
	 */
	public GenericMapEntity getNeighbor(Direction d){
		switch(d) {
		case LEFT:
			if(this.coordinates.y-1<0)
				return null;
			else
				return map.getMap()[this.coordinates.x][this.coordinates.y-1];
		case RIGHT:
			if(this.coordinates.y+1>=map.getGridSize())
				return null;
			else
				return map.getMap()[this.coordinates.x][this.coordinates.y+1];
		case TOP:
			if(this.coordinates.x-1<0)
				return null;
			else
				return map.getMap()[this.coordinates.x-1][this.coordinates.y];
		case BOTTOM:
			if(this.coordinates.x+1>=map.getGridSize())
				return null;
			else
				return map.getMap()[this.coordinates.x+1][this.coordinates.y];
		}
		return null;
	}
	
	/**
	 * Returns the current entity coordinates
	 * @return Current coordinates
	 */
	public Coordinates getCoordinates() {
		return coordinates;
	}
	
	/**
	 * Updates the entity coordinates
	 * @param coordinates New coordinates
	 */
	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}

	/**
	 * Returns a string that represents the entity. The same entity can have multiple representations depending on game states
	 * @return The one-char representation of the entity
	 */
	public abstract String toString();
	
	/**
	 * Updates the entity upon user interaction. For non-static entities, the entity will try to move on the map
	 * @return True if the entity could move, false otherwise.
	 */
	public boolean tick() {
		return true;
	}
}
