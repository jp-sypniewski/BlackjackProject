package com.skilldistillery.blackjack;

public class Dealer {
	private BlackjackHand hand;
	private Deck deck;
	private int numDecks = 6;
	private boolean aceIsOne;

	public Dealer() {
		hand = new BlackjackHand();
		deck = new Deck(6);
	}

	public Dealer(int numDecks) {
		hand = new BlackjackHand();
		this.numDecks = numDecks;
		deck = new Deck(numDecks);
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
		if (!aceIsOne) {
			return hand.getHandValue();
		} else {
			return dealerHandValueWithAceAsOne();
		}
	}
	
	public int dealerHandValueWithAceAsOne() {
		int aceCount = 0;
		for (Card card : hand.getHand()) {
			if (card.getValue() == 11) {
				aceCount++;
			}
		}
		int newHandValue = hand.getHandValue() - aceCount * 10;
		return newHandValue;
	}

	public boolean dealerBusts() {
		if (hand.isBust()) {
			if (dealerHandValueWithAceAsOne() <= 21) {
				aceIsOne = true;
				return false;
			}
			else {
				return hand.isBust();
			}
		} else {
			return hand.isBust();
		}
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
		aceIsOne = false;
		hand.clear();
	}

	public void newDeckCheck() {
		if (deck.cardsLeftInDeck() < numDecks * 52 / 5) {
			deck = new Deck(numDecks);
		}
	}

}
