package logic.MapEntities;

import logic.Levels.Map;

/**
 * Keys spawn in some levels and are used to unlock the door to the next level.
 */
public class Key extends GenericMapEntity {
	/**
	 * @see GenericMapEntity#GenericMapEntity(int x, int y, Map map)
	 */
	public Key(int x, int y, Map map) {
		super(x, y, map);
	}
	
	/**
	 * {@inheritDoc}
	 * @return "K"
	 */
	@Override
	public String toString() {
		return "K";
	}

}
