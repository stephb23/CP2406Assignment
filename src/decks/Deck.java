package decks;

import cards.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Created by Stephanie on 5/09/2016.
 *
 * This class allows the creation of a deck object which holds all of the cards required for a game.
 * It provides useful functionality such as shuffling and dealing.
 *
 */

public class Deck {
    private ArrayList<Card> cardDeck;

    // Constructor for creating cardDeck from provided array list of cards.
    public Deck(ArrayList<Card> cardDeck) {
        this.cardDeck = cardDeck;
    }

    // Returns the card at a given index
    public Card getCardAt(int index) {
        return cardDeck.get(index);
    }

    // Shuffles the entire deck based on a pseudorandom seed
    public void shuffle() {
        long seed = System.nanoTime();
        Collections.shuffle(cardDeck, new Random(seed));
    }

    // Returns the size of the deck.
    public int length() {
        return cardDeck.size();
    }

    // Returns the first 8 cards of the deck
    public ArrayList<Card> dealHand() {
        ArrayList<Card> hand = new ArrayList<>();

        // add the cards to be dealt to the player into an array list
        for (int i = 0; i < 8; ++i) {
            hand.add(cardDeck.get(i));
        }

        // remove the dealt cards from the deck
        for (int i = 0; i < 8; ++i) {
            cardDeck.remove(0);
        }

        return hand;
    }

    // Returns a single card from the top of the deck & removes it from the deck
    public Card dealCard() {
        Card card = cardDeck.get(0);
        cardDeck.remove(0);
        return card;
    }

    // To string method to view the whole deck.
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
