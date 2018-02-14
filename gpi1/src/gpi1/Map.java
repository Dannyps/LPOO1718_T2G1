package gpi1;

public class Map {
	private char[][] map;
	private int gridSize;
	private Coordinates hero;
	
	public Map(String str) {
		// Assuming the map is a square
		this.gridSize = (int) Math.sqrt(str.length());
		map = new char[this.gridSize][this.gridSize];
		
		// TODO check if n is perfect square number
		this.gridSize = (int) Math.sqrt(str.length());
		
		for(int i = 0; i < this.gridSize; i++) {
			map[i] = str.substring(this.gridSize*i, this.gridSize*(i+1)).toCharArray();
		}
		
		// Find hero position
		int ind = str.indexOf('H');
		hero = new Coordinates(ind/this.gridSize, ind%this.gridSize);
		
	}
	/**
	 * return a string representation of the map.
	 */
	public String toString() {
		String ret = new String();
		
		// Make Header
		{
			ret += "┌─";
			for (int i = 0; i < this.gridSize-1; i++) {
				ret += "┬─";
			}
			ret += "┐\n";
		}
		
		
		// Make Body
			{
				
				for (int i = 0; i < this.gridSize; i++) {
					ret += "│";
					for(int j=0;j<this.gridSize;j++) {
						ret+=map[i][j]+ "│";;
					}
					ret+="\n";
					// -- new line
					if(i==this.gridSize-1)
						break; // do not draw ------ under last line
					ret += "├─";
					for (int j = 0; j < this.gridSize-1; j++) {
						ret += "┼─";
					}
					ret += "┤\n";
				}
				
			}
			
		// Make Footer
			{
				ret += "└─";
				for (int i = 0; i < this.gridSize-1; i++) {
					ret += "┴─";
				}
				ret += "┘\n";
			}
		return ret;
	}
	
	/**
	 * 
	 * @param c
	 */
	public void moveHero(char c) {
		Coordinates heroNewPos = hero.clone();
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
		
		// If the new coordinates are not a wall or door, update coordinates
		if(map[heroNewPos.getX()][heroNewPos.getY()] != 'X' && map[heroNewPos.getX()][heroNewPos.getY()] != 'I') {
			hero.setX(heroNewPos.getX());
			hero.setY(heroNewPos.getY());
		}
	}
}
