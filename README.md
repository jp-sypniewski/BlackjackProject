## Blackjack Project

### Week 5 Homework - Skill Distillery

#### Overview

This project provides three Blackjack application classes for slightly different versions of the card game:
- SinglePlayerBlackjackApp - one player with a single deck of cards
- PlayerVDealerBlackjackApp - one player with a dealer, with a single deck of cards
- BlackjackApp - one player with a dealer on multiple decks with dealer capable of acting on soft aces


#### Concepts

- Object oriented programming
  - Instead of running everything in one class, the project includes a number of different classes.
  - Project includes classes of Card, Deck, and Hand.   These can be of use outside of the Blackjack project.
  - This methodology provides a simpler way to speak to the project.  E.g. the dealer has both a deck and a hand, with the deck and hand objects manipulated by first calling the dealer object.
- Complex logic statements - the player is given different options depending on the current hand.

#### Technologies Used

- Java

#### Lessons Learned

- Visibility of fields
- Importance of refactoring/readability
  - The logic required for just the player has repetition with slight changes, so keeping the code "dry" allows for easier reading of the run() method within the app
    - In moving from the PlayerVDealerBlackjackApp to the BlackjackApp, classes for a Player and a Dealer were added.  These two classes contain functionality previously within the run() method.  The logic of the game within the run() in BlackjackApp is easier to follow as a result.
- Manipulation of values from enums
  - With a soft ace, the sum value of a blackjack hand can be 10 less than a hard ace at 11.  Since the enum has a final value of 11, the value calculation takes into account the total value of the hand and provides for scenarios in which an ace value of 1 is needed.
