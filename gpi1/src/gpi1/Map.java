package gpi1;

public class Map {
	private char[][] map;
	
	public Map(String str) {
		// Assuming the map it's a square
		int n = (int) Math.sqrt(str.length());
		
		for(int i = 0; i < n; i++) {
			map[i] = str.substring(0, n-1).toCharArray();
		}
	}
}
