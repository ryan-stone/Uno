package uno_package;

public class Deck_Test {

	public static void main(String[] args) {
		Deck deck = new Deck();
		deck.shuffle();
		printDeck(deck);
		

	}
	
	public static void printDeck(Deck deck) {
		for (int i = 0; i < deck.size(); i++) {
			System.out.println(deck.get(i).toString());
		}
	}

}
