package MapEntities;

import Levels.Map;
import gpi1.Coordinates;
import gpi1.Direction;

public class Guard extends GenericMapEntity {
	
	private char[] route;
	private int index; 
	public Guard(int x, int y, Map map) {
		super(x, y, map);
		// TODO Auto-generated constructor stub
		route = "assssaaaaaasdddddddwwwww".toCharArray(); // set predefined route
		index = 0;
	}

	@Override
	public String toString() {
		return "G";
	}
	
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
			  this.map.gameIsOver=true;
			  }
		}
		
		return true;
	}
	
	/**
	 * 
	 * @param c a character representing the way the hero should move.
	 */
	private void moveGuard(char c) {
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
}
