package logic.MapEntities.Guard;

import java.util.Random;

import logic.Coordinates;
import logic.MapEntities.GenericMapEntity;

/**
 * A semi-self-aware entity, that will kill the hero on contact. 
 */
public abstract class Guard extends GenericMapEntity {
	
	protected char[] route; // predefined route, defined with 'a', 's', 'd' and 'w'
	protected int index; // index of next move
	
	public Guard(int x, int y, String route) {
		super(x, y);
		this.route = route.toCharArray(); // set predefined route
		index = 0;
	}
	
	/**
	 * Returns the next guard desired position based pre-defined route
	 * @return
	 */
	protected Coordinates moveGuard() {
		Coordinates next = this.coordinates.clone();		
		next.move(this.route[index++]);
		
		// reached end of route ?
		if(index == route.length - 1) {
			index = 0;
		}
		
		return next;
	}
	
	/**
	 * Returns the next guard desired position
	 * Each guard personality moves differently and upon different conditions, so each must implement this method 
	 * @return 
	 */
	public abstract Coordinates nextCoordinates();
	
	/**
	 * Randomly reverses the guard route
	 */
	protected void randomRevertRoute() {
		if (new Random().nextBoolean()) {
			// reverse route
			for (int i = 0; i < route.length / 2; i++) {
				char temp = route[i];
				route[i] = route[route.length - i - 1];
				route[route.length - i - 1] = temp;
			}
		}
	}
	
	@Override
	public String toString() {
		return "G";
	}
	
	/*
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
	*/
	/**
	 * 
	 * @param c a character representing the way the hero should move.
	 */
	/*
	protected void moveGuard(char c) {
		GenericMapEntity futurePos; // the desired position's current occupier
		switch(c) {
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
		
		if(futurePos instanceof Empty) {
			Coordinates curr, next;
			curr = this.getCoordinates();
			next = futurePos.getCoordinates();
			
			this.map.map[curr.x][curr.y] = new Empty(curr.x, curr.y, map);
			this.map.map[next.x][next.y] = this;
			this.setCoordinates(next);
		}
	}
	*/
}
