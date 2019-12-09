package com.skilldistillery.blackjack;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BlackjackAppMultiplayer {
	private Table table;
	private Dealer dealer;
	private Player player;
	private Scanner kb;
	private int input = 0;
	private boolean gameMustContinue;

	public static void main(String[] args) {
		BlackjackAppMultiplayer bja = new BlackjackAppMultiplayer();
		bja.run();
	}

	public void run() {
		table = new Table(2);
		dealer = new Dealer();
		player = new Player();
		kb = new Scanner(System.in);

		startUp();

		do {
			roundSetup();

			// dealer blackjack

			if (dealer.dealerHandValue() == 21) {

				// check dealer+player blackjack

				if (player.playerHandValue() == 21) {
					// player and dealer blackjack
					System.out.println("Push, dealer and player both have blackjack.");
				} else {
					// dealer only blackjack
					System.out.println("Dealer has blackjack, player loses.");
				}

				gameMustContinue = false;
			}

			// player only blackjack

			if (player.playerHandValue() == 21 && dealer.dealerHandValue() != 21) {
				System.out.println("Blackjack!  Player wins!");
				gameMustContinue = false;
			}

			// if (neither dealer nor player blackjack)
			// player can hit as long as player doesn't bust

			if (gameMustContinue) {

				while (!player.playerBusts() && dealer.dealerHandValue() != 21) {

					System.out.println("How would player like to proceed?\t[Enter Integer]");
					System.out.println("1: Hit");
					System.out.println("2: Stay");

					try {
						input = kb.nextInt();
					} catch (InputMismatchException e) {
						System.err.println("WRONG INPUT TYPE");
						kb.next();
					}

					// player hits TODO add try catch on above input

					if (input == 1) {
						System.out.println(dealer.playerHits(player));
						System.out.println("Player is now at: " + player.playerHandValue());

						// any input other than 1

					} else {
						break;
					}

					// check player bust after hit

					if (player.playerBusts()) {
						System.out.println("Player busts, dealer wins");
						gameMustContinue = false;
						break;
					}
				}
			}

			// if (neither dealer nor player blackjack, player not busted)
			// dealer hits according to rules (if hand value less than 17, or 17 with soft
			// ace)

			if (gameMustContinue) {

				dealer.printFullHand();
				while (dealer.dealerHandValue() < 17 || dealer.hasSoftSeventeen()) {
					System.out.println("Dealer must hit.");
					System.out.println(dealer.dealerHits());
					System.out.println("Dealer is now at: " + dealer.dealerHandValue());
				}

				// check dealer bust after hit

				if (dealer.dealerBusts()) {
					System.out.println("Dealer busts, player wins!");
					gameMustContinue = false;
				}
			}

			// if (neither dealer nor player blackjack, neither player bust)
			// compare hand values to determine winner

			if (gameMustContinue) {
				System.out.println("Dealer: " + dealer.dealerHandValue() + "\tPlayer: " + player.playerHandValue());
				if (dealer.dealerHandValue() == player.playerHandValue()) {
					System.out.println("Push");
				} else if (dealer.dealerHandValue() > player.playerHandValue()) {
					System.out.println("Dealer wins.");
				} else {
					System.out.println("Player wins!");
				}
			}

			// player choice to re-deal TODO add try catch for input

			System.out.println("Play again?\t[Enter Integer]");
			System.out.println("1: Sure!");
			System.out.println("2: Nah.");
			try {
				input = kb.nextInt();
			} catch (InputMismatchException e) {
				System.err.println("WRONG INPUT TYPE");
				kb.next();
			}

			if (input == 1) {
				System.out.println("Sounds good.");
			} else if (input == 2) {

				// player quits out

				System.out.println("Bye.");
				player.playerQuits();
				kb.close();
			}

			// clear hands, check if new deck is needed

			roundCleanup();

		} while (player.getContinuePlaying());

		// END RUN

	}

	public void startUp() {

		System.out.println("Welcome to BlackjackApp!");
		System.out.println("In this game, there is one player against the dealer.");
		System.out.println("If the player and dealer are both dealt Blackjack, the game is a push");
		System.out.println("If the player is dealt Blackjack, the player wins");
		System.out.println("If the dealer is dealt Blackjack, the dealer wins");
		System.out.println("Else, the player may hit until busted, or stay with their current hand");
		System.out.println("The dealer will hit if less than 17, or with a soft 17");
		System.out.println("On the flop, the dealer will display their second card dealt unless");
		System.out.println("\tthe second card is of value ten or an ace");
		System.out.println("Six decks are used, with a new shoe generated upon usage of 80% of the shoe");

	}

	public void roundSetup() {

		System.out.println("**Dealing a new hand**");

		gameMustContinue = true;

		dealer.deal(player);

		dealer.printHandAfterDeal();
		player.printHand();
	}

	public void roundCleanup() {

		System.out.println("**Round over, cards in, please**");

		dealer.clearHand();
		player.clearHand();
		dealer.newDeckCheck();

	}

}
