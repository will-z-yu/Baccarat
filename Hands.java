import java.util.LinkedList;

public class Hands {
	/*Fields*/
	LinkedList<Card> hand;
	String name;
	private boolean hasThird;
	
	/*Constructor*/
	public Hands(String name) {
		hand = new LinkedList<>();
		this.name = name;
		this.hasThird = false;
	}
	
	/*Methods*/
	void dealCard(Card card) {	
		hand.add(card);
	}
	
	int checkScore(){
		int score = 0;
		
		//Add all the score on hand
		for(int i = 0; i < hand.size(); i++) {
			score += hand.get(i).getRank().getValue();
		}
		
		//If the score goes over 9, make it -10
		if(score > 19) {
			score -= 20;
		}
		if(score > 9) {
			score -= 10;
		}
		
		System.out.println("Score for " + this.name + ": " + score);
		return score;
	}
	
	void clearHands() {
		hand.clear();
		hasThird = false;
	}
	
	void setThird() {hasThird = true;}
	
	boolean hasThird() {return hasThird;}
	
	LinkedList<Card> getHands(){return this.hand;}
	
	String getName() {return this.name;}
}
