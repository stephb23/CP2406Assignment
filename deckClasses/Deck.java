import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

/**
 * Created by Stephanie on 5/09/2016.
 */
public class Deck {
    private ArrayList<Card> cardDeck;

    public Deck() {
        cardDeck = new ArrayList<>();
    }

    public Deck(ArrayList<Card> cardDeck) {
        this.cardDeck = cardDeck;
    }

    public Card getCardAt(int index) {
        return cardDeck.get(index);
    }

    public void addCard(Card card) {
        cardDeck.add(card);
    }

    public void addCardAt(Card card, int index) {
        cardDeck.add(index, card);
    }

    public void removeCardAt(int index) {
        cardDeck.remove(index);
    }

    public void shuffle() {
        long seed = System.nanoTime();
        Collections.shuffle(cardDeck, new Random(seed));
    }

    public int length() {
        return cardDeck.size();
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        Card card;
        for (int i = 0; i < cardDeck.size(); ++i) {
            card = cardDeck.get(i);
            builder.append(card.getCardName() + ". ");
            if (i%5 == 0 && i != 0) {
                builder.append("\n");
            }
        }
        return builder.toString();
    }

}
