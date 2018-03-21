package logic.MapEntities;

import logic.Levels.Map;

/**
 * The walls delimit the movable area and create impenetrable barriers.
 */
public class Wall extends GenericMapEntity {

		
	public Wall(int x, int y, Map map) {
		super(x, y, map);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "X";
	}

}
