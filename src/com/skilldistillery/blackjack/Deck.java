package com.skilldistillery.blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
	private List<Card> cards;
	
	{
		cards = new ArrayList<Card>();
	}
	
	public Deck() {
		for (Suit suit : Suit.values()) {
			for (Rank rank : Rank.values()) {
				cards.add(new Card(suit, rank));
			}
		}
		shuffle();
		
	}
	
	public int cardsLeftInDeck() {
		return cards.size();
	}
	
	public Card dealCard() {
		return cards.remove(0);
	}
	
//	public void dealCard(Hand hand) {
//		
//	}
	
	public void shuffle() {
		Collections.shuffle(cards);
	}
	
	public int size() {
		return 52;
	}

}
