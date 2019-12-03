package com.skilldistillery.blackjack;

public class Card {
	private Suit suit;
	private Rank rank;
	
	public Card(Suit suit, Rank rank) {
		this.suit = suit;
		this.rank = rank;
	}
	
	public int getValue() {
		
		
		return 0;
	}

	@Override
	public String toString() {
		return "Card \nSuit=" + suit + "\nRank=" + rank;
	}
	
	
}
