/*Represents a card object*/

public class Card {
	/*Fields*/
	private Rank rank;
	private Suit suit;
	private int id;
	
	/*Constructor*/
	public Card( Suit suit, Rank rank, int id){	
		this.suit = suit;
		this.rank = rank;		
		this.id = id;
	}
	
	/*Methods*/
	
	//Get
	Rank getRank(){return this.rank;}
	
	Suit getSuit(){return this.suit;}
	
	int getID() {return this.id;}
	
	//Set
	void setRank(Rank rank){this.rank = rank;}
	
	void setSuit(Suit suit){this.suit = suit;}
	
	void setID(int id) {this.id = id;}
}
