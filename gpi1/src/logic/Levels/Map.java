package logic.Levels;

import java.util.ArrayList;
import java.util.List;

import logic.MapEntities.*;

public abstract class Map {
	public GenericMapEntity[][] map;
	private int gridSize;
	
	public int getGridSize() {
		return gridSize;
	}
	public char buffer;
	boolean isHeroCaptured;
	boolean isHeroOnStairs;
	public boolean gameIsOver;
	
	/**
	 * A direct reference to the Hero.
	 */
	protected Hero hero;
	
	/**
	 * A direct reference to the Guard (if any).
	 */
	protected Guard guard;
	
	/**
	 * A direct reference to the Ogre (if any).
	 */
	protected Ogre ogre;
	
	/**
	 * A direct reference to all doors on map
	 */
	protected List<Door> exitDoors;
	
	/**
	 * A direct reference to lever
	 */
	protected Lever lever;
	
	/**
	 * A direct reference to key
	 */
	protected Key key;
	
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
		
		// Initialize entities
		this.guard = null;
		this.ogre = null;
		
		buildMapFromString(str);
		
	}
	/**
	 * @brief Constructs the map, filling it with the entities
	 * @param str A string representing the map
	 */
	private void buildMapFromString(String str) {
		int line=0, column=0;
		
		// initialize list
		this.exitDoors = new ArrayList<Door>();
		
		// fill matrix
		for(char c : str.toCharArray()) {
			if(column>=this.gridSize) {
				line++;
				column=0;
			}
			System.out.println(this.getClass().getName());
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
				this.ogre = new Ogre(line, column, this);
				map[line][column] = this.ogre;
				break;
			case 'K':
				if(this instanceof Level1) {
					this.lever = new Lever(line, column, this);
					map[line][column] = this.lever;
				}
				else if (this instanceof Level2) {
					this.key = new Key(line, column, this);
					map[line][column] = this.key;
				}
				break;
			case ' ':
				map[line][column] = new Empty(line, column, this);
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
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @return Returns the string that represents the entity at some position
	 */
	public String getEntityAtPos(int x, int y) {
		if (map[y][x] != null)
			return map[y][x].toString();
		else
			return "";
	}
	
	public void input(char input) {	
		this.buffer = input;
		hero.tick();	
	}
	
	public List<Door> getExitDoors() {
		return this.exitDoors;
	}
	
	/// Handlers return true or false, indicating whether the first entity should be moved to the futurePosition or not.
	
	public abstract boolean heroMetLeverHandler();
	public abstract boolean heroMetKeyHandler();
	public abstract boolean heroMetDoorHandler(Door door);
	
}