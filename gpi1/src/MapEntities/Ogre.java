package MapEntities;

import java.util.Random;

import Levels.Map;
import gpi1.Coordinates;
import gpi1.Direction;

public class Ogre extends GenericMapEntity {

	public Ogre(int x, int y, Map map) {
		super(x, y, map);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "O";
	}
	
	public boolean tick() {
		moveOgre();
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
			
			this.map.map[curr.x][curr.y] = new Empty(curr.x, curr.y, map);
			this.map.map[next.x][next.y] = this;
			this.setCoordinates(next);
		}
		// TODO the ogre can also move over the key
	}

}
