package uno_package;
import java.util.ArrayList;

public class Bot extends Player {
	
	Bot(){
		score = 0;
	}
	
	// needs to be implemented
	public Card playCard(Pile pile) {
		
		// Sleep for 750 ms
		try {
			Thread.sleep(750);
		}catch(InterruptedException e) {
			
		}
		
		
		Card card = new Card(null, null);
		
		for (int i = 0; i < hand.size(); i++) {
			// If play card is wild then it may be played
			if (hand.get(i).getColor() == "WILD") {
				card = hand.get(i);
				hand.remove(i);
				break;
			}
			// Play card is not a wild
			else {
				// If the top card in the pile is a wild then check 
				// that the card matches wild color
				if (pile.getTopCard().getColor().equals("WILD")) {
					if (hand.get(i).getColor().equals(pile.getWildColor())){
						card = hand.get(i);
						hand.remove(i);
						break;
					}
				}
				// If the top pile card is not a wild check that the color or value matches
				else {
					if (hand.get(i).getColor().equals(pile.getTopCard().getColor()) ||
						hand.get(i).getValue().equals(pile.getTopCard().getValue())) {
						card = hand.get(i);
						hand.remove(i);
						break;
					}
				}
			}
		}
		
		return card;
	}
}
