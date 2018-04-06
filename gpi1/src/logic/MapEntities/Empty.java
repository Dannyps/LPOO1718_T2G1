package logic.MapEntities;

import logic.Levels.Map;

/**
 * The void in the map.
 */
public class Empty extends GenericMapEntity {

	public Empty(int x, int y, Map map) {
		super(x, y, map);
	}
	
	@Override
	public String toString() {
		return " ";
	}

}
