package logic.MapEntities.Guard;

import logic.Coordinates;

public class RookieGuard extends Guard {

	public RookieGuard(int x, int y, String route) {
		super(x, y, route);
	}
	
	public Coordinates nextCoordinates() {
		return this.moveGuard();
	}

}
