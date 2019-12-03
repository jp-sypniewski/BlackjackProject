package com.skilldistillery.blackjack;

public class BlackjackHand extends Hand {

	public BlackjackHand() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getHandValue() {
		int value = 0;
		for (Card card : hand) {
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
