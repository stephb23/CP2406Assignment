package cards;
/**
 * Created by Stephanie on 5/09/2016.
 *
 * Class for creating and managing "trump" type cards in the Supertrump game. Only real difference to the base card
 * class is the inclusion of a type and a card description.
 *
 */

public class TrumpCard extends Card {
    private String cardDescription;
    private final String TYPE_STRING = "trump";

    // Default constructor
    public TrumpCard() {
        setType(TYPE_STRING);
    }

    // Constructor for card name & description only
    public TrumpCard(String cardName, String cardDescription) {
        super(cardName);
        this.cardDescription = cardDescription;
        setType(TYPE_STRING);
    }

    // Constructor for all parameters
    public TrumpCard(String imageFile, String imageName, String cardName, String cardDescription) {
        super(imageFile, imageName, cardName);
        setType(TYPE_STRING);
        this.cardDescription = cardDescription;
    }

    // Getter for card description
    public String getCardDescription() {
        return cardDescription;
    }

    // Overriden to string method
    @Override
    public String toString() {
        String trumpString = super.toString();
        trumpString += ", Card Description = " + cardDescription;
        return trumpString;
    }

    // Concise & formatted string
    public String toShortString() {
        return "Name: " + getCardName() + ", Trump Suit: " + getCardDescription();
    }
}
