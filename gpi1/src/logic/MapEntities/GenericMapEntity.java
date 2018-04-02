package logic.MapEntities;

import logic.Coordinates;

/**
 * All entities in the map descent from this one.
 */
public abstract class GenericMapEntity {

	protected Coordinates coordinates;
	
	/**
	 * @param x the x position of the entity
	 * @param y the y position of the entity
	 */
	public GenericMapEntity(int x, int y) {
		this.coordinates = new Coordinates(x, y);
	}
	
	/**
	 * @return The current coordinates
	 */
	public Coordinates getCoordinates() {
		return coordinates;
	}
	
	/**
	 * Updates the entity coordinates
	 * @param coordinates The new coordinates
	 */
	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}
	
	/**
	 * @see Updates the entity coordinates
	 * @param c An array of integers representing the new x and y coordinates e.g. [x,y]
	 */
	public void setCoordinates(int[] c) {
		this.coordinates = new Coordinates(c);
	}
		
	/**
	 * @return the one-char representation of the entity
	 */
	public abstract String toString();
	
	/**
	 * Returns the neighbor at some direction
	 * @param d Direction to return
	 * @return the neighbor on the specified direction, or null if neighbor is outside of the map. 
	 */
	/*
	public GenericMapEntity getNeighbor(Direction d){
		switch(d) {
		case LEFT:
			if(this.coordinates.y-1<0)
				return null;
			else
				return map.map[this.coordinates.x][this.coordinates.y-1];
		case RIGHT:
			if(this.coordinates.y+1>=map.getGridSize())
				return null;
			else
				return map.map[this.coordinates.x][this.coordinates.y+1];
		case TOP:
			if(this.coordinates.x-1<0)
				return null;
			else
				return map.map[this.coordinates.x-1][this.coordinates.y];
		case BOTTOM:
			if(this.coordinates.x+1>=map.getGridSize())
				return null;
			else
				return map.map[this.coordinates.x+1][this.coordinates.y];
		default:
			return null;
		}
	}
	*/
	
	// probably will be obsolete
	public boolean tick() {
		return true;
	}
}
