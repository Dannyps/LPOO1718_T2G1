package MapEntities;

import Levels.Map;
import gpi1.Coordinates;
import gpi1.Direction;

public class Hero extends GenericMapEntity {

	public Hero(int x, int y, Map map) {
		super(x, y, map);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "H";
	}
	
	public boolean tick() {
		this.moveHero(map.buffer);
		return true;
	}
	
	/**
	 * 
	 * @param c a character representing the way the hero should move.
	 */
	private boolean moveHero(char c) {
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
			return false;
		}
		
		if(futurePos instanceof Empty) {
			
			Coordinates curr, next;
			curr = this.getCoordinates();
			next = futurePos.getCoordinates();
			
			this.map.map[curr.x][curr.y] = new Empty(curr.x, curr.y, map);
			this.map.map[next.x][next.y] = this;
			this.setCoordinates(next);
			return true;
		}else if(futurePos instanceof Lever) {
			map.heroMetLeverHandler();
			return false;
		}
		else if(futurePos instanceof Key) {
			map.heroMetLeverHandler();
			return false;
		}
		else if(futurePos instanceof Door && ((Door)futurePos).open) {
			// the player has left the map, the game is prob over.
			map.gameIsOver=true;
			return false;
		}
		else {
			return false;
		}
	}

}
