package uno_package;

import java.util.Random;

public class Game {
	
	Player player = new Player();
	Bot bot1 = new Bot();
	Bot bot2 = new Bot();
	Bot bot3 = new Bot();
	Deck deck = new Deck();
	Pile pile = new Pile();
	Gui gui = new Gui();
	
	Game(){
		gameLoop();
	}
	
	// Controls the overall game including score between rounds
	public void gameLoop() {
		do {
			round();
			updateScores();			
		} while (!gameOver());
	}
	
	// Game ends when someone reaches 200 points
	public boolean gameOver() {
		if (player.getScore() < 200 && bot1.getScore() < 200 &&
			bot2.getScore() < 200 && bot3.getScore() < 200)
			return false;
		else return true;
	}
	
	/* All the logic for each round of game play goes in here. 
	 * Must keep track of whose turn it is, the direction of play
	 * which can be changed by reverses, the color of the top card
	 * if it is a Wild, etc. */
	public void round() {
		// reset card arrays
		deck.initDeck();
		player.resetHand();
		bot1.resetHand();
		bot2.resetHand();
		bot3.resetHand();
		pile.resetPile();

		String direction = "CCW"; // CW = Clockwise, CCW = Counter CW
		String turn = "PLAYER";
		boolean roundOver = false;
		
		Card playCard = null; // Holds the card that is being played
		
		// Start the round by flipping the top card of the deck
		// onto the pile
		do {
			deck.shuffle();
			deal();
			deck.flipCard(pile);
			gui.updatePile(pile);
			gui.playerRedrawHand(player.hand);
			gui.bot1RedrawHand(bot1.hand);
			gui.bot2RedrawHand(bot2.hand);
			gui.bot3RedrawHand(bot3.hand);
		} while(pile.getTopCard().getColor() == "WILD");

		while (!roundOver) {
			// ***************** Player's turn *****************
			if (turn == "PLAYER") {
				String action = null;
				playCard = null;
				
				
				// Get the action that the player wants to take
				while (true) {
					do { action = gui.getAction(); } while (action == null);
					try {
						playCard = player.playCard(pile, action);
						break;
					} catch (IllegalArgumentException e) {
						gui.showAlert("You cannot play that card!", "Invalid Card");
					}
				}
				
				// Draw a card if they choose to draw
				if (playCard.getValue() == null) {
					player.draw(1,  deck);
					gui.playerRedrawHand(player.hand);
				}
				// Otherwise play a card
				else {
					pile.add(playCard);
					player.hand.remove(playCard);
					gui.playerPlay(playCard, player.hand);
					
					// Handle wild card played
					if (playCard.getColor() == "WILD") {
						String color = "";
						gui.resetWildColor();
						do {
							color = gui.getWildColor();
							pile.setWildColor(color);
						} while (color == "");
					}
					if (playCard.getValue().equals("DRAW4")) {
						if (direction == "CW") {
							bot1.draw(4,  deck);
							gui.bot1RedrawHand(bot1.hand);
						}
						else {
							bot3.draw(4,  deck);
							gui.bot3RedrawHand(bot3.hand);
						}
					}
					else if (playCard.getValue().equals("DRAW2")) {
						if (direction == "CW") {
							bot1.draw(2,  deck);
							gui.bot1RedrawHand(bot1.hand);
						}
						else {
							bot3.draw(2,  deck);
							gui.bot3RedrawHand(bot3.hand);
						}
					}
					else if (playCard.getValue() == "REVERSE") {
						if (direction == "CW") direction = "CCW";
						else if (direction == "CCW") direction = "CW";
						gui.reverseDirection();
					}
				}
				
				// ******** Determines whose turn is next ***********
				if (playCard.getValue() == "DRAW4" || 
					playCard.getValue() == "DRAW2" ||
					playCard.getValue() == "SKIP")
					turn = "BOT2";
				else if (direction == "CW") turn = "BOT1";
				else turn = "BOT3";
				
			}
			
			// *********** BOT1's turn **************
			else if (turn == "BOT1") {
				playCard = null;
				
				
				// Get the action that the player wants to take
				playCard = bot1.playCard(pile);
				
				// Draw a card if they choose to draw
				if (playCard.getValue() == null) {
					bot1.draw(1,  deck);
					gui.bot1RedrawHand(bot1.hand);			// ******** HERE **********
				}
				// Otherwise play a card
				else {
					pile.add(playCard);
					gui.bot1Play(playCard, bot1.hand);
					
					// Handle wild card played
					if (playCard.getColor() == "WILD") {
						// Choose random color
						Random rand = new Random();
						int colorChoice = rand.nextInt(4);
						String color;
						
						if (colorChoice == 0) color = "GREEN";
						else if (colorChoice == 1) color = "BLUE";
						else if (colorChoice == 2) color = "RED";
						else color = "YELLOW";
						
						gui.resetWildColor();
						gui.setWildColor(color);
						pile.setWildColor(color);
						
					}
					
					if (playCard.getValue().equals("DRAW4")) {
						if (direction == "CW") {
							bot2.draw(4,  deck);
							gui.bot2RedrawHand(bot2.hand);
						}
						else {
							player.draw(4,  deck);
							gui.playerRedrawHand(player.hand);
						}
					}
					else if (playCard.getValue().equals("DRAW2")) {
						if (direction == "CW") {
							bot2.draw(2,  deck);
							gui.bot2RedrawHand(bot2.hand);
						}
						else {
							player.draw(2,  deck);
							gui.playerRedrawHand(player.hand);
						}
					}
					else if (playCard.getValue() == "REVERSE") {
						if (direction == "CW") direction = "CCW";
						else if (direction == "CCW") direction = "CW";
						gui.reverseDirection();
					}
				}
				
				if (playCard.getValue() == "DRAW4" || 
					playCard.getValue() == "DRAW2" ||
					playCard.getValue() == "SKIP")
						turn = "BOT3";
				else if (direction == "CW") turn = "BOT2";
				else turn = "PLAYER";
				
			}
			// ************ Bot2's turn **************
			else if (turn == "BOT2") {
				playCard = null;
				
				// Get the action that the player wants to take
				playCard = bot2.playCard(pile);
				
				// Draw a card if they choose to draw
				if (playCard.getValue() == null) {
					bot2.draw(1,  deck);
					gui.bot2RedrawHand(bot2.hand);			// ******** HERE **********
				}
				// Otherwise play a card
				else {
					pile.add(playCard);
					gui.bot2Play(playCard, bot2.hand);
					
					// Handle wild card played
					if (playCard.getColor() == "WILD") {
						// Choose random color
						Random rand = new Random();
						int colorChoice = rand.nextInt(4);
						String color;
						
						if (colorChoice == 0) color = "GREEN";
						else if (colorChoice == 1) color = "BLUE";
						else if (colorChoice == 2) color = "RED";
						else color = "YELLOW";
						
						gui.resetWildColor();
						gui.setWildColor(color);
						pile.setWildColor(color);
						
					}
					
					if (playCard.getValue().equals("DRAW4")) {
						if (direction == "CW") {
							bot3.draw(4,  deck);
							gui.bot3RedrawHand(bot3.hand);
						}
						else {
							bot1.draw(4,  deck);
							gui.bot1RedrawHand(bot1.hand);
						}
					}
					else if (playCard.getValue().equals("DRAW2")) {
						if (direction == "CW") {
							bot3.draw(2,  deck);
							gui.bot3RedrawHand(bot3.hand);
						}
						else {
							bot1.draw(2,  deck);
							gui.bot1RedrawHand(bot1.hand);
						}
					}
					else if (playCard.getValue() == "REVERSE") {
						if (direction == "CW") direction = "CCW";
						else if (direction == "CCW") direction = "CW";
						gui.reverseDirection();
					}
				}

				if (playCard.getValue() == "DRAW4" || 
					playCard.getValue() == "DRAW2" ||
					playCard.getValue() == "SKIP")
						turn = "PLAYER";
				else if (direction == "CW") turn = "BOT3";
				else turn = "BOT1";
			}
			// ************* Bot3's turn **************
			else if (turn == "BOT3") {
				playCard = null;
				
				// Get the action that the player wants to take
				playCard = bot3.playCard(pile);
				
				// Draw a card if they choose to draw
				if (playCard.getValue() == null) {
					bot3.draw(1,  deck);
					gui.bot3RedrawHand(bot3.hand);			// ******** HERE **********
				}
				// Otherwise play a card
				else {
					pile.add(playCard);
					gui.bot3Play(playCard, bot3.hand);
					
					// Handle wild card played
					if (playCard.getColor() == "WILD") {
						// Choose random color
						Random rand = new Random();
						int colorChoice = rand.nextInt(4);
						String color;
						
						if (colorChoice == 0) color = "GREEN";
						else if (colorChoice == 1) color = "BLUE";
						else if (colorChoice == 2) color = "RED";
						else color = "YELLOW";
						
						gui.resetWildColor();
						gui.setWildColor(color);
						pile.setWildColor(color);
						
					}
					
					if (playCard.getValue().equals("DRAW4")) {
						if (direction == "CW") {
							player.draw(4,  deck);
							gui.playerRedrawHand(player.hand);
						}
						else {
							bot2.draw(4,  deck);
							gui.bot2RedrawHand(bot2.hand);
						}
					}
					else if (playCard.getValue().equals("DRAW2")) {
						if (direction == "CW") {
							player.draw(2,  deck);
							gui.playerRedrawHand(player.hand);
						}
						else {
							bot2.draw(2,  deck);
							gui.bot2RedrawHand(bot2.hand);
						}
					}
					else if (playCard.getValue() == "REVERSE") {
						if (direction == "CW") direction = "CCW";
						else if (direction == "CCW") direction = "CW";
						gui.reverseDirection();
					}
				}
				
				if (playCard.getValue() == "DRAW4" || 
					playCard.getValue() == "DRAW2" ||
					playCard.getValue() == "SKIP")
						turn = "BOT1";
				else if (direction == "CW") turn = "PLAYER";
				else turn = "BOT2";
			}
			
			if (player.hand.size() == 0 ||
				bot1.hand.size() == 0 ||
				bot2.hand.size() == 0 ||
				bot3.hand.size() == 0) {
				roundOver = true;
			}
		}
		
	}
	
	public void updateScores() {
		player.updateScore();
		bot1.updateScore();
		bot2.updateScore();
		bot3.updateScore();
		
		gui.showScores(player.score, bot1.score, bot2.score, bot3.score, gameOver());
	}
	
	/* getWinner() only runs from class Main
	 * when the game is completed and 
	 * returns the player with the lowest score*/
	public Player getWinner() {
		if (player.getScore() < bot1.getScore() &&
			player.getScore() < bot2.getScore() &&	
			player.getScore() < bot3.getScore())
			return player;
		else if (bot1.getScore() < player.getScore() &&
			bot1.getScore() < bot2.getScore() &&	
			bot1.getScore() < bot3.getScore())
			return bot1;
		else if (bot2.getScore() < player.getScore() &&
				bot2.getScore() < bot1.getScore() &&	
				bot2.getScore() < bot3.getScore())
				return bot2;
		else return bot3;
	}
	
	private void deal() {
		for (int i = 0; i < 7; i++) {
			player.draw(1,  deck);
			bot1.draw(1, deck);
			bot2.draw(1, deck);
			bot3.draw(1, deck);
		}
		
		gui.playerRedrawHand(player.hand);
	}

}
