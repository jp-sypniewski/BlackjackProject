package com.skilldistillery.blackjack;

public enum Suit {
	DIAMOND("diamond"), HEART("heart"), CLUB("club"), SPADE("spade");
	
	private String name;
	
	private Suit(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	
}
