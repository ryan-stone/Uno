package uno_package;
import java.util.ArrayList;

public class Player {
	/* Using an ArrayList for the player hand because it
	 * allows for a resizeable array */
	ArrayList<Card> hand = new ArrayList<Card>();
	public int score; // Tracks the score between rounds
	String name; // Player's name
	
	Player(){
		this("Player1");
	}
	
	public Player(String n){
		name = n;
		score = 0;
	}
	
	// Draws n cards from deck
	public void draw(int n, Deck deck) {
		for (int i = 0; i < n; i++) {
			hand.add(deck.draw());
		}
	}
	
	// toString method for the hand
	public String getHand() {
		String str = "";
		for (int i = 0; i < hand.size(); i++) {
			str += hand.get(i).toString();
			str += "\n";
		}
		return str;
	}
	

	public Card playCard(Pile pile, String action) throws IllegalArgumentException{
		// filler code
		Card card = new Card(null, null);
		
		// Code for selecting card
		int pos = -1;
		if (action.contains("PLAY")) {
			String temp = action.replaceAll("[^0-9]", "");
			pos = Integer.parseInt(temp);
			card = hand.get(pos);
		}
		else if (action.contains("DRAW")) {
			return card;
		}
		
		// Code for validating card to play
		IllegalArgumentException e = new IllegalArgumentException();
		if (pile.getTopCard().getColor() == "WILD") {
			if (pile.getWildColor() != card.getColor() &&
				card.getColor() != "WILD") {
				throw e;
			}
		}		
		else if (!pile.getTopCard().getColor().equals(card.getColor()) &&
				 !pile.getTopCard().getValue().equals(card.getValue()) &&
				 card.getColor() != "WILD") {
						
			throw e;
		}
		
		if (pos >= 0) hand.remove(pos);
		
		return card;
	}
	
	public int getScore() { return score; }
	
	public void updateScore() {
		for (int i = 0; i < hand.size(); i++) {
			score += hand.get(i).getScore();
		}
	}
	
	public void resetHand() {
		while(hand.size() != 0) 
			hand.remove(0);
	}
	
	public String getName() { return name; }

	public void printHand() {
		for (int i = 0; i < hand.size(); i++) {
			System.out.println(hand.get(i).toString());
		}
	}
	
	
}
