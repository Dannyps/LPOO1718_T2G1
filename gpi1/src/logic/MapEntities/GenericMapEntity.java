/**
 * 
 */
package logic.MapEntities;

import gpi1.Coordinates;
import gpi1.Direction;
import logic.Levels.Map;

/**
 *
 */
public abstract class GenericMapEntity {

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
	
	/**
	 * Returns the neighbor at some direction
	 * @param d
	 * @return
	 */
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
		
	
	public abstract String toString();
	
	public boolean tick() {
		return true;
	}
}
