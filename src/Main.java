/*Main*/

public class Main {
	/*Fields*/
	
	/*Constructor*/
	
	/*Methods*/
	public static void main(String[] args) {
		GameController game = new GameController();
		int roundCount = 0;
		
		game.gameStart();
		while(game.notGameOver()) {
			System.out.println("------Game:" + roundCount + "------");
			game.dealCards();
			game.checkScores();
			game.addSpecialCond(roundCount);
			game.clearRound();
			roundCount++;
			System.out.print("\n");
		}
		game.gameOver(roundCount);
		
	}
}
