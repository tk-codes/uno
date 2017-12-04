package CardModel;

import java.awt.Color;

import View.UNOCard;

public class WildCard extends UNOCard {
	
	private /*@ spec_public @*/ int Function = 0;
	private /*@ spec_public nullable @*/ Color chosenColor;
	
	public WildCard() {
	}
	
	public WildCard(String cardValue){
		super(BLACK, WILD, cardValue);		
	}
	
	//@ ensures chosenColor == wildColor;
	public void useWildColor(Color wildColor){
		chosenColor = wildColor;
	}
	
	//@ ensures \result == chosenColor;
	public Color getWildColor(){
		return chosenColor;
	}

}
