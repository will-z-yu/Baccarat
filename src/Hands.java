import java.util.LinkedList;

public class Hands {
	/*Fields*/
	private LinkedList<Card> hand;
	private String name;
	private boolean hasThird;
	
	/*Constructor*/
	public Hands(String name) {
		this.hand = new LinkedList<>();
		this.name = name;
		this.hasThird = false;
	}
	
	/*Methods*/
	public void dealCard(Card card) {	
		hand.add(card);
	}
	
	public int checkScore(){
		int score = 0;
		
		//Add all the score on hand
		for(int i = 0; i < hand.size(); i++) {
			score += hand.get(i).getRank().getValue();
		}
		
		//If the score goes over 9, make it -10
		while(score > 9) {
			score -= 10;
		}
		
		System.out.println("Score for " + this.name + ": " + score);
		return score;
	}
	
	public void clearHands() {
		hand.clear();
		hasThird = false;
	}
	
	public void setThird() {hasThird = true;}
	
	public boolean hasThird() {return hasThird;}
	
	public LinkedList<Card> getHands(){return this.hand;}
	
	public String getName() {return this.name;}
}
