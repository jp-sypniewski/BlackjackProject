package com.skilldistillery.blackjack;

public class Dealer {
	BlackjackHand hand;
	Deck deck;
	
	public Dealer() {
		hand = new BlackjackHand();
		deck = new Deck(6);
	}
	
	public void printDeal() {
		System.out.println("----------");
		if (hand.getHand().get(1).getValue() >= 10) {
			printFullHand();
		}
		else {
			System.out.println(hand.getHand().get(1));
		}
		System.out.println("----------");
	}
	
	public void printFullHand() {
		System.out.println("----------");
		for (Card card : hand.getHand()) {
			System.out.println(card);
		}
		System.out.println("----------");
	}
	
	public void clearHand() {
		hand.clear();
	}

}
