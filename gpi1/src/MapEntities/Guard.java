package MapEntities;

import Levels.Map;
import gpi1.Coordinates;

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
		
		return true;
	}
	
	/**
	 * 
	 * @param c a character representing the way the hero should move.
	 */
	private void moveGuard(char c) {
		Coordinates nextPos = this.getCoordinates().clone();
		switch(c) {
		case 'w':
				nextPos.y--;
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
