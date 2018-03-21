package logic.MapEntities;

import logic.Levels.Map;

/**
 * Levers spawn in some levels and are use to immediately open a gateway door.
 *
 */
public class Lever extends GenericMapEntity {

	public Lever(int x, int y, Map map) {
		super(x, y, map);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "K";
	}
}
