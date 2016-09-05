import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

/**
 * Created by Stephanie on 5/09/2016.
 */
public class Deck {
    private ArrayList<Card> cardDeck;

    public Deck(ArrayList<Card> cardDeck) {
        this.cardDeck = cardDeck;
    }

    public ArrayList<Card> getAllCardsInDeck() {
        return cardDeck;
    }

    public Card getCardAt(int index) {
        return cardDeck.get(index);
    }

    public void removeCardAt(int index) {
        cardDeck.remove(index);
    }

    public void shuffleDeck() {
        long seed = System.nanoTime();
        Collections.shuffle(cardDeck, new Random(seed));
    }


}
