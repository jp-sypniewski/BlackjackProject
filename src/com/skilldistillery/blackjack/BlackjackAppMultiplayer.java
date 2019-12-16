package com.skilldistillery.blackjack;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BlackjackAppMultiplayer {
	private Table table;
	private Player player;
	private Scanner kb;
	private int input = 0;

	public static void main(String[] args) {
		BlackjackAppMultiplayer bjam = new BlackjackAppMultiplayer();
		bjam.run();
	}

	public void run() {
		table = new Table(2);
		player = new Player();
		kb = new Scanner(System.in);

		startUp();

		do {
			roundSetup();

			// dealer blackjack

			if (table.getDealer().dealerHandValue() == 21) {

				System.out.println("Dealer has Blackjack.");
				table.getDealer().handIsDone();

				for (Player player : table.getPlayersList()) {

					if (player.playerHandValue() == 21) {
						System.out.println("Player " + player.getId() + ": Push");
					} else {
						System.out.println("Player " + player.getId() + ": Loss");
					}
				}
			}

			if (!table.getDealer().getHandDone()) {
				for (Player player : table.getPlayersList()) {

					// check player blackjack

					if (player.playerHandValue() == 21) {
						System.out.println("Player " + player.getId() + ": Blackjack!");
						player.handIsDone();
					} else {

						// or player gets to hit until bust

						while (!player.playerBusts()) {

							System.out.println(
									"How would player " + player.getId() + " like to proceed?\t[Enter Integer]");
							System.out.println("1: Hit");
							System.out.println("2: Stay");

							try {
								input = kb.nextInt();
							} catch (InputMismatchException e) {
								System.err.println("WRONG INPUT TYPE");
								kb.next();
							}

							// player hits

							if (input == 1) {
								System.out.println(table.getDealer().playerHits(player));
								System.out.println("Player is now at: " + player.playerHandValue());

								// any input other than 1

							} else {
								break;
							}

							// check player bust after hit

							if (player.playerBusts()) {
								System.out.println("Player " + player.getId() + " busts.");
								player.handIsDone();
								break;
							}
						}
					}
				}
			}

			// if (neither dealer nor player blackjack, player not busted)
			// dealer hits according to rules (if hand value less than 17, or 17 with soft
			// ace)

			if (table.playerMustCompareToDealer()) {

				table.getDealer().printFullHand();
				while (table.getDealer().dealerHandValue() < 17 || table.getDealer().hasSoftSeventeen()) {
					System.out.println("Dealer must hit.");
					System.out.println(table.getDealer().dealerHits());
					System.out.println("Dealer is now at: " + table.getDealer().dealerHandValue());
				}

				// check dealer bust after hit

				if (table.getDealer().dealerBusts()) {
					table.getDealer().handIsDone();
					for (Player player : table.getPlayersList()) {
						if (!player.getHandDone()) {
							System.out.println("Since dealer busted, player " + player.getId() + " wins.");
						}
					}
				}
			}

			// if (neither dealer nor player blackjack, neither player bust)
			// compare hand values to determine winner

			if (table.playerMustCompareToDealer()) {

				System.out.println("Dealer: " + table.getDealer().dealerHandValue());

				for (Player player : table.getPlayersList()) {

					System.out.println("Player " + player.getId() + ": " + player.playerHandValue());
					if (table.getDealer().dealerHandValue() == player.playerHandValue()) {
						System.out.println("Push");
					} else if (table.getDealer().dealerHandValue() > player.playerHandValue()) {
						System.out.println("Dealer wins.");
					} else {
						System.out.println("Player " + player.getId() + " wins!");
					}

				}
			}

			// player choice to re-deal
			for (Player player : table.getPlayersList()) {
				
				
				System.out.println("Player "+player.getId()+"Play again?\t[Enter Integer]");
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
			}
			// clear hands, check if new deck is needed

			roundCleanup();

		} while (table.getPlayersList().size() > 0);

		// END RUN

	}

	public void startUp() {

		System.out.println("Welcome to BlackjackApp!");
		System.out.println("In this game, there are multiple (user) players against the dealer.");
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

		table.getDealer().deal(table);

		table.getDealer().printHandAfterDeal();

		for (Player player : table.getPlayersList()) {
			player.printHand();
		}
	}

	public void roundCleanup() {

		System.out.println("**Round over, cards in, please**");

		table.getDealer().clearHand();
		table.getDealer().resetHandIsDone();
		for (Player player : table.getPlayersList()) {
			player.clearHand();
			player.resetHandIsDone();
		}

		table.getDealer().newDeckCheck();

	}

}
