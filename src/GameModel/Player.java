package GameModel;

import java.util.LinkedList;

import View.UNOCard;

public class Player {

	//@ public initially name != null;
	private /*@ spec_public nullable @*/ String name;
	private /*@ spec_public @*/ boolean isMyTurn = false;
	private /*@ spec_public @*/ boolean saidUNO = false;

	//@ public initially myCards != null;
	private /*@ spec_public nullable @*/ LinkedList<UNOCard> myCards;

	//@ public invariant playedCards >= 0;
	private /*@ spec_public @*/ int playedCards = 0;

	public Player(){
		myCards = new LinkedList<>();
	}

	/*@ assignable name, myCards;
	  @ ensures name == player;
	  @ ensures myCards != null && myCards.size() == 0;
	  @*/
	public Player(String player){
		setName(player);
		myCards = new LinkedList<>();
	}

	/*@ assignable this.name;
	  @ ensures this.name == newName;
	  @*/
	public void setName(String newName){
		name = newName;
	}

	//@ ensures \result == this.name;
	public /*@ pure @*/ String getName(){
		return this.name;
	}

	/*@ ensures myCards.size() == \old(myCards.size()) + 1;
	  @ ensures myCards.get(myCards.size() - 1) == card;
	  @ ensures_redundantly
	  @ 	(\forall int i; 0 <= i && i < myCards.size() - 1;
	  @ 		myCards.get(i) == \old(myCards).get(i));
	  @*/
	public void obtainCard(UNOCard card){
		myCards.add(card);
	}

	//@ ensures \result == this.myCards;
	public /*@ pure @*/LinkedList<UNOCard> getAllCards(){
		return myCards;
	}

	//@ ensures \result == myCards.size();
	public /*@ pure @*/ int getTotalCards(){
		return myCards.size();
	}

	/*@ ensures \result
	  @ 	== (\num_of int j; 0 <= j && j < myCards.size();
	  @ 		myCards.get(j) == thisCard) > 0;
	  @*/
	public /*@ pure @*/ boolean hasCard(UNOCard thisCard){
		return myCards.contains(thisCard);
	}

	/*@ assignable myCards, playedCards;
	  @ ensures myCards.size() == \old(myCards.size()) - 1;
	  @ ensures playedCards == \old(playedCards) + 1;
	  @*/
	public void removeCard(UNOCard thisCard){
		myCards.remove(thisCard);
		playedCards++;
	}

	//@ ensures \result == playedCards;
	public /*@ pure @*/ int totalPlayedCards(){
		return playedCards;
	}

	/*@ assignable isMyTurn;
	  @ ensures isMyTurn != \old(isMyTurn) && (isMyTurn == true || isMyTurn == false);
	  @*/
	public void toggleTurn(){
		isMyTurn = (isMyTurn) ? false : true;
	}

	//@ ensures \result == isMyTurn;
	public /*@ pure @*/ boolean isMyTurn(){
		return isMyTurn;
	}

	//@ ensures \result == myCards.size() > 0;
	public /*@ pure @*/ boolean hasCards(){
		return (myCards.isEmpty()) ? false : true;
	}

	//@ ensures \result == saidUNO;
	public /*@ pure @*/ boolean getSaidUNO(){
		return saidUNO;
	}

	/*@ assignable saidUNO;
	  @ ensures saidUNO == true;
	  @*/
	public void saysUNO(){
		saidUNO = true;
	}

	/*@ assignable saidUNO;
	  @ ensures saidUNO == false;
	  @*/
	public void setSaidUNOFalse(){
		saidUNO = false;
	}

	/*@ assignable myCards;
	  @ ensures myCards != null && myCards.size() == 0;
	  @*/
	public void setCards(){
		myCards = new LinkedList<>();
	}
}
