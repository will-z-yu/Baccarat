
public enum Rank {
	ACE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), 
	SEVEN(7), EIGHT(8), NINE(9), TEN(0), 
	JACK(0), QUEEN(0), KING(0);
	
	//Fields
	private int ranks;
	
	//Constructor
	private Rank(int ranks) {
		this.ranks = ranks;
	}
	
	//Methods
	public int getValue() {return ranks;}
}
