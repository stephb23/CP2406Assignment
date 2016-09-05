import java.util.ArrayList;

/**
 * Created by Stephanie on 5/09/2016.
 */
public interface DeckBuilder {
    ArrayList<Card> cardDeck = new ArrayList<>();
    void addCard(Card newCard);
    void addCardAt(Card newCard, int index);
    Deck toDeck();
}
