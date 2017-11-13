package CardModel;

import java.awt.Color;

import View.UNOCard;

public class WildCard extends UNOCard {
	
	private int Function = 0;
	private /*@ spec_public nullable @*/ Color chosenColor;
	
	public WildCard() {
	}
	
	public WildCard(String cardValue){
		super(BLACK, WILD, cardValue);		
	}
	
	public void useWildColor(Color wildColor){
		chosenColor = wildColor;
	}
	
	public Color getWildColor(){
		return chosenColor;
	}

}
