/*Represents 8 decks of playing cards*/

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/*Game deck is 8 decks of cards*/
public class GameDeck {
	/*Fields*/
	Card[] gameDeck;
	Deck[] deckArray;
	
	/*Constructor*/
	public GameDeck() {
		this.gameDeck = new Card[416];
		this.deckArray = new Deck[8];
	}
	
	/*Methods*/
	void makeDeck() {
		//Make 8 decks of cards and shuffle them
		for(int i = 0; i < 8; i++) {
			deckArray[i] = new Deck(i);
			deckArray[i].makeDeck();
			deckArray[i].shffuleDeck(deckArray[i].getDeck());
		}	
		
		//Then put the 8 decks of card in game deck
		int deckLength = deckArray[0].getDeck().length;
		for(int i = 0; i < 8; i++) {
		System.arraycopy(deckArray[i].getDeck(), 0, gameDeck, deckLength*i, deckLength);
		}
	}
	
	void shffuleDeck(Card[] inputDeck) {//shuffles the game deck
		Card[] shuffledDeck = inputDeck;
		Random rnd = ThreadLocalRandom.current();
		
		for(int i = 0; i < 416; i++) {	
			int index = rnd.nextInt(416);
			
			//Swap
			Card temp = shuffledDeck[index];
			shuffledDeck[index] = shuffledDeck[i];
			shuffledDeck[i] = temp;
		}
		
		this.gameDeck = shuffledDeck;
	}
	
	void printDeck() {
		for(int i = 0; i < gameDeck.length; i++) {
			//System.out.print(inputDeck[i].getRank() + " of " + inputDeck[i].getSuit() + " ");	
			System.out.print(gameDeck[i].getID() + "\t");
			
			if((i + 1) % 20 == 0) {
				System.out.print("\n");
			}
		}
		System.out.print("\n");
	}
	
	Card[] getDeck() {return this.gameDeck;}
}
