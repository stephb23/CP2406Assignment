/**
 * Created by Stephanie on 2/09/2016.
 */
public class TestCard {
    public static void main(String[] args) {
        Card testCard1 = new Card();
        Card testCard2 = new Card("The Joker");
        Card testCard3 = new Card("HarleyQuinn.jpg", "HarleyQuinn", "The Queen");

        System.out.println("Testing constructor methods...");
        System.out.println("Card 1: " + testCard1.toString());
        System.out.println("Card 2: " + testCard2.toString());
        System.out.println("Card 3: " + testCard3.toString() + "\n");

        testCard1.setCardName("The Enchantress");
        testCard1.setImageFile("MagicCard.jpg");
        testCard1.setImageName("MagicCard");

        System.out.println("Testing setter methods... modifying card 1");
        System.out.println("Expected: \tCard Name = The Enchantress, Image Name = MagicCard, Image File = MagicCard.jpg");
        System.out.println("Actual: \t" + testCard1.toString());
        System.out.println();

        System.out.println("Testing getter methods... getting values from Card 3");
        System.out.println("Expected Name = The Queen, Actual Name = " + testCard3.getCardName());
        System.out.println("Expected Image Name = HarleyQuinn, Actual Name = " + testCard3.getImageName());
        System.out.println("Expected Image File = HarleyQuinn.jpg, Actual Name = " + testCard3.getImageFile());

        System.out.println("\nTEST COMPLETE");
    }
}
