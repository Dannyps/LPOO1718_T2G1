package logic.MapEntities;

import logic.Direction;
import logic.Levels.Map;

/**
 * The Ogre's club is a dangerous tool used to kill the hero.
 */
public class OgreClub extends GenericMapEntity {
	
	/**
	 * @see GenericMapEntity#GenericMapEntity(int x, int y, Map map)
	 */
	public OgreClub(int x, int y, Map map) {
		super(x, y, map);
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
	
	/**
	 * {@inheritDoc}
	 * @return "*"
	 */
	@Override
	public String toString() {
		return "*";
	}

}
