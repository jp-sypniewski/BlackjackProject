package com.skilldistillery.blackjack;

import java.util.ArrayList;
import java.util.List;

public abstract class Hand {
	List<Card> hand;
	
	{
		hand = new ArrayList<Card>();
	}
	
	public Hand() {
		
	}
	
	public void addCard(Card card) {
		hand.add(card);
	}
	
	
	// this is bad, try to find a better way to clean it out.....
	public void clear() {
		hand.removeAll(hand);
	}
	
	abstract int getHandValue();

	@Override
	public String toString() {
		return "Hand \nHand=" + hand;
	}
	
	

}
