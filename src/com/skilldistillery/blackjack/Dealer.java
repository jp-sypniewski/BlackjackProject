package com.skilldistillery.blackjack;

public class Dealer {
	private BlackjackHand hand;
	private Deck deck;
	private boolean softAce;
	
	public Dealer() {
		hand = new BlackjackHand();
		deck = new Deck(6);
	}
	
	public void deal(Player player) {
		for(int i = 0; i < 2; i++) {
			player.takeCard(deck.dealCard());
			hand.addCard(deck.dealCard());
		}
	}
	
	public void playerHits(Player player) {
		player.takeCard(deck.dealCard());
	}
	
	public void dealerHits() {
		hand.addCard(deck.dealCard());
	}
	
	public int dealerHandValue() {
		return hand.getHandValue();
	}
	
	public void printDeal() {
		System.out.println("----------");
		System.out.println("Dealer hand:");
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
	
	public void newDeck() {
		deck = new Deck(6);
	}

}
