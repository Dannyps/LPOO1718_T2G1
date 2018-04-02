package logic.MapEntities;

/**
 * The walls delimit the movable area and create impenetrable barriers.
 */
public class Wall extends GenericMapEntity {

		
	public Wall(int x, int y) {
		super(x, y);
	}
	
	@Override
	public String toString() {
		return "X";
	}

}
