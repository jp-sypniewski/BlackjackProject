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
		boolean dealerGotBlackjack;

		do {
			playerGotBlackjack = false;
			dealerGotBlackjack = false;
			showStartDealerHand();
			showPlayerHand();
			
			
			


			// player actions
			
			while (!playerHand.isBust()) {
				
				// checks for dealer blackjack
				
				if (dealerHand.isBlackjack()) {
					showFullDealerHand();
					dealerGotBlackjack = true;
					if (playerHand.isBlackjack()) {
						System.out.println("Dealer and player blackjack - push!");
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
					}
					else {
						System.out.println("Dealer blackjack...player loses...");
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
					}
				}
				
				// player dealt blackjack check
				
				if (playerHand.isBlackjack()) {
					System.out.println("BLACKJACK! Player wins!");
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
					
					// player add card actions, 
					
				} else {
					System.out.println("How would player like to proceed?");
					System.out.println("1: Hit");
					System.out.println("2: Stay");
					input = kb.nextInt();
					if (input == 1) {
						playerHand.addCard(deck.dealCard());
						System.out.println("Player is now at: " + playerHand.getHandValue());
					} else {
						break;
					}
				}

			}

			// player actions done
			
			if (continuePlaying && !playerGotBlackjack && !dealerGotBlackjack) {
				
				// player busts, no need for dealer checks
				
				if (playerHand.isBust()) {
					System.out.println("Player busted! Dealer wins!");
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
					
					// dealer adds cards based on rules
					
					showFullDealerHand();
					
					while (dealerHand.getHandValue() < 17 && !dealerHand.isBust()) {
						System.out.println("Dealer draws...");
						dealerHand.addCard(deck.dealCard());
						System.out.println(dealerHand.getHand().get(dealerHand.getHand().size()-1));
						System.out.println("The dealer is at: " + dealerHand.getHandValue());
						
					}
					
					// dealer bust actions
					
					if (dealerHand.isBust()) {
						System.out.println("Dealer busts! Player wins!");
						blackjackOrBustSysOut();
						input = kb.nextInt();
						if (input == 1) {
							clearHands();
							freshDeal();
						} else {
							continuePlaying = false;
						}
					} else {
						
						// hand comparisons where neither player busts
						
						int diff = playerHand.getHandValue() - dealerHand.getHandValue();
						if (diff > 0) {
							System.out.println("Player wins!");
						} else if (diff < 0) {
							System.out.println("Dealer wins!");
						} else {
							System.out.println("Push!");
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
		
	}  // end run()
	
	

	public void showPlayerHand() {
		System.out.println("Player hand is:");
		for (Card card : playerHand.getHand()) {
			System.out.println(card);
		}
		System.out.println("Score: " + playerHand.getHandValue());
	}

	
	//update this method to show as appropriate
	public void showFullDealerHand() {
		System.out.println("The dealer hand is:");
		for (Card card : dealerHand.getHand()) {
			System.out.println(card);
		}
		System.out.println("Score: " + dealerHand.getHandValue());
	}
	public void showStartDealerHand() {
		System.out.println("The dealer hand is:");
		System.out.println(dealerHand.getHand().get(1));
	}

	public void blackjackOrBustSysOut() {
		System.out.println("How would player like to proceed?");
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
