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
}
