package com.skilldistillery.blackjack;

public class Player {
	BlackjackHand hand;
	
	public Player() {
		hand = new BlackjackHand();
	}
	
	public void printHand() {
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
