package logic.Levels;
import logic.MapEntities.*;

public class Level1 extends Map {

	public Level1() throws Exception {
		super("XXXXXXXXXXXH  I X GXXXX XXX  XX I I X  XXXX XXX  XI        XI        XXXX XXXX XX I I XK XXXXXXXXXXX");
		
		// specify exit doors
		exitDoors.add((Door)this.map[5][0]);
		exitDoors.add((Door)this.map[6][0]);
	}

	@Override
	public boolean heroMetLeverHandler() {
		getExitDoors().forEach(door -> door.open = true);
		return false;
	}

	@Override
	public boolean heroMetKeyHandler() {
		// this should not happen!
		assert(1==2);
		return false;
	}

	@Override
	public void input(char input) {
		super.input(input);
		guard.tick();
	}

	@Override
	public boolean heroMetDoorHandler(Door door) {
		if(door.open)
			gameIsOver=true;
		return false;
	}

}