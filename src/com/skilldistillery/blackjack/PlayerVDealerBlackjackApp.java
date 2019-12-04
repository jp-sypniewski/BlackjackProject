package com.skilldistillery.blackjack;

import java.util.Scanner;

public class PlayerVDealerBlackjackApp {
	BlackjackHand playerHand;
	BlackjackHand dealerHand;
	Deck deck;
	Scanner kb;
	int input;
	boolean continuePlaying = true;

	public static void main(String[] args) {
		PlayerVDealerBlackjackApp pvd = new PlayerVDealerBlackjackApp();
		pvd.run();
	}

	public void run() {
		kb = new Scanner(System.in);
		deck = new Deck();
		freshDeal();
		boolean playerGotBlackjack;

		do {
			playerGotBlackjack = false;
			showDealerHand();
			showPlayerHand();
			
			//TODO add function if dealer is dealt blackjack

			// player actions
			while (!playerHand.isBust()) {
				if (playerHand.isBlackjack()) {
					System.out.println("BLACKJACK! You win!");
					playerGotBlackjack = true;
					blackjackOrBustSysOut();
					input = kb.nextInt();
					if (input == 1) {
						clearHands();
						freshDeal();
						break;
					} else {
						continuePlaying = false;
						break;
					}
				} else {
					System.out.println("How would you like to proceed?");
					System.out.println("1: Hit");
					System.out.println("2: Stay");
					input = kb.nextInt();
					if (input == 1) {
						playerHand.addCard(deck.dealCard());
						System.out.println("You are now at: " + playerHand.getHandValue());
					} else {
						break;
					}
				}

			}

			// player actions done
			if (continuePlaying && !playerGotBlackjack) {
				if (playerHand.isBust()) {
					System.out.println("You busted! Dealer wins!");
					blackjackOrBustSysOut();
					input = kb.nextInt();
					if (input == 1) {
						clearHands();
						freshDeal();
						continue;
					} else {
						continuePlaying = false;
					}
				}
				// dealer actions
				else {
					while (dealerHand.getHandValue() < 17 && !dealerHand.isBust()) {
						System.out.println("Dealer draws...");
						dealerHand.addCard(deck.dealCard());
						System.out.println("The dealer is at: " + dealerHand.getHandValue());
						
					}
					if (dealerHand.isBust()) {
						System.out.println("Dealer busts! You win!");
						blackjackOrBustSysOut();
						input = kb.nextInt();
						if (input == 1) {
							clearHands();
							freshDeal();
						} else {
							continuePlaying = false;
						}
					} else {
						int diff = playerHand.getHandValue() - dealerHand.getHandValue();
						if (diff > 0) {
							System.out.println("You win!");
						} else if (diff < 0) {
							System.out.println("Dealer wins!");
						} else {
							System.out.println("A tie!");
						}
						blackjackOrBustSysOut();
						input = kb.nextInt();
						if (input == 1) {
							clearHands();
							freshDeal();
						} else {
							continuePlaying = false;
						}
					}
				}

			}

		} while (continuePlaying);
	}

	public void showPlayerHand() {
		System.out.println("Your hand is:");
		for (Card card : playerHand.getHand()) {
			System.out.println(card);
		}
		System.out.println("Score: " + playerHand.getHandValue());
	}

	public void showDealerHand() {
		System.out.println("The dealer hand is:");
		for (Card card : dealerHand.getHand()) {
			System.out.println(card);
		}
		System.out.println("Score: " + dealerHand.getHandValue());
	}

	public void blackjackOrBustSysOut() {
		System.out.println("How would you like to proceed?");
		System.out.println("1: Deal again");
		System.out.println("2: Quit");
	}

	public void freshDeal() {
		if (deck.cardsLeftInDeck() > 20) {
			playerHand = new BlackjackHand(deck);
			dealerHand = new BlackjackHand(deck);
		} else {
			deck = new Deck();
			System.out.println("New deck!");
			playerHand = new BlackjackHand(deck);
			dealerHand = new BlackjackHand(deck);
		}
	}

	public void clearHands() {
		playerHand.clear();
		dealerHand.clear();
	}

}
