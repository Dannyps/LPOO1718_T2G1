package gpi1;

import java.util.ArrayList;
import java.util.List;

public class Map {
	public GenericMapEntity[][] map;
	private int gridSize;
	protected char buffer;
	boolean isHeroCaptured;
	boolean isHeroOnStairs;
	boolean gameIsOver;
	
	/**
	 * A direct reference to the Hero.
	 */
	private Hero hero;
	
	/**
	 * A direct reference to the Guard.
	 */
	private Guard guard;
	
	/**
	 * A direct reference to all doors on map
	 */
	private List<Door> doors;
	
	/**
	 * A direct reference to lever
	 */
	private Lever lever;
	
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
		
		// Initialize booleans
		this.isHeroCaptured = false;
		this.isHeroOnStairs = false;
		
		buildMapFromString(str);
		
	}
	/**
	 * @brief Constructs the map, filling it with the entities
	 * @param str A string representing the map
	 */
	private void buildMapFromString(String str) {
		int line=0, column=0;
		
		// initialize list
		this.doors = new ArrayList<Door>();
		
		// fill matrix
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
				this.doors.add(new Door(line, column, this));
				map[line][column] = this.doors.get(this.doors.size() - 1);
				break;
			case 'G':
				this.guard = new Guard(line, column, this);
				map[line][column] = this.guard;
				break;
			case 'O':
				map[line][column] = new Ogre(line, column, this);
				break;
			case 'K':
				this.lever = new Lever(line, column, this);
				map[line][column] = this.lever;
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
		// TODO Auto-generated method stub
		this.buffer = input;
		/*
		for(GenericMapEntity[] mapL: map) {
			for(GenericMapEntity gme:mapL) {
				if(gme==null)
					continue;
				gme.tick();
			}
		}*/
		
		/*
		for(int i = 0; i < this.gridSize; i++) {
			for(int j = 0; j < this.gridSize; j++) {
				GenericMapEntity gme = this.map[i][j];
				if(gme == null)
					continue;
				gme.tick();
				Coordinates newpos = gme.getCoordinates();
				if(i != newpos.x || j != newpos.y) {
					this.map[newpos.y][newpos.x] = gme;
					this.map[i][j] = null;
				}
			}
		}*/
		
		hero.tick();
		

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
