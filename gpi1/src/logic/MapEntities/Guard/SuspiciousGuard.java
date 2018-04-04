package logic.MapEntities.Guard;

import logic.Coordinates;

public class SuspiciousGuard extends Guard {

	public SuspiciousGuard(int x, int y, String route) {
		super(x, y, route);
	}
	
	@Override
	public Coordinates nextCoordinates() {
		this.randomRevertRoute();
		
		// move the guard
		return this.moveGuard();
	}
	
	/*
	@Override
	public boolean tick() {
		randomRevertRoute();
		
		this.moveGuard(route[index]);
		if(index == route.length - 1) 
			index = 0;
		else 
			index++;
		
		// check if guard has caught the hero.
		for (Direction dir : Direction.values()) {
			  if(this.getNeighbor(dir) instanceof Hero) {
				  //caught!
				  this.map.setGameIsOver(true);
			  }
		}
		
		return true;
	}
	*/
}
