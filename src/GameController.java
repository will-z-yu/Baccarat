import java.util.*;
import java.math.*;

public class GameController {
	/*Fields*/
	private GameDeck gameDeck; //8 decks of cards
	private LinkedList<Card> gameQueue; //queue for 8 decks of cards
	
	//Record of games that has special condition
	private LinkedList<Integer> dragonGames; 
	private LinkedList<Integer> pandaGames;
	private LinkedList<Integer> tieGames;
	private LinkedList<Integer> PPairGames;
	private LinkedList<Integer> BPairGames;
	private LinkedList<Integer> APairGames;
	
	//Hands
	private Hands playerHands;
	private Hands bankerHands;
	
	//Scores and wins
	private int playerScore, bankerScore;
	private int playerWins, bankerWins;
	private int dragonCount, pandaCount;
	private int tieCount;
	private int pPairCount, bPairCount, aPairCount;
	
	private int playerThird;//Value of player's third card
	
	//Flags
	private boolean hasDragon, hasPanda, hasTie;
	private boolean hasPPair, hasBPair, hasAPair;
	private boolean pair;

	
	/*Constructor*/
	public GameController(){
		gameDeck = new GameDeck();
		gameQueue = new LinkedList<>(); 
		
		playerHands = new Hands("Player");
		bankerHands = new Hands("Banker");
		
		dragonGames = new LinkedList<>();
		pandaGames = new LinkedList<>();
		tieGames = new LinkedList<>();
		PPairGames = new LinkedList<>();
		BPairGames = new LinkedList<>();
		APairGames = new LinkedList<>();
	}	
	
	/*Methods*/
	//Public
	public void gameStart() {
		//Initialization
		playerScore = 0; 
		bankerScore = 0;
		playerWins = 0;
		bankerWins = 0;
		dragonCount = 0; 
		pandaCount = 0;
		tieCount = 0;
		pPairCount =  0;
		bPairCount = 0; 
		aPairCount = 0;
		hasDragon = false;
		hasPanda = false;
		hasTie = false;
		hasPPair = false;
		hasBPair = false;
		hasAPair = false;
		pair = false;
		
		//Make and shuffle 8 decks of cards
		gameDeck.makeDeck();
		gameDeck.shffuleDeck(gameDeck.getDeck());
		//gameDeck.printDeck();	
		
		//Queue the shuffled deck
		queueDeck(gameDeck.getDeck());
		
		//Dispose cards based on the first card
		initSetup();
		//printQueue();
	}
	
	public void dealCards() {
		//Player, Banker, Player, Banker
		playerHands.dealCard(gameQueue.poll());
		bankerHands.dealCard(gameQueue.poll());
		playerHands.dealCard(gameQueue.poll());
		bankerHands.dealCard(gameQueue.poll());
		
		//Check pairs 
		checkPairs(playerHands, bankerHands);
		if(pair == true) {
			System.out.print("\n");
		}		
	}
	
	public void checkScores() {
		playerScore = playerHands.checkScore();
		bankerScore = bankerHands.checkScore();
		
		//Banker Natural
		if(bankerScore > 7) {
			System.out.println("Natural");
			compareHands();
		}
		//Player Draw		
		else if(playerScore < 6) {	
			addCards(playerHands);
			playerThird = playerHands.getHands().get(2).getRank().getValue();
			
			if(bankerScore > 6) {//Banker Stand
				compareHands();
			}
			else if(bankerScore < 3) {//Banker Draws
				addCards(bankerHands);											
				compareHands();
			}
			else if(bankerScore == 3) {//3		
				if(playerThird != 8) {
					addCards(bankerHands);											
					compareHands();
				}
				else {
					compareHands();
				}				
			}
			else if(bankerScore == 4) {//4
				if(playerThird == 2 || playerThird == 3 || playerThird == 4 || playerThird == 5 || playerThird == 6 || playerThird == 7) {
					addCards(bankerHands);											
					compareHands();
				}
				else {
					compareHands();
				}				
			}
			else if(bankerScore == 5) {//5
				if(playerThird == 4 || playerThird == 5 || playerThird == 6 || playerThird == 7) {
					addCards(bankerHands);											
					compareHands();
				}
				else {
					compareHands();
				}				
			}
			else if(bankerScore == 6) {//6
				if(playerThird == 6 || playerThird == 7) {
					addCards(bankerHands);											
					compareHands();
				}
				else {
					compareHands();
				}				
			}	
			else {
				System.out.println("I fucked up");
			}
		}
		//Player Stand
		else if(playerScore < 8) {
			System.out.println("Player stands");
			if(bankerScore < 6) {//Banker Draw
				addCards(bankerHands);											
				compareHands();
			}
			else{//Banker Stand 
				compareHands();
			}		
		}
		//Player Natural
		else {
			System.out.println("Natural");
			compareHands();
		}
	}

	public void addSpecialCond(int roundCount) {
		if(hasDragon) {
			dragonGames.add(roundCount);
		}
		if(hasPanda) {
			pandaGames.add(roundCount);
		}
		if(hasTie) {
			tieGames.add(roundCount);
		}
		if(hasPPair) {
			PPairGames.add(roundCount);
		}
		if(hasBPair) {
			BPairGames.add(roundCount);
		}
		if(hasAPair) {
			APairGames.add(roundCount);
		}
	}
		
	public void clearRound() {
		playerHands.clearHands();
		bankerHands.clearHands();
		hasDragon = false;
		hasPanda = false;
		hasTie = false;
		hasPPair = false;
		hasBPair = false;
		hasAPair = false;
		pair = false;
	}
	
	public boolean notGameOver() {
		return gameQueue.size() > 20;
	}

	public void gameOver(int roundCount) {
		System.out.println("Total Rounds: " + roundCount);
		System.out.println("Banker Wins: " + bankerWins);
		System.out.println("Player Wins: " + playerWins);
		
		//Dragon
		System.out.print("Dragon: " + dragonCount);
		printRoundNum(dragonGames);
		
		//Panda
		System.out.print("Panda: " + pandaCount);
		printRoundNum(pandaGames);
		
		//Tie
		System.out.print("Tie: " + tieCount);
		printRoundNum(tieGames);
		
		//Pairs
		System.out.print("Banker Pairs: " + bPairCount);
		printRoundNum(BPairGames);
		System.out.print("Player Pairs: " + pPairCount);		
		printRoundNum(PPairGames);
		System.out.print("Both Pairs: " + aPairCount);
		printRoundNum(APairGames);
	}
	
	//Private
	private void queueDeck(Card[] inputDeck) {
		for(int i = 0; i < inputDeck.length; i++) {
			this.gameQueue.add(inputDeck[i]);
		}
	}
	
	private void initSetup() {
		Card firstCard = gameQueue.poll();
		int i;
		
		switch(firstCard.getRank()) {
		case ACE:
			//System.out.println("First card is Ace of " + firstCard.getSuit());
			gameQueue.remove();
			break;
		case TWO:
			//System.out.println("First card is 2 of " + firstCard.getSuit());
			for(i = 0; i < 2; i++) {
				gameQueue.remove();
			}
			break;
		case THREE:
			//System.out.println("First card is 3 of " + firstCard.getSuit());
			for(i = 0; i < 3; i++) {
				gameQueue.remove();
			}
			break;
		case FOUR:
			//System.out.println("First card is 4 of " + firstCard.getSuit());
			for(i = 0; i < 4; i++) {
				gameQueue.remove();
			}
			break;
		case FIVE:
			//System.out.println("First card is 5 of " + firstCard.getSuit());
			for(i = 0; i < 5; i++) {
				gameQueue.remove();
			}
			break;
		case SIX:
			//System.out.println("First card is 6 of " + firstCard.getSuit());
			for(i = 0; i < 6; i++) {
				gameQueue.remove();
			}
			break;
		case SEVEN:
			//System.out.println("First card is 7 of " + firstCard.getSuit());
			for(i = 0; i < 7; i++) {
				gameQueue.remove();
			}
			break;
		case EIGHT:
			//System.out.println("First card is 8 of " + firstCard.getSuit());
			for(i = 0; i < 8; i++) {
				gameQueue.remove();
			}
			break;
		case NINE:
			//System.out.println("First card is 9 of " + firstCard.getSuit());
			for(i = 0; i < 9; i++) {
				gameQueue.remove();
			}
			break;
		case TEN:
			//System.out.println("First card is 10 of " + firstCard.getSuit());
			for(i = 0; i < 10; i++) {
				gameQueue.remove();
			}
			break;
		case JACK:
			//System.out.println("First card is Jack of " + firstCard.getSuit());
			for(i = 0; i < 10; i++) {
				gameQueue.remove();
			}
			break;
		case QUEEN:
			//System.out.println("First card is Queen of " + firstCard.getSuit());
			for(i = 0; i < 10; i++) {
				gameQueue.remove();
			}
			break;
		case KING:
			//System.out.println("First card is King of " + firstCard.getSuit());
			for(i = 0; i < 10; i++) {
				gameQueue.remove();
			}
			break;
		default:
			System.out.println("Error");
			break;
		}
	}
	
	private void printQueue() {
		for(int i = 0; i < gameQueue.size(); i++) {
			System.out.print(gameQueue.get(i).getID() + "\t");
			if((i + 1) % 20 == 0) {
				System.out.print("\n");
			}
		}
		System.out.print("\n");
	}

	private void checkPairs(Hands pHands, Hands bHands) {
		Rank player1 = pHands.getHands().get(0).getRank();
		Rank player2 = pHands.getHands().get(1).getRank();
		Rank banker1 = bHands.getHands().get(0).getRank();
		Rank banker2 = bHands.getHands().get(1).getRank();
		
		if(player1 == player2) {
			System.out.println("Player Pair!" + player1 + " " + player2);
			pair = true;
			hasPPair = true;
			pPairCount++;			
		}
		if(banker1 == banker2) {
			System.out.println("Banker Pair!" + banker1 + " " + banker2);
			pair = true;
			hasBPair = true;
			bPairCount++;			
		}
		if(player1 == player2 && banker1 == banker2) {
			System.out.println("Both Pair!");
			pair = true;
			hasAPair = true;
			aPairCount++;			
		}
	}
	
	private void addCards(Hands hands) {
		hands.dealCard(gameQueue.poll());
		hands.setThird();
		System.out.println(hands.getName() + " draws " + hands.getHands().get(2).getRank().getValue());	
	}	
	
	private void compareHands() {
		playerScore = playerHands.checkScore();
		bankerScore = bankerHands.checkScore();
		
		if(playerScore > bankerScore) {
			if(playerScore == 8 && playerHands.hasThird()) {//Panda
				System.out.println("      PANDA");
				hasPanda = true;
				pandaCount++;
			}
			else {
				System.out.println("      PLAYER");
				playerWins++;
			}
		}
		else if(playerScore < bankerScore) {
			if(bankerScore == 7 && bankerHands.hasThird()) {//Dragon
				System.out.println("      DRAGON");
				hasDragon = true;
				dragonCount++;
			}
			else {
				System.out.println("      BANKER");
				bankerWins++;
			}
		}
		else {
			System.out.println("       TIE");
			hasTie = true;
			tieCount++;
		}
	}
		
	private void printRoundNum(LinkedList<Integer> gameArray) {
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
