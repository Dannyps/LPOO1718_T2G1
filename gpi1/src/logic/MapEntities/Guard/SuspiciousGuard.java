package logic.MapEntities.Guard;

import java.util.Random;

import logic.Direction;
import logic.Levels.Map;
import logic.MapEntities.Hero;

public class SuspiciousGuard extends Guard {

	public SuspiciousGuard(int x, int y, Map map) {
		super(x, y, map);
	}
	
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
	
	protected void randomRevertRoute() {
		if (new Random().nextBoolean()) {
			// reverse route
			for (int i = 0; i < route.length / 2; i++) {
				char temp = route[i];
				route[i] = route[route.length - i - 1];
				route[route.length - i - 1] = temp;
			}
		}
	}
}
