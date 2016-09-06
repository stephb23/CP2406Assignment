import cards.TrumpCard;

/**
 * Created by Stephanie on 5/09/2016.
 */
public class TestTrumpCard {
    public static void main(String[] args) {
        TrumpCard testTrumpCard = new TrumpCard("Slide59.jpg", "Slide59", "The Geophysicist", "Specific gravity");

        System.out.println("Test 1: constructor with all parameters");
        System.out.println(testTrumpCard.toString());

        System.out.println("\nTest 2: constructor with no parameters");
        System.out.println(new TrumpCard().toString());

        System.out.println("\nTest 3: constructor with name and description parameters only");
        System.out.println(new TrumpCard("The Magician", "Magic tricks"));

        System.out.println("\nTest new setter by changing description of the first card");
        testTrumpCard.setCardDescription("This is a card");
        System.out.println("cards.Card is now: ");
        System.out.println(testTrumpCard.toString());

        System.out.println("\nTesting new getter using the edited card");
        System.out.println("Expected cards.Card Description = This is a card, Actual cards.Card Description = " +
                testTrumpCard.getCardDescription());

        System.out.println("\nAll tests complete");
    }
}
