import cards.Card;

/**
 * Created by Stephanie on 2/09/2016.
 */
public class TestCard {
    public static void main(String[] args) {
        Card testCard1 = new Card();
        Card testCard2 = new Card("The Joker");
        Card testCard3 = new Card("HarleyQuinn.jpg", "HarleyQuinn", "The Queen");

        System.out.println("Testing constructor methods...");
        System.out.println("Default constructor: " + testCard1.toString());
        System.out.println("Constructor with card name only: " + testCard2.toString());
        System.out.println("Constructor with image file, image name, and card name: " + testCard3.toString() + "\n");


        System.out.println("Testing getter methods... getting values from card 3");
        System.out.println("Expected Name = The Queen, Actual Name = " + testCard3.getCardName());
        System.out.println("Expected Image Name = HarleyQuinn, Actual Name = " + testCard3.getImageName());
        System.out.println("Expected Image File = HarleyQuinn.jpg, Actual Name = " + testCard3.getImageFile());

        System.out.println("\nTEST COMPLETE");
    }
}
