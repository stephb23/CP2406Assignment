package players;

import cards.Card;
import cards.PlayCard;
import cards.TrumpCard;

/**
 * Created by Stephanie on 6/09/2016.
 */
public class HumanPlayer extends Player{

    public HumanPlayer(String name){
        super(name);
    }

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

    public Card playCard(int index) {
        Card card = getCardAt(index);
        removeCard(index);
        return card;
    }

    public boolean canPlayCard(Card currentCard, String currentCategory) {

        if (currentCard.getType().equals("trump")) {
            return true;
        } else {
            for (int i = 0; i < getHandSize(); ++i) {
                if (getCardAt(i).getType().equals("trump")) {
                    return true;
                } else {
                    PlayCard currentPlayCard = (PlayCard) currentCard;
                    PlayCard playCard = (PlayCard) getCardAt(i);
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

        return false;
    }
}
