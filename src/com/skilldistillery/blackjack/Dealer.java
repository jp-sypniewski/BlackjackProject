package com.skilldistillery.blackjack;

public class Dealer {
	private BlackjackHand hand;
	private Deck deck;
	// private boolean softAce;

	public Dealer() {
		hand = new BlackjackHand();
		deck = new Deck(6);
	}

	public void deal(Player player) {
		for (int i = 0; i < 2; i++) {
			player.takeCard(deck.dealCard());
			hand.addCard(deck.dealCard());
		}
	}

	public Card playerHits(Player player) {
		System.out.println("Player is dealt:");
		Card card = deck.dealCard();
		player.takeCard(card);
		return card;
	}

	public Card dealerHits() {
		System.out.println("Dealer is dealt:");
		Card card = deck.dealCard();
		hand.addCard(card);
		return card;
	}

	public int dealerHandValue() {
		return hand.getHandValue();
	}

	public boolean dealerBusts() {
		return hand.isBust();
	}

	public void printHand() {
		System.out.println("----------");
		System.out.println("Dealer hand:");
		if (hand.getHand().get(1).getValue() >= 10) {
			for (Card card : hand.getHand()) {
				System.out.println(card);
			}
		} else {
			System.out.println(hand.getHand().get(1));
			System.out.println("And one face down...");
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

	public void newDeckCheck() {
		if (deck.cardsLeftInDeck() < 104) {
			deck = new Deck(6);
		}
	}

}
