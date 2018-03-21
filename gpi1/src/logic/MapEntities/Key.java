package logic.MapEntities;

import logic.Levels.Map;

/**
 * Keys spawn in some levels and are used to unlock the door to the next level.
 */
public class Key extends GenericMapEntity {

	public Key(int x, int y, Map map) {
		super(x, y, map);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "K";
	}

}
