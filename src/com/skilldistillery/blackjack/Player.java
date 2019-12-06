package com.skilldistillery.blackjack;

import java.util.ArrayList;
import java.util.List;

public class Player {
	List<Card> hand;
	
	public Player() {
		hand = new ArrayList<Card>();
	}
	
	public void printHand() {
		for (Card card : hand) {
			System.out.println(card);
		}
	}

}
