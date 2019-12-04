package com.skilldistillery.blackjack;

import java.util.Scanner;

public class SinglePlayerBlackjackApp {
	BlackjackHand hand;
	Deck deck;
	Scanner kb;
	int input;
	boolean quit = true;

	public static void main(String[] args) {
		SinglePlayerBlackjackApp spba = new SinglePlayerBlackjackApp();
		spba.run();

	}

	public void run() {
		kb = new Scanner(System.in);
		deck = new Deck();
		hand = new BlackjackHand(deck);

		do {
			showHand();

			while (!hand.isBust()) {
				if (hand.isBlackjack()) {
					System.out.println("BLACKJACK!");
					blackjackOrBustSysOut();
					input = kb.nextInt();
					if (input == 1) {
						hand.clear();
						redealIfNeeded();
						hand = new BlackjackHand(deck);
						break;
					} else {
						quit = false;
						break;
					}
				} else {
					System.out.println("How would you like to proceed?");
					System.out.println("1: Hit");
					System.out.println("2: Stay, Deal Again");
					System.out.println("3: Quit");
					input = kb.nextInt();
					if (input == 1) {
						hand.addCard(deck.dealCard());
					} else if (input == 2) {
						hand.clear();
						redealIfNeeded();
						hand = new BlackjackHand(deck);
						break;
					} else {
						quit = false;
						break;
					}

				}
				showHand();

			}
			if (hand.isBust()) {
				System.out.println("You busted!");
				blackjackOrBustSysOut();
				input = kb.nextInt();
				if (input == 1) {
					hand.clear();
					redealIfNeeded();
					hand = new BlackjackHand(deck);
				} else {
					quit = false;
				}
			}

		} while (quit);

	}

	public void showHand() {
		System.out.println("Your hand is:");
		for (Card card : hand.getHand()) {
			System.out.println(card);
		}
		System.out.println("Score: " + hand.getHandValue());
	}

	public void blackjackOrBustSysOut() {
		System.out.println("How would you like to proceed?");
		System.out.println("1: Deal again");
		System.out.println("2: Quit");
	}

	public void redealIfNeeded() {
		if (deck.cardsLeftInDeck() < 9) {
			deck = new Deck();
			System.out.println("Got a new deck!");
		} else {
			System.out.println("We have " + deck.cardsLeftInDeck() + " cards left in the deck");
		}
	}

}
