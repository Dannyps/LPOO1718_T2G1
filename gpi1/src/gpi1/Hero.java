package gpi1;

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
	private void moveHero(char c) {
		GenericMapEntity neighbor;
		switch(c) {
		case 'w':
			neighbor = this.getNeighbor(Direction.TOP);
			break;
		case 's':
			neighbor = this.getNeighbor(Direction.BOTTOM);
			break;
		case 'a':
			neighbor = this.getNeighbor(Direction.LEFT);
			break;
		case 'd':
			neighbor = this.getNeighbor(Direction.RIGHT);
			break;
		default:
			neighbor = null;
		}
		
		if(!(neighbor instanceof Wall || neighbor instanceof Door)) {
			Coordinates curr, next;
			curr = this.getCoordinates();
			next = neighbor.getCoordinates();
			
			this.map.map[curr.x][curr.y] = new Empty(curr.x, curr.y, map);
			this.map.map[next.x][next.y] = this;
			this.setCoordinates(next);
			
		}
	}

}
