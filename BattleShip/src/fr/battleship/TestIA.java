package fr.battleship;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import abadie.aubin.*;

public class TestIA {
	
	public static void main(String[] args) throws IOException {
		
		File f = new File("ai_proof.csv");
		FileWriter fw = new FileWriter(f);
		fw.append("AI Name; score; AI Name2; score2\n");
		
		for (int k = 0 ; k < 3 ; k++) {
			
			System.out.println("Game " + k);
			
			Player p1;
			Player p2;
			int p1Score = 0;
			int p2Score = 0;
			int nbGame = 10;
			int round = 0; //rounds number
			
			
			while(round < nbGame) {
				
				int first = round%2;
				
				switch (k) { 
					case 0: //AI Level Beginner; X1; AI Level Medium; Y1
						p1 = new AIEasy();
						p1.initializeShips();
						p2 = new AIMedium();
						p2.initializeShips();
						break;
					case 1: //AI Level Beginner; X2; AI Level Hard; Y2
						p1 = new AIEasy();
						p1.initializeShips();
						p2 = new AIHard();
						p2.initializeShips();
						break;
					case 2 : //AI Level Medium; X3; AI Level Hard ;Y3
						p1 = new AIMedium();
						p1.initializeShips();
						p2 = new AIHard();
						p2.initializeShips();
						break;
					default:
						p1 = new AIEasy();
						p1.initializeShips();
						p2 = new AIMedium();
						p2.initializeShips();
						break;
				}
				
				while (true) {

					if(first == 0) {
						shoot(p1, p2, p1.prepareShoot(p2));
						if (p1.hasLost()) {
							System.out.println("Player 2 wins!");
							p2Score++;
							break;
						}
						shoot(p2, p1, p2.prepareShoot(p1));
						if (p2.hasLost()) {
							System.out.println("Player 1 wins!");
							p1Score++;
							break;
						}
					} else {
						shoot(p2, p1, p2.prepareShoot(p1));
						if (p1.hasLost()) {
							System.out.println("Player 2 wins!");
							p2Score++;
							break;
						}
						shoot(p1, p2, p1.prepareShoot(p2));
						if (p2.hasLost()) {
							System.out.println("Player 1 wins!");
							p1Score++;
							break;
						}
					}
				}
				round++;
			}
			
			switch (k) {
				case 0:
					System.out.println("AI Level Beginner; " + p1Score + "; AI Level Medium; " + p2Score + "\n");
					fw.append("AI Level Beginner; " + p1Score + "; AI Level Medium; " + p2Score + "\n");
					break;
				case 1:
					System.out.println("AI Level Beginner; " + p1Score + "; AI Level Hard; " + p2Score + "\n");
					fw.append("AI Level Beginner; " + p1Score + "; AI Level Hard; " + p2Score + "\n");
					break;
				case 2:
					System.out.println("AI Level Medium; " + p1Score + "; AI Level Hard; " + p2Score + "\n");
					fw.append("AI Level Medium; " + p1Score + "; AI Level Hard; " + p2Score + "\n");
					break;
				default:
					break;
			}
		}
		fw.flush();
		fw.close();
	}
		
	public static void shoot(Player p, Player e, Coord shoot) {
		
		if(e.isShipAtCoord(shoot) ) {
			Ship s = e.getShipAtCoord(shoot);
			if (s.isHit(shoot)) {
				//System.out.println("Already hit " + shoot.toString());
			} else {
				//System.out.println("Hit in " + shoot.toString());
				p.hitShip(s, shoot);
				if (s.isDestroyed()) {
					if(p.isHasHitShip())
						p.setHasHitShip(false);
					
					//System.out.println("Hit and sank!");
				}
			}
		} else {		
			if(p.isHasHitShip())
				p.setHasHitShip(false);
			
			if(!p.getShoots().isEmpty()) {
				if(p.hasHitAtCoord(shoot)) {
					//System.out.println("Already hit " + shoot.toString());
				} else {
					//System.out.println("Miss in " + shoot.toString());
					p.addShoot(shoot);
				}
			} else {
				//System.out.println("Miss in " + shoot.toString());
				p.addShoot(shoot);
			}
		}
	}
}
