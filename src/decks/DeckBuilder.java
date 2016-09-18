package decks;

import cards.Card;
import decks.Deck;

import java.util.ArrayList;

/**
 * Created by Stephanie on 5/09/2016.
 *
 * Interface that defines the requirements of a DeckBuilder, regardless of which type of file it may need to read
 * from
 *
 */

public interface DeckBuilder {
    ArrayList<Card> cardDeck = new ArrayList<>();
    Deck toDeck();
}
