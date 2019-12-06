package com.skilldistillery.blackjack;

import java.util.Scanner;

public class BlackjackApp {
	private Dealer dealer;
	private Player player;
	private Scanner kb;
	private int input = 0;
	private boolean dealerMustPlay;
	private boolean comparisonOfHands;

	public static void main(String[] args) {
		BlackjackApp bja = new BlackjackApp();
		bja.run();
	}

	public void run() {
		dealer = new Dealer();
		player = new Player();
		kb = new Scanner(System.in);

		do {
			roundSetup();

			if (dealer.dealerHandValue() == 21) {
				if (player.playerHandValue() == 21) {
					System.out.println("Push, dealer and player both have blackjack.");
					comparisonOfHands = false;
				} else {
					System.out.println("Dealer has blackjack, player loses.");
					comparisonOfHands = false;
				}
				dealerMustPlay = false;
			}

			if (player.playerHandValue() == 21) {
				System.out.println("Blackjack!  Player wins!");
				dealerMustPlay = false;
				comparisonOfHands = false;
			} else {
				
				while (!player.playerBusts()) {
					System.out.println("How would player like to proceed?");
					System.out.println("1: Hit");
					System.out.println("2: Stay");
					input = kb.nextInt();
					if (input == 1) {
						System.out.println("Dealer gives: ");
						System.out.println(dealer.playerHits(player));
						System.out.println("Player is now at: " + player.playerHandValue());
					} else {
						break;
					}
					
					if (player.playerBusts()) {
						System.out.println("Player busts, dealer wins");
						dealerMustPlay = false;
						comparisonOfHands = false;
						break;
					}
				}
			}

			if (dealerMustPlay) {

				dealer.printFullHand();
				while (dealer.dealerHandValue() < 17) {
					System.out.println("Dealer must hit.");
					System.out.println(dealer.dealerHits());
					System.out.println("Dealer is now at: " + dealer.dealerHandValue());
				}

				if (dealer.dealerBusts()) {
					System.out.println("Dealer busts, player wins!");
					comparisonOfHands = false;
				}
			}

			if (comparisonOfHands) {
				System.out.println("Dealer: " + dealer.dealerHandValue() + "\tPlayer: " + player.playerHandValue());
				if (dealer.dealerHandValue() == player.playerHandValue()) {
					System.out.println("Push");
				} else if (dealer.dealerHandValue() > player.playerHandValue()) {
					System.out.println("Dealer wins.");
				} else {
					System.out.println("Player wins!");
				}
			}

			System.out.println("Gonna keep sitting there?  It's like 9 am the next day already...");
			System.out.println("1: Sure!  What is time anyway?");
			System.out.println("2: Do I at least get a free buffet ticket?");
			System.out.println("3: Thanks for the time, catch ya on the flip side");
			input = kb.nextInt();
			if (input == 1) {
				System.out.println("Sounds good.");
			}
			if (input == 2) {
				System.out.println("No. Bye.");
				player.playerQuits();
				kb.close();
			} else if (input == 3) {
				System.out.println("MGM thanks you!");
				player.playerQuits();
				kb.close();
			}
			
			roundCleanup();

		} while (player.getContinuePlaying());

	}
	
	public void roundSetup() {
		dealerMustPlay = true;
		comparisonOfHands = true;

		dealer.deal(player);

		dealer.printHand();
		player.printHand();
	}
	
	public void roundCleanup() {
		dealer.clearHand();
		player.clearHand();
		dealer.newDeckCheck();
		
	}

}
