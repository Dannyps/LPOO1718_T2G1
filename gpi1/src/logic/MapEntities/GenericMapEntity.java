package logic.MapEntities;

import logic.Coordinates;

/**
 * All entities in the map descent from this one.
 */
public abstract class GenericMapEntity {
	protected GenericMapEntity previousEnt = null; // a reference to the entity overlapped
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
	 * 
	 * @param e
	 */
	public void setOverlappedEntity(GenericMapEntity e) {
		this.previousEnt = e;
	}
	
	/**
	 * 
	 * @return
	 */
	public GenericMapEntity getOverlappedEntity() {
		if(this.previousEnt == null)
			this.previousEnt = new Empty(this.coordinates.getX(), this.coordinates.getY());
		return this.previousEnt;
	}
	
	// TODO
	// probably will be obsolete
	public boolean tick() {
		return true;
	}
}
