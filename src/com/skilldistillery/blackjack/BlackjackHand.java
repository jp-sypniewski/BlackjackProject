package com.skilldistillery.blackjack;

public class BlackjackHand extends Hand {

	public BlackjackHand() {
		
	}
	
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
