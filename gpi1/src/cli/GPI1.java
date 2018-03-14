package cli;

public class GPI1 {

	public static void main(String[] args) {
		System.out.println("Start!");
				
		try {
			Gameplay game = new Gameplay();
			while(!game.gameEnd) game.refresh((char) System.in.read());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}	
