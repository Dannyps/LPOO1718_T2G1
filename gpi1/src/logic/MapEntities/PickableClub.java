package logic.MapEntities;

import logic.Levels.Map;

public class PickableClub extends GenericMapEntity {

	public PickableClub(int x, int y, Map map) {
		super(x, y, map);
	}

	@Override
	public String toString() {
		return "A";
	}

}
