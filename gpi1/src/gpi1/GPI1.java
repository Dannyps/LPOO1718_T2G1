package gpi1;

import java.io.IOException;

public class GPI1 {

	public static void main(String[] args) {
		System.out.println("Start!");
		
		// initialize map
		/*
		Map map;
		try {
			map = new Map("XXXXXXXXXXXH  I X GXXXX XXX  XX I I X  XXXX XXX  XI        XI        XXXX XXXX XX I I XK XXXXXXXXXXX");
			System.out.println(map);
			
			char input;

			while((input = (char)System.in.read()) != 'i') {
				map.input(input);
				System.out.println(map);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		} // our board
		*/
		
		try {
			Gameplay game = new Gameplay();
			while(true) game.refresh();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}	
