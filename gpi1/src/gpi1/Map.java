package gpi1;

public class Map {
	private char[][] map;
	private int gridSize;

	public Map(String str) {
		// Assuming the map is a square
		map = new char[this.gridSize][this.gridSize];
		this.gridSize = (int) Math.sqrt(str.length());
		
		for(int i = 0; i < this.gridSize; i++) {
			map[i] = str.substring(0, this.gridSize-1).toCharArray();
		}
	}
	
	public String toString() {
		String ret = new String();
		
		ret+="┌─┬─┬─┬─┬─┬─┬─┬─┬─┬─┐\n";
		
		/*
				"│ │ │ │ │ │ │ │ │ │ │\n" + 
				"├─┼─┼─┼─┼─┼─┼─┼─┼─┼─┤\n" + 
				"│ │ │ │ │ │ │ │ │ │ │\n" + 
				"├─┼─┼─┼─┼─┼─┼─┼─┼─┼─┤\n" + 
				"│ │ │ │ │ │ │ │ │ │ │\n" + 
				"├─┼─┼─┼─┼─┼─┼─┼─┼─┼─┤\n" + 
				"│ │ │ │ │ │ │ │ │ │ │\n" + 
				"├─┼─┼─┼─┼─┼─┼─┼─┼─┼─┤\n" + 
				"│ │ │ │ │ │ │ │ │ │ │\n" + 
				"├─┼─┼─┼─┼─┼─┼─┼─┼─┼─┤\n" + 
				"│ │ │ │ │ │ │ │ │ │ │\n" + 
				"├─┼─┼─┼─┼─┼─┼─┼─┼─┼─┤\n" + 
				"│ │ │ │ │ │ │ │ │ │ │\n" + 
				"├─┼─┼─┼─┼─┼─┼─┼─┼─┼─┤\n" + 
				"│ │ │ │ │ │ │ │ │ │ │\n" + 
				"├─┼─┼─┼─┼─┼─┼─┼─┼─┼─┤\n" + 
				"│ │ │ │ │ │ │ │ │ │ │\n" + 
				"├─┼─┼─┼─┼─┼─┼─┼─┼─┼─┤\n" + 
				"│ │ │ │ │ │ │ │ │ │ │\n" + 
				"└─┴─┴─┴─┴─┴─┴─┴─┴─┴─┘ ";
	}
	
	public void print() {
		for(int i = 0; i < map.length; i++) {
			System.out.println(map[i]);
		}
	}
}
