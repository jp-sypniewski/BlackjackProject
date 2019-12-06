package com.skilldistillery.blackjack;

public class BlackjackHand extends Hand {

	// empty constructor, used with Player/Dealer classes.
	// allows deal to alternate giving cards to player and dealer
	
	public BlackjackHand() {
		
	}
	
	// constructor with deck param, used in early versions of app
	// ...but causes the same player to get two cards in a row
	
	public BlackjackHand(Deck deck) {
		addCard(deck.dealCard());
		addCard(deck.dealCard());
	}

	@Override
	public int getHandValue() {
		int value = 0;
		for (Card card : getHand()) {
			value += card.getValue();
		}
		return value;
	}
	
	public boolean isBlackjack() {
		if (getHandValue() == 21) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean isBust() {
		if (getHandValue() > 21) {
			return true;
		}
		else {
			return false;
		}
	}

}
