package CardModel;

import java.awt.Color;

import View.UNOCard;

public class ActionCard extends UNOCard {

	// Constructor
	public ActionCard() {
	}

	public ActionCard(Color cardColor, String cardValue) {
		super(cardColor, ACTION, cardValue);
	}
}
