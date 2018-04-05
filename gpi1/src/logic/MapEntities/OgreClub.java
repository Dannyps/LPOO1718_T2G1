package logic.MapEntities;

import logic.Direction;
import logic.Levels.Map;

/**
 * The Ogre's club is a dangerous tool it uses to kill the hero.
 */
public class OgreClub extends GenericMapEntity {

	public OgreClub(int x, int y, Map map) {
		super(x, y, map);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "*";
	}

	@Override
	public boolean tick() {
		// check if club is adjacent to the hero.
		for (Direction dir : Direction.values()) {
			if (this.getNeighbor(dir) instanceof Hero) {
				// caught!
				this.map.setGameIsOver(true);
				return true;
			}
		}
		// delete old club
		map.getMap()[this.getCoordinates().x][this.getCoordinates().y] = new Empty(this.getCoordinates().x,
				this.getCoordinates().y, map);
		return true;
	}

}
