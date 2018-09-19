import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Deck {
	/*Fields*/
	Card[] cardDeck;
	private int deckID;
	
	/*Constructor*/
	public Deck(int deckID){
		this.cardDeck = new Card[52];
		this.deckID = deckID;
	}
	
	/*Methods*/
	void makeDeck(){//initializes deck
		int k = 0;
		
		for(Rank j : Rank.values()) {
			for(Suit i : Suit.values()) {
				this.cardDeck[k] = new Card(i,j,k);
				k++;
			}
		}		
	}
	
	void shffuleDeck(Card[] inputDeck) {//shuffles deck
		Card[] shuffledDeck = inputDeck;
		Random rnd = ThreadLocalRandom.current();
		
		for(int i = 0; i < 52; i++) {	
			int index = rnd.nextInt(52);
			
			//Swap
			Card temp = shuffledDeck[index];
			shuffledDeck[index] = shuffledDeck[i];
			shuffledDeck[i] = temp;
		}
		
		this.cardDeck = shuffledDeck;
	}
	
	void printDeck(Card[] inputDeck) {//print deck
		for(int i = 0; i < inputDeck.length; i++) {
			//System.out.print(inputDeck[i].getRank() + " of " + inputDeck[i].getSuit() + " ");	
			System.out.print(inputDeck[i].getID() + "\t");
			
			if((i + 1) % 7 == 0) {
				System.out.print("\n");
			}
		}
		System.out.print("\n");
	}
	
	Card[] getDeck() {return this.cardDeck;}
	
	int getDeckID() {return this.deckID;}
}
