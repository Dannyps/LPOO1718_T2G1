package gpi1;

public class GPI1 {

	public static void main(String[] args) {
		System.out.println("Start!");
				
		try {
			Gameplay game = new Gameplay();
			while(!game.gameEnd) game.refresh();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}	
