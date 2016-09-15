import decks.Deck;
import decks.XMLDeckBuilder;

/**
 * Created by Stephanie on 6/09/2016.
 */
public class TestDeckBuilder {
    public static void main(String[] args) {
        XMLDeckBuilder builder = new XMLDeckBuilder();
        Deck deck = builder.toDeck();

        System.out.println("The deck contains " + deck.length() + " cards");
        System.out.println("The deck contains the following cards: ");
        System.out.println(deck.toString());

        for (int i = 0; i < deck.length(); ++i) {
            System.out.println(deck.getCardAt(i).toString());
        }

        System.out.println("\nNow let's shuffle the cards! decks.Deck now is: ");
        deck.shuffle();
        System.out.println(deck.toString());
    }
}
