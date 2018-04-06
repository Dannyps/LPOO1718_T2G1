package logic.MapEntities.Guard;

import logic.Coordinates;
import logic.Direction;
import logic.Levels.Map;
import logic.MapEntities.Empty;
import logic.MapEntities.GenericMapEntity;
import logic.MapEntities.Hero;

/**
 * A semi-self-aware entity, that will kill the hero on contact. 
 */
public class Guard extends GenericMapEntity {
	
	// Predefined path
	protected char[] route;
	
	// Current path index
	protected int index;
	
	/**
	 * @see GenericMapEntity#GenericMapEntity(int x, int y, Map map)
	 */
	public Guard(int x, int y, Map map) {
		super(x, y, map);
		route = "assssaaaaaasdddddddwwwww".toCharArray(); // set predefined route
		index = 0;
	}
	
	/** 
	 * Tries to move the guard on the map in a specific direction (pre-defined).
	 * @param c a character representing the way the guard should move.
	 */
	protected void moveGuard(char c) {
		GenericMapEntity futurePos = getFuturePosOccupier(c);
		
		if(futurePos instanceof Empty) {
			Coordinates curr, next;
			curr = this.getCoordinates();
			next = futurePos.getCoordinates();
			
			this.map.getMap()[curr.x][curr.y] = new Empty(curr.x, curr.y, map);
			this.map.getMap()[next.x][next.y] = this;
			this.setCoordinates(next);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean tick() {
		this.moveGuard(route[index]);
		if(index == route.length - 1) 
			index = 0;
		else 
			index++;
		
		// check if guard has caught the hero.
		for (Direction dir : Direction.values()) {
			  if(this.getNeighbor(dir) instanceof Hero) {
				  //caught!
				  this.map.setGameIsOver(true);
			  }
		}
		return true;
	}
	
	/**
	 * {@inheritDoc}
	 * @return "G"
	 */
	@Override
	public String toString() {
		return "G";
	}
}
