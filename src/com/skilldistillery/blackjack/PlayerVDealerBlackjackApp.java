package com.skilldistillery.blackjack;

import java.util.Scanner;

public class PlayerVDealerBlackjackApp {
	BlackjackHand playerHand;
	BlackjackHand dealerHand;
	Deck deck;
	Scanner kb;
	int input;
	boolean quit = true;
	
	
	
	
	public static void main(String[] args) {
		PlayerVDealerBlackjackApp pvd = new PlayerVDealerBlackjackApp();
		pvd.run();
	}
	
	public void run() {
		kb = new Scanner(System.in);
		deck = new Deck();
		dealerHand = new BlackjackHand(deck);
		playerHand = new BlackjackHand(deck);
		
		do {
			showDealerHand();
			showPlayerHand();
			
			
			
			
			
		} while (quit);		
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

}
