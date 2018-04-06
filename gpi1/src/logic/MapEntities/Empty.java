package logic.MapEntities;

import logic.Levels.Map;

/**
 * The void in the map
 */
public class Empty extends GenericMapEntity {
	
	/**
	 * @see GenericMapEntity#GenericMapEntity(int x, int y, Map map)
	 */
	public Empty(int x, int y, Map map) {
		super(x, y, map);
	}
	
	/**
	 * {@inheritDoc}
	 * @return " "
	 */
	@Override
	public String toString() {
		return " ";
	}

}
