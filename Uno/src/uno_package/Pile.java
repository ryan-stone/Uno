package uno_package;

import java.util.ArrayList;

public class Pile {
	public ArrayList<Card> pile = new ArrayList<Card>();
	public String wildColor;
	
	// Wraps the pile.add() function so that when we call it elsewhere,
	// we don't have to write "pile.pile.add()"
	public void add(Card card) {
		pile.add(card);
	}
	
	public Card removeBottom() {
		Card tempCard = new Card(null, null);
		if (pile.size() == 1) {
			System.err.println("Error: Cannot remove last card from pile");
		}
		else if (pile.size() == 0) {
			System.err.println("Error: Cannot remove card from empty pile");
		}
		else {
			tempCard = pile.get(0);
			pile.remove(0);
		}
		
		return tempCard;
	}
	
	public Card getTopCard() {
		return pile.get(pile.size()-1);
	}
	
	public String getTopCardValue() {
		return getTopCard().getValue();
	}
	
	public void resetPile() {
		while (pile.size() != 0)
			pile.remove(0);
	}
	
	public void setWildColor(String color) {
		wildColor = color;
	}
	
	public String getWildColor() { return wildColor; }
	
	public int size() { return pile.size(); }
	

}
