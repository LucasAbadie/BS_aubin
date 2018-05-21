package abadie.aubin;
import java.util.Scanner;

public class BattleShip {
	
	public static void main(String[] args) {
		
		Player p1;
		Player p2;
		int round = 0;// rounds number 
		int p1Score = 0;// p1's wins count
		int p2Score = 0;// p1's wins count
		int gameMode = 0; // 0 : Human vs Human 
						  // 1 : Human vs IA
		int difficulty = -1; // 0 : Easy 
							 // 1 : Medium 
							 // 2 : Hard
		Scanner reader = new Scanner(System.in);
		
		// Choose game mode : Human, AIEasy, AIMedium, AIHard
		while (true) {
			
			
			System.out.println("Which game type? " + "\n 0 : Human vs Human \n 1 : Human vs IA");
			String t = reader.nextLine();
			
			if (!t.equals("0") && !t.equals("1")) {
				System.out.println("Choose a correct type...");
			} else {
				gameMode = Integer.parseInt(t);
				break;
			}
		}
		
		// Choose p2 type : Human, AIEasy, AIMedium, AIHard
		if (gameMode == 1) {
			while (true) {
				System.out.println("Which AI difficult? ? " + "\n 0 : Easy \n 1 : Medium \n 2 : Hard");
				String d = reader.nextLine();
				
				if (!d.equals("0") && !d.equals("1") && !d.equals("2")) {
					System.out.println("Choose a correct level...");
				}
				else {
					difficulty = Integer.parseInt(d);
					break;
				}
			}
		}
		
		while(true) {
			
			round++;
			int first = round%2;
			
			switch(gameMode) {
				case 0:
					System.out.println("\nJoueur 1 :");
					p1 = new Human();
					p1.initializeShips();
					System.out.println("\nJoueur 2 :");
					p2 = new Human();
					p2.initializeShips();
					/*p1 = new Human();
					Ship[] ships1 = p1.getShips();
					ships1[0].setSquares(new Coord("A1"), new Coord("A5"), 1);
					ships1[1].setSquares(new Coord("C3"), new Coord("F3"), 0);
					ships1[2].setSquares(new Coord("C8"), new Coord("E8"), 0);
					ships1[3].setSquares(new Coord("H5"), new Coord("H7"), 1);
					ships1[4].setSquares(new Coord("G10"), new Coord("H10"), 0);
					p2 = new Human();
					Ship[] ships2 = p2.getShips();
					ships2[0].setSquares(new Coord("A1"), new Coord("A5"), 1);
					ships2[1].setSquares(new Coord("C3"), new Coord("F3"), 0);
					ships2[2].setSquares(new Coord("C8"), new Coord("E8"), 0);
					ships2[3].setSquares(new Coord("H5"), new Coord("H7"), 1);
					ships2[4].setSquares(new Coord("G10"), new Coord("H10"), 0);*/
					break;
					
				case 1:
					System.out.println("\nJoueur 1 :");
					p1 = new Human();
					Ship[] ships1 = p1.getShips();
					ships1[0].setSquares(new Coord("A1"), new Coord("A5"), 1);
					ships1[1].setSquares(new Coord("C3"), new Coord("F3"), 0);
					ships1[2].setSquares(new Coord("C8"), new Coord("E8"), 0);
					ships1[3].setSquares(new Coord("H5"), new Coord("H7"), 1);
					ships1[4].setSquares(new Coord("G10"), new Coord("H10"), 0);
					//p1.initializeShips();
					System.out.println("\nAI initialization...");
					switch(difficulty) {
						case 0:
							p2 = new AIEasy();
							break;
						case 1:
							p2 = new AIMedium();
							break;
						case 2:
							p2 = new AIHard();
							break;
						default:
							p2 = new AIMedium();
							break;
					}
					p2.initializeShips();
					/*Ship[] ships2 = p2.getShips();
					ships2[0].setSquares(new Coord("A1"), new Coord("A5"), 1);
					ships2[1].setSquares(new Coord("C3"), new Coord("F3"), 0);
					ships2[2].setSquares(new Coord("C8"), new Coord("E8"), 0);
					ships2[3].setSquares(new Coord("H5"), new Coord("H7"), 1);
					ships2[4].setSquares(new Coord("G10"), new Coord("H10"), 0);*/
					System.out.println("\nAI ready!");
					p2.printGrid(p1);
					break;
					
				default:
					System.out.println("\nJoueur 1 :");
					p1 = new Human();
					p1.initializeShips();
					System.out.println("\nJoueur 2 :");
					p2 = new Human();
					p2.initializeShips();
					break;
			}
			
			while (true) {

				System.out.println("\nPlayer 1, which target?");
				shoot(p1, p2, p1.prepareShoot(p2));

				System.out.println("\nAI is choosing target...");
				shoot(p2, p1, p2.prepareShoot(p1));

				if (p1.hasLost()) {
					System.out.println("Joueur 2 l'emporte !");
					break;
				} else if (p2.hasLost()) {
					System.out.println("Joueur 1 l'emporte !");
					break;
				}
			}
		}
	}
	
	public static void shoot(Player p, Player e, Coord shoot) {
		
		if(e.getShipAtCoord(shoot) != null) {
			Ship s = e.getShipAtCoord(shoot);
			if (s.isHit(shoot)) {
				System.out.println("Already hit " + shoot.toString());
			} else {
				System.out.println("Hit in " + shoot.toString());
				p.hitShip(s, shoot);
				if (s.isDestroyed()) {
					System.out.println("Hit and sank!");
				}
			}
		} else {
			if(!p.getShoots().isEmpty()) {
				for(Coord c : p.getShoots()) {
					if(c.equals(shoot)) {
						System.out.println("Already hit " + shoot.toString());
						break;
					} else {
						System.out.println("Miss in " + shoot.toString());
						p.addShoot(shoot);
					}
				}
			} else {
				System.out.println("Miss in " + shoot.toString());
				p.addShoot(shoot);
			}
		}
	}
}
