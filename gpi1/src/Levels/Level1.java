package Levels;
import MapEntities.*;

public class Level1 extends Map {

	public Level1() throws Exception {
		super("XXXXXXXXXXXH  I X GXXXX XXX  XX I I X  XXXX XXX  XI        XI        XXXX XXXX XX I I XK XXXXXXXXXXX");
		
		// specify exit doors
		exitDoors.add((Door)this.map[5][0]);
		exitDoors.add((Door)this.map[5][0]);
	}

}
