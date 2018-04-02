package logic.MapEntities;

/**
 * The void in the map.
 */
public class Empty extends GenericMapEntity {
	
	public Empty(int x, int y) {
		super(x, y);
	}
	
	@Override
	public String toString() {
		return " ";
	}

}
