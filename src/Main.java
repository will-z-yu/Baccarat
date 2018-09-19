/*Main function*/

import java.util.*;

public class Main {
	/*Fields*/
	LinkedList<Integer> dragonGames = new LinkedList<>();
	LinkedList<Integer> pandaGames = new LinkedList<>();
	LinkedList<Integer> tieGames = new LinkedList<>();
	LinkedList<Integer> PPairGames = new LinkedList<>();
	LinkedList<Integer> BPairGames = new LinkedList<>();
	LinkedList<Integer> APairGames = new LinkedList<>();
	
	/*Constructor*/
	
	/*Methods*/
	public static void main(String[] args) {
		Main main = new Main();
		GameController game = new GameController();
		int roundCount = 0;
		int i;		
		
		game.gameStart();
		while(game.notGameOver()) {
			System.out.println("------Game:" + roundCount + "------");
			game.dealCards();
			game.checkScores();
			main.addSpecialCond(game, roundCount);
			game.clearRound();
			roundCount++;
			System.out.print("\n");
		}
		
		System.out.println("Total Rounds: " + roundCount);
		System.out.println("Banker Wins: " + game.getBankerWins());
		System.out.println("Player Wins: " + game.getPlayerWins());
		
		//Dragon
		System.out.print("Dragon: " + game.getDragonCount());
		main.printRoundNum(main.dragonGames);
		
		//Panda
		System.out.print("Panda: " + game.getPandaCount());
		main.printRoundNum(main.pandaGames);
		
		//Tie
		System.out.print("Tie: " + game.getTieCount());
		main.printRoundNum(main.tieGames);
		
		//Pairs
		System.out.print("Banker Pairs: " + game.getBPairCount());
		main.printRoundNum(main.BPairGames);
		System.out.print("Player Pairs: " + game.getPPairCount());		
		main.printRoundNum(main.PPairGames);
		System.out.print("Both Pairs: " + game.getAPairCount());
		main.printRoundNum(main.APairGames);
	}
	
	void addSpecialCond(GameController game, int roundCount) {
		if(game.getDragon()) {
			dragonGames.add(roundCount);
		}
		if(game.getPanda()) {
			pandaGames.add(roundCount);
		}
		if(game.getTie()) {
			tieGames.add(roundCount);
		}
		if(game.getPPair()) {
			PPairGames.add(roundCount);
		}
		if(game.getBPair()) {
			BPairGames.add(roundCount);
		}
		if(game.getAPair()) {
			APairGames.add(roundCount);
		}
	}

	void printRoundNum(LinkedList<Integer> gameArray) {
		if(gameArray.size() > 0) {
			int arraySize = gameArray.size();
			System.out.print(" (");
			for(int i = 0; i < arraySize; i++) {
				if(i == arraySize - 1) {
					System.out.print(gameArray.poll());
				}
				else {
					System.out.print(gameArray.poll() + ", ");
				}
			}
			System.out.print(")");
		}
		System.out.print("\n");
	}
}
