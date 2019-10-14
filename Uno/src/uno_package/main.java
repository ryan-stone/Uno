package uno_package;

public class main {

	public static void main(String[] args) {
		Game game = new Game(); // Starts the game
		
		// 		<-- Once we are here, the game is over
		
		Player winner = game.getWinner();
		System.out.println("Winner is: " + winner.getName());
	}

}
