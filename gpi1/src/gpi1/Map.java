package gpi1;

public class Map {
	private GenericMapEntity[][] map;
	private int gridSize;
	protected char buffer;
	
	/**
	 * A direct reference to the Hero.
	 */
	private Hero hero;
	
	/**
	 * A direct reference to the Guard.
	 */
	private Guard guard;
	
	/**
	 * @brief Constructor
	 * @param str A string that represents the game map
	 * @throws Exception
	 */
	public Map(String str) throws Exception {
		int strlen=str.length();
		// Assuming the map is a square
		this.gridSize = (int) Math.sqrt(strlen);
		map = new GenericMapEntity[this.gridSize][this.gridSize];
		
		// check if n is perfect square number
		this.gridSize = (int) Math.sqrt(strlen);
		if(this.gridSize*this.gridSize!=strlen) {
			throw new Exception("str doen't represent a square!");
		}
		
		buildMapFromString(str);
		
	}
	/**
	 * @param str
	 */
	private void buildMapFromString(String str) {
		int line=0, column=0;
		for(char c : str.toCharArray()) {
			if(column>=this.gridSize) {
				line++;
				column=0;
			}
			System.out.println(line + " " + column + "\n");
			switch(c) {
			case 'X':
				map[line][column] = new Wall(line, column, this);
				break;
			case 'H':
				this.hero = new Hero(line, column, this);
				map[line][column] = this.hero;
				break;
			case 'I':
				map[line][column] = new Door(line, column, this);
				break;
			case 'G':
				this.guard = new Guard(line, column, this);
				map[line][column] = this.guard;
				break;
			case 'O':
				map[line][column] = new Ogre(line, column, this);
				break;
			case 'K':
				map[line][column] = new Lever(line, column, this);
				break;
			case ' ':
				map[line][column] = null;
				break;
			default:
				System.err.println("Unrecognized char read!"); System.exit(-2);
				break;
			}
			column++;
		}

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
						if(map[i][j]==null) {ret+=" "; } else { ret+=map[i][j]; }
						ret+= "│";
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
	
	public void input(char input) {
		// TODO Auto-generated method stub
		this.buffer = input;
		for(GenericMapEntity[] mapL: map) {
			for(GenericMapEntity gme:mapL) {
				if(gme==null)
					continue;
				gme.tick();
			}
		}
	}
	
	public boolean isHeroCaptured() {
		return true;
	}
	
	public boolean isHeroOnLever() {
		return true;
	}
	
	public boolean isHeroOnStairs() {
		return true;
	}
}
