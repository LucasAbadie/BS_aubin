package abadie.aubin;

import java.util.ArrayList;
import java.util.Random;

public class AIMedium extends Player {
	
	public AIMedium() {
		this.ships = new Ship[nbShips];

		for (int i = 0; i < nbShips; i++) {
			ships[i] = new Ship(sizes[i]);
		}

		this.shoots = new ArrayList<Coord>();
	}
	
	@Override
	public void initializeShips() {
		
		for (Ship s : this.getShips()) {
			
			Coord start = null;
			Coord end = null;
			int direction = -1;
			boolean validShip = false;

			while (!validShip) {
				
				Random r = new Random();
				
				direction = (r.nextInt(10 - 1 + 1) + 1) % 2;
				
				char rowStart;
				int colStart;
				
				if(direction == 0) {
					rowStart = (char) (r.nextInt(10 - s.getSize() + 1) + 'A');
					colStart = r.nextInt(10) + 1;
					start = new Coord(rowStart + Integer.toString(colStart));
					end = new Coord((char)(rowStart + s.getSize() - 1) + Integer.toString(colStart));
				} else {
					rowStart = (char) (r.nextInt((10 + 1) + 'A'));
					colStart = r.nextInt(10 - s.getSize() + 1) + 1;
					start = new Coord(rowStart + Integer.toString(colStart));
					end = new Coord(rowStart + Integer.toString(colStart + s.getSize() - 1));
				}

                validShip = this.isValidShip(s, start, end);
			}
			
			s.setSquares(start, end, direction);
		}
	}	
	
	@Override
	public Coord prepareShoot(Player e) {
		
		String shoot;

		if(this.shoots.isEmpty()) {
			shoot = this.createShoot();
			System.out.println("first shoot IA");
		}
		else {
			System.out.println("okok");
			shoot = this.createShoot();
			/*do {
				shoot = this.createShoot();
				if(this.shoots.contains(new Coord(shoot)))
					System.out.println(shoot + " is contain into array list");
					
			}while(this.shoots.contains(new Coord(shoot)));
			System.out.println("IA shoot at " + shoot);*/
		}

		return new Coord(shoot);
	}
}
