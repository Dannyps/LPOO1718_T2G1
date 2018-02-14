package gpi1;

public class Map {
	private char[][] map;
	
	public Map(String str) {
		// Assuming the map is a square
		int n = (int) Math.sqrt(str.length());

		map = new char[n][n];
		
		for(int i = 0; i < n; i++) {
			map[i] = str.substring(i*n, (i+1)*n).toCharArray();
		}
	}
	
	public String toString() {
		return  "┌─┬─┬─┬─┬─┬─┬─┬─┬─┬─┐\n" + 
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
