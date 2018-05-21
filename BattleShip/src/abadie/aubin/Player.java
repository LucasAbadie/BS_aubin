package abadie.aubin;
import java.util.ArrayList;

public abstract class Player implements IPlayer {

	protected static final int sizes[] = { 5, 4, 3, 3, 2 };
	protected static final int nbShips = 5;

	protected Ship ships[];
	protected ArrayList<Coord> shoots;

	public Ship[] getShips() {
		return ships;
	}
	
	public void addShoot(Coord shoot) {
		this.shoots.add(shoot);
	}
	
	public ArrayList<Coord> getShoots() {
		return shoots;
	}
	
	public boolean isValidShip(Ship s, Coord start, Coord end) {
		
		if (!(start.getX() >= 'A' && start.getX() <= 'J' && start.getY() >= 1 && start.getY() <= 10)) {
			System.out.println("Invalid coordinates, please retry...\n");
			return false;
		}
		
		if (isConflictShipCoord(s)) {
			return false;
		}
		
		return true;
	}
	
	public boolean isConflictShipCoord(Ship s) {
		
		for (Ship sh : this.ships) {
			for (Coord sq : sh.getSquares()) {
				for(Coord sq2 : s.getSquares()) {
					if (sq != null && sq.equals(sq2)) {
						return true;
					}
				}	
			}
		}
		
		return false;
	}
	
	public boolean isShipAtCoord(Coord c) {
		
		for (Ship s : this.ships) {
			for (Coord sq : s.getSquares()) {
				if (sq != null && sq.equals(c)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public Ship getShipAtCoord(Coord c) {
		
		Ship res = null;
		
		for (Ship s : this.ships) {
			for (Coord sq : s.getSquares()) {
				if (sq != null && sq.equals(c)) {
					res = s;
				}
			}
		}
		return res;
	}
	
	public boolean hasHitAtCoord(Coord c) {
		for(Coord h : this.getShoots()) {
			if(h.equals(c)) {
				return true;
			}
		}
		
		return false;
	}
	
	public void hitShip(Ship s, Coord c) {

		for (Coord sq : s.getSquares()) {
			if (sq.equals(c)) {
				if (sq.isHit() == false) {
					sq.setHit(true);
				}
				this.shoots.add(sq);
			}
		}
	}

	public boolean hasLost() {
		boolean res = true;

		for (Ship s : this.ships) {
			if (s.isDestroyed() == false) {
				res = false;
			}
		}

		return res;
	}
	
	public void printGrid(Player e) {
		
		System.out.println("      1   2   3   4   5   6   7   8   9  10  ");
		System.out.println("     ___ ___ ___ ___ ___ ___ ___ ___ ___ ___ ");

		for (char i = 'A' ; i <= 'J' ; i++){
			
			System.out.print(" " + i + "  |");
			
			for (int j = 1 ; j <= 10 ; j++) {
				
				Coord pos = new Coord(i + Integer.toString(j));
				
				if(e.hasHitAtCoord(pos)) {
					if(this.isShipAtCoord(pos) && this.getShipAtCoord(pos).isHit(pos)) {
						System.out.print(" X  ");
					} else {
						System.out.print(" O  ");
					}
				} else {
					if (this.isShipAtCoord(pos)) {
						System.out.print(" 1  ");
					} else {
						System.out.print(" ~  ");
					}
				}
			}
			
			System.out.println();
		}
		
		System.out.println();
	}

	public void printShootGrid(Player e) {

		System.out.println("      1   2   3   4   5   6   7   8   9  10  ");
		System.out.println("     ___ ___ ___ ___ ___ ___ ___ ___ ___ ___ ");

		for (char i = 'A' ; i <= 'J' ; i++){
			
			System.out.print(" " + i + "  |");
			
			for (int j = 1 ; j <= 10 ; j++) {
				
				Coord pos = new Coord(i + Integer.toString(j));
				
				if(this.hasHitAtCoord(pos)) {
					if(e.isShipAtCoord(pos) && e.getShipAtCoord(pos).isHit(pos)) {
						System.out.print(" X  ");
					} else {
						System.out.print(" O  ");
					}
				} else {
					System.out.print(" ~  ");
				}
			}
			
			System.out.println();
		}
		
		System.out.println();
	}
}