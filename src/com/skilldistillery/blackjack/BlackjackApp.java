package com.skilldistillery.blackjack;

import java.util.Scanner;

public class BlackjackApp {
	Dealer dealer;
	Player player;
	Scanner kb;

	public static void main(String[] args) {
		BlackjackApp bja = new BlackjackApp();
		bja.run();
	}
	
	public void run() {
		dealer = new Dealer();
		player = new Player();
		kb = new Scanner(System.in);
		boolean dealerMustPlay;
		
		
		do {
			dealerMustPlay = true;
			
			dealer.deal(player);
			
			dealer.printDeal();
			player.printHand();
			
			if (dealer.dealerHandValue() == 21) {
				if (player.playerHandValue() == 21) {
					// push
				}
				else {
					// player loses, print out continue choice w condition
				}
				dealerMustPlay = false;
			}
			
			if (player.playerHandValue() == 21) {
				// player wins
				dealerMustPlay = false;
			}
			else {
				//player decides if hit or not
				// WHILE LOOP, if player busts then change dealerMustPlay to false
			}
			
			while (dealerMustPlay) {
				
				
				
				
				
				
			}
			
			
			
			
			
			
			
		} while (player.getContinuePlaying());
		
	}

}
