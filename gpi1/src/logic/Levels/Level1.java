package logic.Levels;
import logic.MapEntities.*;
import logic.MapEntities.Guard.Guard;
import logic.MapEntities.Guard.RookieGuard;


/**
 * Level 1 (1 guard, no ogres)
 * 
 */
public class Level1 extends Map {	
	public Level1() throws Exception {
		super("XXXXXXXXXXXH  I X GXXXX XXX  XX I I X  XXXX XXX  XI        XI        XXXX XXXX XX I I XK XXXXXXXXXXX");
		
		
		// specify exit doors
		exitDoors.add((Door)this.map[5][0]);
		exitDoors.add((Door)this.map[6][0]);
	}

	@Override
	public void heroMetLeverHandler() {
		exitDoors.forEach(door -> door.open());
	}

	@Override
	public void heroMetKeyHandler() {
		// on this level there are no keys
		// TODO maybe throw exception?
		return;
	}

	@Override
	public boolean heroMetDoorHandler(Door door) {
		if(door.isOpen())
			levelIsOver=true; 
		return false;
	}

	@Override
	public Map getNextLevel() throws Exception {
		return null; //new Level2();
	}

	@Override
	protected GenericMapEntity parseK(int line, int column) {
		return new Lever(line, column);
	}

	@Override
	protected Guard parseGuard(int line, int column) {
		this.guard = new RookieGuard(line, column, "assssaaaaaasdddddddwwwww");
		return this.guard;
	}

}
