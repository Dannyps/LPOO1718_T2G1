package logic.Levels;

import java.util.List;
import java.util.Random;

import logic.MapEntities.*;
import logic.MapEntities.Guard.DrunkenGuard;
import logic.MapEntities.Guard.Guard;
import logic.MapEntities.Guard.RookieGuard;
import logic.MapEntities.Guard.SuspiciousGuard;

/**
 * Level 1 (1 guard, no ogres)
 * 
 */
public class Level1 extends Map {

	public Level1(MapArgs ma) throws Exception {
		super("XXXXXXXXXXXH  I X GXXXX XXX  XX I I X  XXXX XXX  XI        XI        XXXX XXXX XX I I XK XXXXXXXXXXX", ma);
		// specify exit doors
		exitDoors.add((Door) this.map[5][0]);
		exitDoors.add((Door) this.map[6][0]);
	}
	

	@Override
	public void input(char input) {
		super.input(input);
		guards.get(0).tick();
	}

	@Override
	public boolean heroMetDoorHandler(Door door) {
		if (door.isOpen())
			levelIsOver = true;
		return false;
	}

	@Override
	public Map getNextLevel() throws Exception {
		return new Level2(this.args);
	}

}
