## Blackjack Project

### Week 5 Homework - Skill Distillery

#### Overview

This project provides three Blackjack application classes for slightly different versions of the card game:
- SinglePlayerBlackjackApp - one player with a single deck of cards
- PlayerVDealerBlackjackApp - one player with a dealer, with a single deck of cards
- BlackjackApp - one player with a dealer, playing with multiple decks, dealer only shows one card at initial deal (unless showing card is ace/face/ten), dealer capable of acting on soft aces

Each App class prompts the player for their choice of hit or stay for a given hand, and allows a player to quit out after each hand.  The deck of cards shrinks in size with each hand, and reforms and shuffles automatically should the deck shrink down to a certain size.


#### Concepts

- Object oriented programming
  - Instead of running everything in one class, the project includes a number of different classes, in which higher level classes include fields of other objects.
  - Project includes classes of Card, Deck, and Hand.   These can be of use outside of the Blackjack project.
  - This methodology provides a simpler way to speak to the project.  E.g. the dealer has both a deck and a hand, with the deck and hand objects manipulated by first calling the dealer object.
- Use of collection objects to organize and manage data
  - The deck object is constructed with an ArrayList to hold card objects, which are then removed from the deck object and passed to the hand objects held by the player and the dealer
- Complex/chained logic statements
  - With the implementation of a soft ace, the logic used to calculate hand values and bust status must also manipulate how the player/dealer objects may hit and stay given a certain hand.


#### Technologies Used

- Java


#### Lessons Learned

- Importance of refactoring/readability
  - The logic required for just the player has repetition with slight changes, so keeping the code "dry" allows for easier reading of the run() method within the app
    - In moving from the PlayerVDealerBlackjackApp to the BlackjackApp, classes for a Player and a Dealer were added.  These two classes contain functionality previously within the run() method.  The logic of the game within the run() in BlackjackApp is easier to follow as a result.
- Manipulation of values from enums
  - With a soft ace, the sum value of a blackjack hand can be 10 less than a hard ace at 11.  Since the enum has a final value of 11, the value calculation takes into account the total value of the hand and provides for scenarios in which an ace value of 1 is needed.
- Simplify logic where possible
  - The BlackjackApp was using multiple booleans to determine whether the dealer needed to draw and then compare the players score with the dealer.  By reframing the logic of the statements into a single continue boolean, the program becomes more simple.
