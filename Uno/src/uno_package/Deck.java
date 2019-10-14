package uno_package;
import java.util.Random;
import java.util.ArrayList;

public class Deck {
	/* Top of the deck = deck.get(0) */
	/* Bottom of the deck = deck.get(size-1) */
	
	public ArrayList<Card> deck = new ArrayList<Card>();
	
	Deck() {
		initDeck();
		shuffle();
	}
	
	// Initializes the UNO deck with with 108 cards
	public void initDeck() {
		
		deck.clear();
		
		for (int i = 0; i < 10; i++) {
			deck.add(new Card("RED", Integer.toString(i))) ;
		}
		deck.add(new Card("RED", "SKIP"));
		deck.add(new Card("RED", "REVERSE")) ;
		deck.add(new Card("RED", "DRAW2"));
		
		for (int i = 13; i < 23; i++) {
			deck.add(new Card("YELLOW", Integer.toString(i%13)));
		}
		deck.add(new Card("YELLOW", "SKIP"));
		deck.add(new Card("YELLOW", "REVERSE"));
		deck.add(new Card("YELLOW", "DRAW2"));
		
		for (int i = 26; i < 36; i++) {
			deck.add(new Card("GREEN", Integer.toString(i%26)));
		}
		deck.add(new Card("GREEN", "SKIP"));
		deck.add(new Card("GREEN", "REVERSE"));
		deck.add(new Card("GREEN", "DRAW2"));
		
		for (int i = 39; i < 49; i++) {
			deck.add(new Card("BLUE", Integer.toString(i%39)));
		}
		deck.add(new Card("BLUE", "SKIP"));
		deck.add(new Card("BLUE", "REVERSE"));
		deck.add(new Card("BLUE", "DRAW2"));
		
		
		for (int i = 52; i < 61; i++) {
			deck.add(new Card("RED", Integer.toString(i%52+1)));
		}
		deck.add(new Card("RED", "SKIP"));
		deck.add(new Card("RED", "REVERSE"));
		deck.add(new Card("RED", "DRAW2"));
		
		for (int i = 64; i < 73; i++) {
			deck.add(new Card("YELLOW", Integer.toString(i%64+1)));
		}
		deck.add(new Card("YELLOW", "SKIP"));
		deck.add(new Card("YELLOW", "REVERSE"));
		deck.add(new Card("YELLOW", "DRAW2"));
		
		for (int i = 76; i < 85; i++) {
			deck.add(new Card("GREEN", Integer.toString(i%76+1)));
		}
		deck.add(new Card("GREEN", "SKIP"));
		deck.add(new Card("GREEN", "REVERSE"));
		deck.add(new Card("GREEN", "DRAW2"));
		
		for (int i = 88; i < 97; i++) {
			deck.add(new Card("BLUE", Integer.toString(i%88+1)));
		}
		deck.add(new Card("BLUE", "SKIP"));
		deck.add(new Card("BLUE", "REVERSE"));
		deck.add(new Card("BLUE", "DRAW2"));
		
		for (int i = 100; i < 104; i++) {
			deck.add(new Card("WILD", "WILD"));
		}
		
		for (int i = 104; i < 108; i++) {
			deck.add(new Card("WILD", "DRAW4"));
		}
	}


	public void shuffle() {
		Random rand = new Random();
		int num1;
		int num2;
		Card tempCard;
		
		for (int i = 0; i < 250; i++) {
			num1 = rand.nextInt(size());
			num2 = rand.nextInt(size());
			
			tempCard = deck.get(num1);
			deck.set(num1, deck.get(num2));
			deck.set(num2,  tempCard);
		}
	}

	
	// Returns and removes the top card from the deck
	public Card draw() {
		Card tempCard = deck.get(0);
		deck.remove(0);
		return tempCard;
	}
	
	// Wraps the size function
	public int size() {
		return deck.size();
	}
	
	// Wraps the get function
	public Card get(int n) {
		return deck.get(n);
	}
	
	// Wraps the remove function
	public void remove(int n) {
		deck.remove(n);
	}
	
	// Flips the top card at the beginning of the round
	// and keeps flipping if it's a Wild
	public void flipCard(Pile pile) {
		boolean validTopCard = false;
		do {
			pile.add(draw());
			if (pile.getTopCard().getColor() != "WILD") {
				validTopCard = true;
			}
		} while (!validTopCard);
	}
	
	// If the deck runs out, shuffle all but the top card from the pile
	public void shufflePileToDeck(Pile pile) {
		while (pile.size() > 1) {
			deck.add(pile.removeBottom());
		}
		shuffle();
	}
}
