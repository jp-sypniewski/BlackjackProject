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
		BlackjackAppMultiplayer bjam = new BlackjackAppMultiplayer();
		bjam.run();
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

			if (table.getDealer().dealerHandValue() == 21) {

				System.out.println("Dealer has Blackjack.");

				for (Player player : table.getPlayersList()) {


					if (player.playerHandValue() == 21) {
						System.out.println("Player " + player.getId() + ": Push");
					} else {
						System.out.println("Player " + player.getId() + ": Loss");
					}
				}
				gameMustContinue = false;
			}

			if (gameMustContinue) {
				for (Player player : table.getPlayersList()) {
					
					// check player blackjack
					
					if (player.playerHandValue() == 21) {
						System.out.println("Player " + player.getId() + ": Blackjack!");
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
				while (dealer.dealerHandValue() < 17 || table.getDealer().hasSoftSeventeen()) {
					System.out.println("Dealer must hit.");
					System.out.println(table.getDealer().dealerHits());
					System.out.println("Dealer is now at: " + table.getDealer().dealerHandValue());
				}

				// check dealer bust after hit

				if (table.getDealer().dealerBusts()) {
					
					// TODO can provide win condition to non-blackjack non-busted players here
					
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

			// player choice to re-deal

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

		gameMustContinue = true;

		table.getDealer().deal(table);

		table.getDealer().printHandAfterDeal();

		for (Player player : table.getPlayersList()) {
			player.printHand();
		}
	}

	public void roundCleanup() {

		System.out.println("**Round over, cards in, please**");

		table.getDealer().clearHand();
		for (Player player : table.getPlayersList()) {
			player.clearHand();
		}

		table.getDealer().newDeckCheck();

	}

}
