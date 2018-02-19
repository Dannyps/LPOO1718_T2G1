package gpi1;

public class Hero extends GenericMapEntity {

	public Hero(int x, int y, Map map) {
		super(x, y, map);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "H";
	}
	
	public boolean tick() {
		this.moveHero(map.buffer);
		return true;
	}
	
	/**
	 * 
	 * @param c a character representing the way the hero should move.
	 */
	private void moveHero(char c) {
		Coordinates heroNewPos = t.clone();
		switch(c) {
		case 'w':
				heroNewPos.moveUp();
				break;
		case 's':
				heroNewPos.moveDown();
				break;
		case 'a':
				heroNewPos.moveLeft();
				break;
		case 'd':
				heroNewPos.moveRigth();
				break;
		}
		
		// If the new coordinates are not a wall nor a door, update coordinates
		if(map[heroNewPos.getY()][heroNewPos.getX()] != 'X' && map[heroNewPos.getY()][heroNewPos.getX()] != 'I') {
			map[hero.getY()][hero.getX()] = ' ';
			hero.setX(heroNewPos.getX());
			hero.setY(heroNewPos.getY());
			map[hero.getY()][hero.getX()] = 'H';
			
		}
	}

}
