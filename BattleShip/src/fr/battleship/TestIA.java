package fr.battleship;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import abadie.aubin.*;

public class TestIA {
	
	public static void main(String[] args) throws IOException {
		
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
		int AI1 = -1;
		int AI2 = -1;
		
		File f = new File("ai_proof.csv");
		FileWriter fw = new FileWriter(f);
		fw.append("AI Name; score; AI Name2; score2\n");
		
		
		Coord c1 = null;
		
		for (int k = 0 ; k < 3 ; k++) {;
			
			switch (k) { 
				case 0: //AI Level Beginner; X1; AI Level Medium; Y1
					AI1 = 0;
					AI2 = 1;
					break;
				case 1: //AI Level Beginner; X2; AI Level Hard; Y2
					AI1 = 1;
					AI2 = 2;
					break;
				case 2 : //AI Level Medium; X3; AI Level Hard ;Y3
					AI1 = 0;
					AI2 = 2;
					break;
				default:
					break;
			}
			
			int AI1Wins = 0;
			int AI2Wins = 0;
			
			
			
			
			switch (k) {
				case 0:
					fw.append("AI Level Beginner; " + AI1Wins + "; AI Level Medium; " + AI2Wins + "\n");
					break;
				case 1:
					fw.append("AI Level Beginner; " + AI1Wins + "; AI Level Hard; " + AI2Wins + "\n");
					break;
				case 2:
					fw.append("AI Level Medium; " + AI1Wins + "; AI Level Hard; " + AI2Wins + "\n");
					break;
				default:
					break;
			}
		}
		fw.flush();
		fw.close();
	}
}
