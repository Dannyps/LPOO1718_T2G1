package logic.Levels;
import logic.Coordinates;
import logic.MapEntities.*;
import logic.MapEntities.Guard.Guard;

/**
 * Level 2 (1 ogre, no guards)
 * 
 */
public class Level2 extends Map {
	
	public Level2() throws Exception {
		super("XXXXXXXXXI   O  KXX       XX       XX       XX       XX       XXH      XXXXXXXXXX");
		// specify exit doors
		exitDoors.add((Door)map[1][0]);
	}

	@Override
	public void heroMetLeverHandler() {
		// this should not happen!
		// TODO maybe throw exception ?
		return;
	}

	@Override
	public void heroMetKeyHandler() {
		hero.setHasKey();
		Coordinates cKC = key.getCoordinates(); // current Key Coordinates
		map[cKC.getX()][cKC.getY()]= new Empty(cKC.getX(), cKC.getY()); // make the old Key position become empty
	}

	@Override
	public boolean heroMetDoorHandler(Door door) {
		if(!door.isOpen() && hero.hasKey()) {
			door.open();
			return true;
		}
		else if(door.isOpen()) {
			this.levelIsOver=true;
			return true;
		}
			
		return false;
	}
	
	@Override
	public Map getNextLevel() {
		return null;
		/*
		try {
			return new Level3();
		} catch (Exception e) {
			return null;
		}
		*/
	}

	@Override
	protected GenericMapEntity parseK(int line, int column) {
		this.key = new Key(line, column);
		return this.key;
	}

	@Override
	protected Guard parseGuard(int line, int column) {
		return null;
	}

}
