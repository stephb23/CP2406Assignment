import cards.Card;
import decks.Deck;

import java.util.ArrayList;

/**
 * Created by Stephanie on 6/09/2016.
 */
public class TestDeck {
    public static void main(String[] args) {
        ArrayList<Card> cardDeck = new ArrayList<>();
        cardDeck.add(new Card("Test cards.Card 1"));
        cardDeck.add(new Card("Test cards.Card 2"));
        cardDeck.add(new Card("Test cards.Card 3"));

        Deck deck = new Deck(cardDeck);
        System.out.println("Creating a sample deck with three cards. decks.Deck is...");
        System.out.println(deck.toString());

        System.out.println("Adding new card to end of deck. decks.Deck is now...");
        deck.addCard(new Card("Test cards.Card 4"));
        System.out.println(deck.toString());

        System.out.println("Removing card from start of deck. decks.Deck is now...");
        deck.removeCardAt(0);
        System.out.println(deck.toString());

        System.out.println("Adding new card between cards 2 and 3. decks.Deck is now...");
        deck.addCardAt(new Card("Test cards.Card 2.5"), 1);
        System.out.println(deck.toString());

        System.out.println("Get card at index 2...");
        System.out.println(deck.getCardAt(2).toString());

        System.out.println("Shuffling the deck. decks.Deck is now...");
        deck.shuffle();
        System.out.println(deck.toString());

        System.out.println("Shuffling deck one more time for good measure. decks.Deck is now...");
        deck.shuffle();
        System.out.println(deck.toString());

    }
}
