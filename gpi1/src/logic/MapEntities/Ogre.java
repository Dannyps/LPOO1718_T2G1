package logic.MapEntities;

import java.util.Random;

import gpi1.Coordinates;
import gpi1.Direction;
import logic.Levels.Map;

public class Ogre extends GenericMapEntity {
	
	/**
	 * Should only be set if the ogre is over a key.
	 */
	private Key oldKey=null;

	/**
	 * 
	 * @param x
	 * @param y
	 * @param map reference to the map the ogre is in.
	 */
	public Ogre(int x, int y, Map map) {
		super(x, y, map);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		if(oldKey==null)
			return "O";
		else
			return "$";
	}
	
	public boolean tick() {
		moveOgre();
		// check if ogre has caught the hero.
		for (Direction dir : Direction.values()) {
			  if(this.getNeighbor(dir) instanceof Hero) {
			  //caught!
			  this.map.gameIsOver=true;
			  }
		}
		
		return true;
	}
	
	/**
	 * 
	 * @param c a character representing the way the hero should move.
	 */
	private void moveOgre() {
		GenericMapEntity futurePos = null; // the desired position's current occupier
		int i = new Random().nextInt(4);
		
		switch(i) {
		case 0:
			futurePos = this.getNeighbor(Direction.TOP);
			break;
		case 1:
			futurePos = this.getNeighbor(Direction.BOTTOM);
			break;
		case 2:
			futurePos = this.getNeighbor(Direction.LEFT);
			break;
		case 3:
			futurePos = this.getNeighbor(Direction.RIGHT);
			break;
		}
		
		if(futurePos instanceof Empty) {
			Coordinates curr, next;
			curr = this.getCoordinates();
			next = futurePos.getCoordinates();
			
			if(oldKey==null)
				this.map.map[curr.x][curr.y] = new Empty(curr.x, curr.y, map);
			else {
				this.map.map[curr.x][curr.y] = oldKey;
				oldKey=null;
			}
			this.map.map[next.x][next.y] = this;
			this.setCoordinates(next);
		}
		
		if(futurePos instanceof Key) {
			oldKey= (Key) futurePos;
			Coordinates curr, next;
			curr = this.getCoordinates();
			next = futurePos.getCoordinates();
			
			this.map.map[curr.x][curr.y] = new Empty(curr.x, curr.y, map);
			this.map.map[next.x][next.y] = this;
			this.setCoordinates(next);
		}
	}

}