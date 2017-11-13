package ServerController;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import CardModel.WildCard;
import GameModel.Game;
import GameModel.Player;
import Interfaces.GameConstants;
import View.Session;
import View.UNOCard;

public class Server implements GameConstants {
	private /*@ spec_public nullable @*/ Game game;
	private /*@ spec_public nullable @*/ Session session;
	private /*@ spec_public nullable @*/ Stack<UNOCard> playedCards;
	public boolean canPlay;
	private int mode;

	public Server() {

		mode = requestMode();
		game = new Game(mode);
		playedCards = new Stack<UNOCard>();

		// First Card
		UNOCard firstCard = game.getCard();
		modifyFirstCard(firstCard);

		playedCards.add(firstCard);
		session = new Session(game, firstCard);

		game.whoseTurn();
		canPlay = true;
	}

	//return if it's 2-Player's mode or PC-mode
	private int requestMode() {

		Object[] options = { "vs PC", "Manual", "Cancel" };

		int n = JOptionPane.showOptionDialog(null,
				"Choose a Game Mode to play", "Game Mode",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
				null, options, options[0]);

		if (n == 2)
			System.exit(1);

		return GAMEMODES[n];
	}
	
	//coustom settings for the first card
	private void modifyFirstCard(UNOCard firstCard) {
		firstCard.removeMouseListener(CARDLISTENER);
		if (firstCard.getType() == WILD) {
			int random = new Random().nextInt() % 4;
			try {
				((WildCard) firstCard).useWildColor(UNO_COLORS[Math.abs(random)]);
			} catch (Exception ex) {
				System.out.println("something wrong with modifyFirstcard");
			}
		}
	}
	
	//return Main Panel
	public Session getSession() {
		return this.session;
	}
	
	
	//request to play a card
	public void playThisCard(UNOCard clickedCard) {

		// Check player's turn
		if (!isHisTurn(clickedCard)) {
			infoPanel.setError("It's not your turn");
			infoPanel.repaint();
		} else {

			// Card validation
			if (isValidMove(clickedCard)) {

				clickedCard.removeMouseListener(CARDLISTENER);
				playedCards.add(clickedCard);
				game.removePlayedCard(clickedCard);

				// function cards ??
				switch (clickedCard.getType()) {
				case ACTION:
					performAction(clickedCard);
					break;
				case WILD:
					performWild((WildCard) clickedCard);
					break;
				default:
					break;
				}

				game.switchTurn();
				session.updatePanel(clickedCard);
				checkResults();
			} else {
				infoPanel.setError("invalid move");
				infoPanel.repaint();
			}
			
		}
		
		
		
		if(mode==vsPC && canPlay){
			if(game.isPCsTurn()){
				game.playPC(peekTopCard());
			}
		}
	}

	//Check if the game is over
	private void checkResults() {

		if (game.isOver()) {
			canPlay = false;
			infoPanel.updateText("GAME OVER");
		}
	}
	
	//check player's turn
	public boolean isHisTurn(UNOCard clickedCard) {

		for (Player p : game.getPlayers()) {
			if (p.hasCard(clickedCard) && p.isMyTurn())
				return true;
		}
		return false;
	}

	//check if it is a valid card
	public boolean isValidMove(UNOCard playedCard) {
		UNOCard topCard = peekTopCard();

		if (playedCard.getColor().equals(topCard.getColor())
				|| playedCard.getValue().equals(topCard.getValue())) {
			return true;
		}

		else if (playedCard.getType() == WILD) {
			return true;
		} else if (topCard.getType() == WILD) {
			Color color = ((WildCard) topCard).getWildColor();
			if (color.equals(playedCard.getColor()))
				return true;
		}
		return false;
	}

	// ActionCards
	private void performAction(UNOCard actionCard) {

		// Draw2PLUS
		if (actionCard.getValue().equals(DRAW2PLUS))
			game.drawPlus(2);
		else if (actionCard.getValue().equals(REVERSE))
			game.switchTurn();
		else if (actionCard.getValue().equals(SKIP))
			game.switchTurn();
	}

	private void performWild(WildCard functionCard) {		

		//System.out.println(game.whoseTurn());
		if(mode==1 && game.isPCsTurn()){			
			int random = new Random().nextInt() % 4;
			functionCard.useWildColor(UNO_COLORS[Math.abs(random)]);
		}
		else{
			
			ArrayList<String> colors = new ArrayList<String>();
			colors.add("RED");
			colors.add("BLUE");
			colors.add("GREEN");
			colors.add("YELLOW");
			
			String chosenColor = (String) JOptionPane.showInputDialog(null,
					"Choose a color", "Wild Card Color",
					JOptionPane.DEFAULT_OPTION, null, colors.toArray(), null);
	
			functionCard.useWildColor(UNO_COLORS[colors.indexOf(chosenColor)]);
		}
		
		if (functionCard.getValue().equals(W_DRAW4PLUS))
			game.drawPlus(4);
	}
	
	public void requestCard() {
		game.drawCard(peekTopCard());
		
		if(mode==vsPC && canPlay){
			if(game.isPCsTurn())
				game.playPC(peekTopCard());
		}
		
		session.refreshPanel();
	}

	public UNOCard peekTopCard() {
		return playedCards.peek();
	}

	public void submitSaidUNO() {
		game.setSaidUNO();
	}
}
