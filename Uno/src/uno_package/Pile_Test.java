package uno_package;

public class Pile_Test {

	public static void main(String[] args) {
		Pile pile = new Pile();
		Deck deck = new Deck();
		
		deck.flipCard(pile);
		
		System.out.println(pile.getTopCard().toString());

	}

}
