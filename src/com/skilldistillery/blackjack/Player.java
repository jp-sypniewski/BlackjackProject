package com.skilldistillery.blackjack;

public class Player {
	private int id;
	private BlackjackHand hand;
	private boolean continuePlaying;
	private boolean softAce = false;
	private static int idCount = 0;
	private boolean handDone = false;

	public Player() {
		hand = new BlackjackHand();
		continuePlaying = true;
		id = idCount;
		idCount++;
	}

	public int playerHandValue() {
		if (softAce) {
			return hand.getHandValue();
		} else {
			return hand.getHandValue();
		}
	}

	public boolean playerBusts() {
		return hand.isBust();
	}

	public void printHand() {
		System.out.println("----------");
		System.out.println("Player hand:");
		for (Card card : hand.getHand()) {
			System.out.println(card);
		}
		System.out.println("Hand total is: " + playerHandValue());
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
	
	public int getId() {
		return id;
	}
	
	public boolean getHandDone() {
		return handDone;
	}
	
	public void handIsDone() {
		handDone = true;
	}
	
	public void resetHandIsDone() {
		handDone = false;
	}

}
