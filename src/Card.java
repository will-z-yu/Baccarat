
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
	public Rank getRank(){return this.rank;}
	
	public Suit getSuit(){return this.suit;}
	
	public int getID() {return this.id;}
	
	//Set
	public void setRank(Rank rank){this.rank = rank;}
	
	public void setSuit(Suit suit){this.suit = suit;}
	
	public void setID(int id) {this.id = id;}
}
