package gpi1;

public class GPI1 {

	public static void main(String[] args) {
		System.out.println("Start!");
		
		// initialize map
		Map map;
		try {
			map = new Map("XXXXXXXXXXXH  I X GXXXX XXX  XX I I X  XXXX XXX  XI        XI        XXXX XXXX XX I I XK XXXXXXXXXXX");
			System.out.println(map);
			
			map.moveHero('d');
			System.out.println(map);
		} catch (Exception e) {
			e.printStackTrace();
		} // our board
		
		

	}
}	
