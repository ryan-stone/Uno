package uno_package;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Insets;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

public class Gui extends Frame { //implements KeyListener
	
	final int FRAME_WIDTH = 1000;
	final int FRAME_HEIGHT = 920;
	final int CARD_WIDTH = 70;
	final int CARD_HEIGHT = 100;
	
	JFrame frame;
	
	JLabel bot2name;
	JLabel bot2NumCards;
	JLabel bot2card[];
	
	JLabel bot1name;
	JLabel bot1NumCards;
	JLabel bot1card[];
	
	JLabel bot3name;
	JLabel bot3NumCards;
	JLabel bot3card[];
	
	JButton playername;
	ArrayList<JButton> playercard = new ArrayList<JButton>();
	
	JButton deck;
	JLabel pile;
	JLabel wildColor;
	JButton wildGreen;
	JButton wildBlue;
	JButton wildRed;
	JButton wildYellow;
	JLabel directionImgLabel;
	
	Icon facedownIcon;
	Icon facedownIconSideways1;
	Icon facedownIconSideways2;
	Icon clockwiseIcon;
	Icon counterclockwiseIcon;
	
	String action;
	String direction;
	
	Gui(){
		final int BOT2_ROW = 79;
		
		frame = new JFrame("UNO");
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setLayout(null);
		frame.setBackground(Color.WHITE);
		
		// ********************** CARD ICONS **************************
		//facedown cards
		facedownIcon = new ImageIcon("src\\uno_package\\Card_Images\\FACEDOWN.png");
		Image img = ((ImageIcon) facedownIcon).getImage();
		Image newimg = img.getScaledInstance( 70, 100,  java.awt.Image.SCALE_SMOOTH );
		facedownIcon = new ImageIcon( newimg );
		
		//facedown sideways cards for bot1
		facedownIconSideways1 = new ImageIcon("src\\uno_package\\Card_Images\\FACEDOWN_SIDEWAYS1.png");
		img = ((ImageIcon) facedownIconSideways1).getImage();
		newimg = img.getScaledInstance( 100, 70,  java.awt.Image.SCALE_SMOOTH );
		facedownIconSideways1 = new ImageIcon( newimg );
		
		//facedown sideways cards for bot3
		facedownIconSideways2 = new ImageIcon("src\\uno_package\\Card_Images\\FACEDOWN_SIDEWAYS2.png");
		img = ((ImageIcon) facedownIconSideways2).getImage();
		newimg = img.getScaledInstance( 100, 70,  java.awt.Image.SCALE_SMOOTH );
		facedownIconSideways2 = new ImageIcon( newimg );
		
		
		clockwiseIcon = new ImageIcon("src\\uno_package\\Images\\Clockwise.png");
		img = ((ImageIcon) clockwiseIcon).getImage();
		newimg = img.getScaledInstance(50,  50, java.awt.Image.SCALE_SMOOTH);
		clockwiseIcon = new ImageIcon(newimg);
		
		counterclockwiseIcon = new ImageIcon("src\\uno_package\\Images\\Counter_Clockwise.png");
		img = ((ImageIcon) counterclockwiseIcon).getImage();
		newimg = img.getScaledInstance(50,  50, java.awt.Image.SCALE_SMOOTH);
		counterclockwiseIcon = new ImageIcon(newimg);
		

		//************ DISPLAY BOT1 **************
		// Label for bot name
		bot1name = new JLabel("bot1");
		bot1name.setBounds(20, 50, 50, 50);
		frame.add(bot1name);
		
		// Label for bot num cards
		bot1NumCards = new JLabel("7");
		bot1NumCards.setBounds(20, 70, 50, 50);
		frame.add(bot1NumCards);
		
		bot1card = new JLabel[7];
		
		for (int i = 0; i < 7; i++) {
			bot1card[i] = new JLabel(facedownIconSideways1);
			bot1card[i].setBounds(20, 120+70*i, CARD_HEIGHT, CARD_WIDTH);
			frame.add(bot1card[i]);
		}
		
		
		//************ DISPLAY BOT2 **************
		
		// Label for bot2 name
		bot2name = new JLabel("bot2");
		bot2name.setBounds(350, 30, 50, 50);
		frame.add(bot2name);
		
		// Label for bot2 num cards
		bot2NumCards = new JLabel("7");
		bot2NumCards.setBounds((FRAME_WIDTH)/2 + 100, 30, 50, 50);
		frame.add(bot2NumCards);
		
		bot2card = new JLabel[7];
		
		for (int i = 0; i < 7; i++) {
			bot2card[i] = new JLabel(facedownIcon);
			bot2card[i].setBounds(220+i*80,  BOT2_ROW, CARD_WIDTH, CARD_HEIGHT);
			frame.add(bot2card[i]);
		}
		
		
		//************ DISPLAY BOT3 **************
		// Label for bot3 name
		bot3name = new JLabel("bot3");
		bot3name.setBounds(860, 50, 50, 50);
		frame.add(bot3name);
		
		// Label for bot3 num cards
		bot3NumCards = new JLabel("7");
		bot3NumCards.setBounds(860, 70, 50, 50);
		frame.add(bot3NumCards);
		
		bot3card = new JLabel[7];
		
		for (int i = 0; i < 7; i++) {
			bot3card[i] = new JLabel(facedownIconSideways2);
			bot3card[i].setBounds(860, 120+70*i, CARD_HEIGHT, CARD_WIDTH);
			frame.add(bot3card[i]);
		}
		
		
		// *************** Display Center **********************

		deck = new JButton(facedownIcon);
		deck.setBounds(470, 350, CARD_WIDTH, CARD_HEIGHT);
		deck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	              setAction("DRAW");
	            }
		});
		frame.add(deck);
		
		pile = new JLabel(loadIcon("BLUE", "0"));
		pile.setBounds(370,  350, CARD_WIDTH, CARD_HEIGHT);
		frame.add(pile);
		
		wildColor = new JLabel("");
		wildColor.setBounds(385, 320, 100, 30);
		wildColor.setForeground(Color.BLUE);
		wildColor.setVisible(false);
		frame.add(wildColor);
		
		wildGreen = new JButton("GREEN");
		wildGreen.setBounds(250, 340, 70, 25);
		wildGreen.setMargin(new Insets(0, 0, 0, 0));
		wildGreen.setForeground(Color.GREEN);
		wildGreen.setBackground(Color.GRAY);
		wildGreen.setVisible(false);
		wildGreen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	              setWildColor("GREEN");
	            }
		});
		frame.add(wildGreen);
		
		wildBlue = new JButton("BLUE");
		wildBlue.setBounds(250, 370, 70, 25);
		wildBlue.setMargin(new Insets(0, 0, 0, 0));
		wildBlue.setForeground(Color.BLUE);
		wildBlue.setBackground(Color.GRAY);
		wildBlue.setVisible(false);
		wildBlue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	              setWildColor("BLUE");
	            }
		});
		frame.add(wildBlue);
		
		wildRed = new JButton("RED");
		wildRed.setBounds(250, 400, 70, 25);
		wildRed.setMargin(new Insets(0, 0, 0, 0));
		wildRed.setForeground(Color.RED);
		wildRed.setBackground(Color.GRAY);
		wildRed.setVisible(false);
		wildRed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	              setWildColor("RED");
	            }
		});
		frame.add(wildRed);
		
		wildYellow = new JButton("YELLOW");
		wildYellow.setBounds(250, 430, 70, 25);
		wildYellow.setMargin(new Insets(0, 0, 0, 0));
		wildYellow.setForeground(Color.YELLOW);
		wildYellow.setBackground(Color.GRAY);
		wildYellow.setVisible(false);
		wildYellow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	              setWildColor("YELLOW");
	            }
		});
		frame.add(wildYellow);
		

		directionImgLabel = new JLabel(counterclockwiseIcon);
		directionImgLabel.setBackground(Color.WHITE);
		directionImgLabel.setBounds(570, 375, 50, 50);
		frame.add(directionImgLabel);
		
		
		// ********************** Player Cards **********************
		

		
		direction = "CCW";
		
		frame.setVisible(true);
	}
	
	// Adds the specified card to the player's hand
	public void playerDraw(Card drawCard) {
		String color = drawCard.getColor();
		String value = drawCard.getValue();
		boolean done = false;
		int i = 0;
		
		while (!done) {
			if (playercard.get(i).isVisible()) {
				i++;
			}
			else {
				playercard.get(i).setIcon(loadIcon(drawCard.getColor(), drawCard.getValue()));
				playercard.get(i).setVisible(true);
				done = true;
			}
		}
	}
	
	
	public void playerRedrawHand(ArrayList<Card> hand) {
		for (int i = 0; i < playercard.size(); i++)
			playercard.get(i).setVisible(false);
		
		/*for (int i = 0; i < playercard.size(); i++) {
			frame.remove(playercard.get(i));
		}*/
		
		playercard.clear();
		
		for (int i = 0; i < hand.size(); i++) {
			JButton card = new JButton();
			card.setIcon(loadIcon(hand.get(i).getColor(), hand.get(i).getValue()));
			card.setBounds(220 + 80*(i%7), 550 + 110*(i/7), CARD_WIDTH, CARD_HEIGHT);
			playercard.add(card);
			playercard.get(i).setVisible(true);
			
			frame.add(playercard.get(i));
			frame.repaint();
		}
		
		for (int i = 0; i < playercard.size(); i++) {
			int current = i;
			playercard.get(i).addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
		              setAction("PLAY", current);
		            }
			});
		}
	}

	
	// Updates the display removing the card from the player's hand
	// and updates the pile card
	public void playerPlay(Card playCard, ArrayList<Card> hand) {
		wildColor.setVisible(false);
		if (playCard.getColor() == "WILD") {
			wildGreen.setVisible(true);
			wildBlue.setVisible(true);
			wildRed.setVisible(true);
			wildYellow.setVisible(true);
		}
		pile.setIcon(loadIcon(playCard.getColor(), playCard.getValue()));
		playerRedrawHand(hand);
	}

	
	public void bot1RedrawHand(ArrayList<Card> hand) {
		
		for (int i = 0; i < bot1card.length; i++)
			bot1card[i].setVisible(false);
		
		for (int i = 0; i < hand.size(); i++) {
			if (i < 7) {
				bot1card[i].setVisible(true);
				frame.add(bot1card[i]);
			}
		}
		
		bot1NumCards.setText(Integer.toString(hand.size()));

	}
	
	
	public void bot1Play(Card playCard, ArrayList<Card> hand) {
		wildColor.setVisible(false);
		pile.setIcon(loadIcon(playCard.getColor(), playCard.getValue()));
		bot1RedrawHand(hand);
	}
	
	public void bot2RedrawHand(ArrayList<Card> hand) {
		
		for (int i = 0; i < bot2card.length; i++)
			bot2card[i].setVisible(false);
		
		for (int i = 0; i < hand.size(); i++) {
			if (i < 7) {
				bot2card[i].setVisible(true);
				frame.add(bot2card[i]);
			}
		}
		
		bot2NumCards.setText(Integer.toString(hand.size()));

	}
	
	public void bot2Play(Card playCard, ArrayList<Card> hand) {
		wildColor.setVisible(false);
		pile.setIcon(loadIcon(playCard.getColor(), playCard.getValue()));
		bot2RedrawHand(hand);
	}
	
	public void bot3RedrawHand(ArrayList<Card> hand) {
		
		for (int i = 0; i < bot3card.length; i++)
			bot3card[i].setVisible(false);
		
		for (int i = 0; i < hand.size(); i++) {
			if (i < 7) {
				bot3card[i].setVisible(true);
				frame.add(bot3card[i]);
			}
		}
		
		bot3NumCards.setText(Integer.toString(hand.size()));

	}
	
	public void bot3Play(Card playCard, ArrayList<Card> hand) {
		wildColor.setVisible(false);
		pile.setIcon(loadIcon(playCard.getColor(), playCard.getValue()));
		bot3RedrawHand(hand);
	}

	
	// loads and returns the specified card icon
	public Icon loadIcon(String c, String v) {
		String file = "src\\uno_package\\Card_Images\\";
		file += c;
		file += v;
		file += ".png";
		
		Icon cardIcon = new ImageIcon(file);
		Image img = ((ImageIcon) cardIcon).getImage();
		Image newimg = img.getScaledInstance(70,  100, java.awt.Image.SCALE_SMOOTH);
		cardIcon = new ImageIcon(newimg);
				
		return cardIcon;
	}
	
	// called when the player clicks on a card in their hand
	private void setAction(String a, int pos) {
		action = a;
		action += pos;		
	}
	
	// called when the player clicks on the pile or deck
	private void setAction(String a) {
		action = a;
	}
	
	public String getAction() {
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			
		}
		String temp = action;
		action = null;
		return temp;
	}

	public void updatePile(Pile p) {
		Card card = p.getTopCard();
		pile.setIcon(loadIcon(card.getColor(), card.getValue()));
	}
	
	public void setWildColor(String c) {
		wildColor.setText(c);
		if (c == "GREEN") wildColor.setForeground(Color.GREEN);
		else if (c == "BLUE") wildColor.setForeground(Color.BLUE);
		else if (c == "RED") wildColor.setForeground(Color.RED);
		else wildColor.setForeground(Color.YELLOW);
		wildColor.setVisible(true);
		wildGreen.setVisible(false);
		wildBlue.setVisible(false);
		wildRed.setVisible(false);
		wildYellow.setVisible(false);
	}
	
	public String getWildColor() {
		try {
			Thread.sleep(200);
		}catch(InterruptedException e) {
			
		}
		
		String temp = wildColor.getText();
		
		return temp;
	}
	
	public void resetWildColor() {
		wildColor.setText("");
	}
	
	public void showAlert(String msg, String title) {
		JOptionPane.showMessageDialog(null, msg, title, JOptionPane.WARNING_MESSAGE);
	}
	
	public void showScores(int score1, int score2, int score3, int score4, boolean gameOver) {
		String title = "Score Total";
		String msg = "Player:  ";
		msg += Integer.toString(score1);
		msg += "\n";
		msg += "Bot1:     ";
		msg += Integer.toString(score2);
		msg += "\n";
		msg += "Bot2:     ";
		msg += Integer.toString(score3);
		msg += "\n";
		msg += "Bot3:     ";
		msg += Integer.toString(score4);
		
		if (gameOver == true) {
			title = "Game Over!";
			if (score1 < score2 && score1 < score3 && score1 < score4) {
				msg += "\n";
				msg += "Congratulations, you win!";
			}
			else {
				msg += "\n";
				msg += "Sorry, you lose.";
			}
		}
		
		JOptionPane.showMessageDialog(null, msg, title, JOptionPane.WARNING_MESSAGE);
	}
	
	
	public void reverseDirection() {
		if (direction == "CCW") {
			direction = "CW";
			directionImgLabel.setIcon(clockwiseIcon);
		}
		else {
			direction = "CCW";
			directionImgLabel.setIcon(counterclockwiseIcon);
		}
	}
	
	
}


