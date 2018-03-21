package logic.Levels;
import logic.Coordinates;
import logic.MapEntities.*;

/**
 * Level 2 (1 ogre, no guards)
 * 
 */
public class Level2 extends Map {

	Ogre ogre = ogres.get(0); // there's only one ogre in this level.
	
	public Level2() throws Exception {
		super("XXXXXXXXXI   O  KXX       XX       XX       XX       XX       XXH      XXXXXXXXXX");
		exitDoors.add((Door)map[1][0]);
	}

	@Override
	public boolean heroMetLeverHandler() {
		// this should not happen!
		assert(1==2);
		return false;
	}

	@Override
	public boolean heroMetKeyHandler() {
		hero.hasKey=true;
		Coordinates cKC = key.getCoordinates(); // current Key Coordinates
		map[cKC.x][cKC.y]= new Empty(cKC.x, cKC.y, this); // make the old Key position become empty
		return true;
	}

	@Override
	public boolean heroMetDoorHandler(Door door) {
		if(!door.open && hero.hasKey) {
			door.open=true;
			return false;
		}else if(door.open) {
			this.levelIsOver=true;
			return true;
		}else {
			return false;
		}
	}
	
	@Override
	public void input(char input) {
		// check club position
		if(ogre.club!=null)
			ogre.club.tick();
		
		// move hero
		super.input(input);
		
		// move ogre
		ogre.tick();
	}

	@Override
	public Map getNextLevel() {
		try {
			return new Level3();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
