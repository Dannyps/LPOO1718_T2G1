package logic.MapEntities;

import logic.Levels.Map;

/**
 * Represents a club that the hero can pick, which will be used to stun nearby ogres.
 *
 */
public class PickableClub extends GenericMapEntity {

	public PickableClub(int x, int y, Map map) {
		super(x, y, map);
	}

	@Override
	public String toString() {
		return "A";
	}

}
