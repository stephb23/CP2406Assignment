package players;

import cards.Card;
import cards.PlayCard;
import cards.TrumpCard;

/**
 * Created by Stephanie on 6/09/2016.
 *
 * This is the class that human player objects can be created from, and provides functionality such as viewing their
 * hand and playing a card.
 *
 */

public class HumanPlayer extends Player{

    // Constructor
    public HumanPlayer(String name){
        super(name);
    }

    // Iterate through all cards in the player's hand and display them
    public void viewAllCards() {
        System.out.println("You have the following cards in your hand");
        for (int i = 1; i <= getHandSize(); ++i) {
            System.out.print(i + ".\t");
            Card card = getCardAt(i -1);
            if (card.getType().equals("play")) {
                System.out.println(((PlayCard) card).toShortString());
            } else if (card.getType().equals("trump")) {
                System.out.println(((TrumpCard) card).toShortString());
            }
        }
    }

    // Play a card by getting the card, removing it from the hand, and returning it to the game controller.
    public Card playCard(int index) {
        Card card = getCardAt(index);
        removeCard(index);
        return card;
    }

    // This method checks whether the player has any cards that can be played
    public boolean canPlayCard(Card currentCard, String currentCategory) {

        // If the card to beat is a trump card, then the player can definitely play; return true.
        if (currentCard.getType().equals("trump")) {
            return true;
        } else {
            // If the card to beat is a play card we need to look more carefully.
            for (int i = 0; i < getHandSize(); ++i) {
                // If the player's hand contains a trump card of their own, they can play it, return true.
                if (getCardAt(i).getType().equals("trump")) {
                    return true;
                } else {
                    // If we have no trumps present anywhere ever, try to find a play card that could beat it.
                    PlayCard currentPlayCard = (PlayCard) currentCard;
                    PlayCard playCard = (PlayCard) getCardAt(i);
                    /* Look for the current category and then check whether the play card has a value greater than
                    the current card for that category. Return true if any card that can be played is found,
                    provide no information as to which one - that's for the player to work out!!*/
                    if (currentCategory.equals("hardness")) {
                        if (playCard.getHardnessAsDouble() > currentPlayCard.getHardnessAsDouble()) {
                            return true;
                        }
                    } else if (currentCategory.equals("specific gravity")) {
                        if (playCard.getSpecificGravityAsDouble() > currentPlayCard.getSpecificGravityAsDouble()) {
                            return true;
                        }
                    } else if (currentCategory.equals("cleavage")) {
                        if (playCard.getCleavageAsInt() > currentPlayCard.getCleavageAsInt()) {
                            return true;
                        }
                    } else if (currentCategory.equals("crustal abundances")) {
                        if (playCard.getCrustalAbundancesAsInt() > currentPlayCard.getCrustalAbundancesAsInt()) {
                            return true;
                        }
                    } else if (currentCategory.equals("economic value")) {
                        if (playCard.getEconomicValueAsInt() > currentPlayCard.getEconomicValueAsInt()) {
                            return true;
                        }
                    }
                }
            }
        }

        // If we get all the way to here then there's nothing at all the player can play, so return false.
        return false;
    }
}
