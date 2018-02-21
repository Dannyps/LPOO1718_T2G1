package Levels;
import MapEntities.*;
import gpi1.Coordinates;

public class Level2 extends Map {

	boolean heroHasKey=false;
	
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
		heroHasKey=true;	
		Coordinates cKC = key.getCoordinates(); // current Key Coordinates
		map[cKC.x][cKC.y]= new Empty(cKC.x, cKC.y, this); // make the old Key position become empty
		return true;
	}

	@Override
	public boolean heroMetDoorHandler(Door door) {
		if(!door.open && heroHasKey) {
			door.open=true;
			return false;
		}else
			return true;
	}

}
