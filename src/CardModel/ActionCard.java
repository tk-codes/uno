package CardModel;

import java.awt.Color;

import View.UNOCard;

public class ActionCard extends UNOCard {

	private /*@ spec_public @*/ int Function = 0;

	// Constructor
	public ActionCard() {
	}

	public ActionCard(Color cardColor, String cardValue) {
		super(cardColor, ACTION, cardValue);
	}
}
