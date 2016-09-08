import cards.Card;
import decks.Deck;

import java.util.ArrayList;

/**
 * Created by Stephanie on 6/09/2016.
 */
public class TestDeck {
    public static void main(String[] args) {
        ArrayList<Card> cardDeck = new ArrayList<>();
        cardDeck.add(new Card("Card 1"));
        cardDeck.add(new Card("Card 2"));
        cardDeck.add(new Card("Card 3"));

        Deck deck = new Deck(cardDeck);
        System.out.println("Creating a sample deck with three cards. Deck is...");
        System.out.println(deck.toString());

        System.out.println("Adding new card to end of deck. Deck is now...");
        deck.addCard(new Card("Card 4"));
        System.out.println(deck.toString());

        System.out.println("Removing card from start of deck. Deck is now...");
        deck.removeCardAt(0);
        System.out.println(deck.toString());

        System.out.println("Adding new card between cards 2 and 3. Deck is now...");
        deck.addCardAt(new Card("Card 2.5"), 1);
        System.out.println(deck.toString());

        System.out.println("Get card at index 2...");
        System.out.println(deck.getCardAt(2).toString());

        System.out.println("Add new cards");
        for (int i = 5; i < 15; ++i){
            deck.addCard(new Card("Card " + i));
        }
        System.out.println("Deck now contains " + deck.length() + " cards.");

        System.out.println("Shuffling the deck. Deck is now...");
        deck.shuffle();
        System.out.println(deck.toString());

        System.out.println("Shuffling deck one more time for good measure. Deck is now...");
        deck.shuffle();
        System.out.println(deck.toString());

        System.out.println("Getting hand of cards...");
        ArrayList<Card> hand = deck.dealHand();
        for (int i = 0; i < hand.size(); ++i) {
            System.out.println(hand.get(i).toString());
        }

        System.out.println("Cards left in deck: ");
        deck.toString();

        System.out.println("Deal another card");
        Card card = deck.dealCard();
        System.out.println("Card is: " + card.toString());

        System.out.println("Deck is now: " );
        System.out.println(deck.toString());

    }
}
