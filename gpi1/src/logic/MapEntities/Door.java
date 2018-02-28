package logic.MapEntities;

import logic.Levels.Map;

public class Door extends GenericMapEntity {

	public boolean open;

	public Door(int x, int y, Map map) {
		super(x, y, map);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		if(this.open)
			return "S";
		else
			return "I";
	}
}
