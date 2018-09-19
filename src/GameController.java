/*A controller that controlls the logic of the game*/

import java.util.*;
import java.math.*;

public class GameController {
	/*Fields*/
	private GameDeck gameDeck;
	private LinkedList<Card> gameQueue; 
	private Hands playerHands;
	private Hands bankerHands;
	private int playerScore, bankerScore;
	private int playerWins, bankerWins;
	private int dragonCount, pandaCount;
	private int tieCount;
	private int pPairCount, bPairCount, aPairCount;
	private int playerThird;
	private boolean hasDragon, hasPanda, hasTie;
	private boolean hasPPair, hasBPair, hasAPair;
	private boolean pair;

	
	/*Constructor*/
	public GameController(){
		gameDeck = new GameDeck();
		gameQueue = new LinkedList<>(); 
		playerHands = new Hands("Player");
		bankerHands = new Hands("Banker");
	}	
	
	/*Methods*/
	void gameStart() {
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
		
		gameDeck.makeDeck();
		gameDeck.shffuleDeck(gameDeck.getDeck());
		//gameDeck.printDeck();	
		
		//Use a linked list for queuing
		queueDeck(gameDeck.getDeck());
		
		//Dispose cards based on the first card
		initSetup();
		//printQueue();
	}
	
	void queueDeck(Card[] inputDeck) {
		for(int i = 0; i < inputDeck.length; i++) {
			this.gameQueue.add(inputDeck[i]);
		}
	}
	
	void initSetup() {
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
	
	void printQueue() {
		for(int i = 0; i < gameQueue.size(); i++) {
			System.out.print(gameQueue.get(i).getID() + "\t");
			if((i + 1) % 20 == 0) {
				System.out.print("\n");
			}
		}
		System.out.print("\n");
	}

	void dealCards() {
		playerHands.dealCard(gameQueue.poll());
		bankerHands.dealCard(gameQueue.poll());
		playerHands.dealCard(gameQueue.poll());
		bankerHands.dealCard(gameQueue.poll());
		checkPairs(playerHands, bankerHands);
		if(pair == true) {
			System.out.print("\n");
		}		
	}
	
	void checkScores() {
		playerScore = playerHands.checkScore();
		bankerScore = bankerHands.checkScore();
		
		if(bankerScore > 7) {//Banker Natural
			System.out.println("Natural");
			compareHands();
		}
		else if(playerScore < 6) {//Player Draw			
			addCards(playerHands);
			playerThird = playerHands.getHands().get(2).getRank().getValue();
			
			if(bankerScore > 6) {//Banker Stand
				compareHands();
			}
			else if(bankerScore < 3) {
				addCards(bankerHands);											
				compareHands();
			}
			else if(bankerScore == 3) {				
				if(playerThird != 8) {
					addCards(bankerHands);											
					compareHands();
				}
				else {
					compareHands();
				}				
			}
			else if(bankerScore == 4) {
				if(playerThird == 2 || playerThird == 3 || playerThird == 4 || playerThird == 5 || playerThird == 6 || playerThird == 7) {
					addCards(bankerHands);											
					compareHands();
				}
				else {
					compareHands();
				}				
			}
			else if(bankerScore == 5) {
				if(playerThird == 4 || playerThird == 5 || playerThird == 6 || playerThird == 7) {
					addCards(bankerHands);											
					compareHands();
				}
				else {
					compareHands();
				}				
			}
			else if(bankerScore == 6) {
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
		else if(playerScore < 8) {//Player Stand
			System.out.println("Player stands");
			if(bankerScore < 6) {//Banker Draw
				addCards(bankerHands);											
				compareHands();
			}
			else{//Banker Stand 
				compareHands();
			}		
		}
		else {//Player Natural
			System.out.println("Natural");
			compareHands();
		}
	}
	
	void checkPairs(Hands pHands, Hands bHands) {
		if(pHands.getHands().get(0).getRank() == pHands.getHands().get(1).getRank()) {
			System.out.println("Player Pair!" + pHands.getHands().get(0).getRank() + " " + pHands.getHands().get(1).getRank());
			hasPPair = true;
			pPairCount++;
			pair = true;
		}
		if(bHands.getHands().get(0).getRank() == bHands.getHands().get(1).getRank()) {
			System.out.println("Banker Pair!" + bHands.getHands().get(0).getRank() + " " + bHands.getHands().get(1).getRank());
			hasBPair = true;
			bPairCount++;
			pair = true;
		}
		if(pHands.getHands().get(0).getRank() == pHands.getHands().get(1).getRank() && bHands.getHands().get(0).getRank() == bHands.getHands().get(1).getRank()) {
			System.out.println("Both Pair!");
			hasAPair = true;
			aPairCount++;
			pair = true;
		}
	}
	
	void addCards(Hands hands) {
		hands.dealCard(gameQueue.poll());
		hands.setThird();
		System.out.println(hands.getName() + " draws " + hands.getHands().get(2).getRank().getValue());	
	}	
	
	void compareHands() {
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
	
	void clearRound() {
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
	
	boolean notGameOver() {
		return gameQueue.size() > 20;
	}

	//Get
	Hands getPlayerHands() {return playerHands;}
	
	Hands getBankerHands() {return bankerHands;}
	
	int getPlayerScore() {return playerScore;}
	
	int getBankerScore() {return bankerScore;}
	
	int getPlayerWins() {return playerWins;}
	
	int getBankerWins() {return bankerWins;}
	
	int getDragonCount() {return dragonCount;}
	
	int getPandaCount() {return pandaCount;}
	
	int getTieCount() {return tieCount;}
	
	int getPPairCount() {return pPairCount;}
	
	int getBPairCount() {return bPairCount;}
	
	int getAPairCount() {return aPairCount;}
	
	boolean getDragon() {return hasDragon;}
	
	boolean getPanda() {return hasPanda;}
	
	boolean getTie() {return hasTie;}
	
	boolean getPPair() {return hasPPair;}
	
	boolean getBPair() {return hasBPair;}
	
	boolean getAPair() {return hasAPair;}
}
