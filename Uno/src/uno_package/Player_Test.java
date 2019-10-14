package uno_package;

public class Player_Test {

	public static void main(String[] args) {
		Deck deck = new Deck();
		deck.shuffle();
		Player player = new Player("Ryan");
		player.draw(7, deck);
		System.out.println("Player hand\n" + player.getHand());
	}

}
