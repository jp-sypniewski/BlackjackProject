package com.skilldistillery.blackjack;

import java.util.ArrayList;
import java.util.List;

public class Table {
	private Dealer dealer;
	private List<Player> players;

	public Table(int numPlayers) {
		dealer = new Dealer();
		players = new ArrayList<Player>(numPlayers);
		for (int i = 0; i < numPlayers; i++) {
			players.add(new Player());
		}
	}

	public void addPlayer() {
		if (players.size() < 6) {
			players.add(new Player());
		} else {
			System.out.println("Too many players at the table!");
		}
	}

	public List<Player> getPlayersList() {
		return players;
	}

	public Player getIthPlayer(int i) {
		return players.get(i);
	}

	public Dealer getDealer() {
		return dealer;
	}
	
	public boolean playerMustCompareToDealer() {
		boolean done = false;
		
		// this method will be used to decide if the dealer needs to bother hitting, assuming dealer didn't get blackjack
		// idea is that if any player needs to have their score checked (i.e. doesn't have 21 OR busted)
		// should return true if dealer needs to hit
		
		if (dealer.dealerBusts()) {
			return false;
		}
		
		for (Player player : players) {
			if (!(player.playerHandValue() == 21 || player.playerBusts())) {
				return true;
			}
		}
		
		
		
		return done;
	}

}
