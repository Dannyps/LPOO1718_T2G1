package logic.MapEntities;

/**
 * A door that can be opened by interacting with the map, or with itself. Some doors are gateways to other levels.
 */
public class Door extends GenericMapEntity {

	private boolean open;

	public Door(int x, int y) {
		super(x, y);
	}
	
	public void open() {
		this.open = true;
	}
	
	public boolean isOpen() {
		return this.open;
	}
	
	@Override
	public String toString() {
		if(this.open)
			return "S";
		else
			return "I";
	}
}
