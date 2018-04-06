package logic.Levels;

import logic.MapEntities.*;

/**
 * Level 1 (1 guard, no ogres)
 * 
 */
public class Level1 extends Map {
	
	/**
	 * Constructor
	 * @param ma The arguments defined by user on GUI
	 * @throws Exception Exception If the pre-defined is not a valid map
	 */
	public Level1(MapArgs ma) throws Exception {
		super("XXXXXXXXXXXH  I X GXXXX XXX  XX I I X  XXXX XXX  XI        XI        XXXX XXXX XX I I XK XXXXXXXXXXX", ma);
		// specify exit doors
		exitDoors.add((Door) this.map[5][0]);
		exitDoors.add((Door) this.map[6][0]);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void input(char input) {
		super.input(input);
		guards.get(0).tick();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean heroMetDoorHandler(Door door) {
		if (door.isOpen())
			levelIsOver = true;
		return false;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map getNextLevel() throws Exception {
		return new Level2(this.args);
	}

}
