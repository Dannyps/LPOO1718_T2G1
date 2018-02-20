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
		switch(c) {
		case 'w':
				GenericMapEntity top = this.getNeighbor(Direction.TOP);
				if(top instanceof Wall)
				break;
		case 's':
				nextPos.y++;
				break;
		case 'a':
				nextPos.x--;
				break;
		case 'd':
				nextPos.x++;
				break;
		}
		
		// If the new coordinates are not a wall nor a door, update coordinates
		String nextPosEntity = map.getEntityAtPos(nextPos.x, nextPos.y);
		if(nextPosEntity != "X" && nextPosEntity != "I") {
			this.setCoordinates(nextPos);
		}
	}

}
