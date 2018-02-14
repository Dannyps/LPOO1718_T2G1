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
	
	public void print() {
		for(int i = 0; i < map.length; i++) {
			System.out.println(map[i]);
		}
	}
}
