package com.skilldistillery.blackjack;

public class Player {
	private BlackjackHand hand;
	private boolean continuePlaying;
	
	public Player() {
		hand = new BlackjackHand();
		continuePlaying = true;
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
	
	public void takeCard(Card card) {
		hand.addCard(card);
	}
	
	public void playerQuits() {
		continuePlaying = false;
	}
	
	public boolean getContinuePlaying() {
		return continuePlaying;
	}

}
