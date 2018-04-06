package logic.MapEntities;

import logic.Levels.Map;

/**
 * Represents a club that the hero can pick, which will be used to stun nearby ogres.
 *
 */
public class PickableClub extends GenericMapEntity {
	/**
	 * @see GenericMapEntity#GenericMapEntity(int x, int y, Map map)
	 */
	public PickableClub(int x, int y, Map map) {
		super(x, y, map);
	}
	
	/**
	 * {@inheritDoc}
	 * @return "A"
	 */
	@Override
	public String toString() {
		return "A";
	}

}
