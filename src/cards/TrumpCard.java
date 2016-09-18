package cards;

import cards.Card;

/**
 * Created by Stephanie on 5/09/2016.
 */
public class TrumpCard extends Card {
    private String cardDescription;
    private final String TYPE_STRING = "trump";

    public TrumpCard() {
        setType(TYPE_STRING);
    }

    public TrumpCard(String cardName, String cardDescription) {
        super(cardName);
        this.cardDescription = cardDescription;
        setType(TYPE_STRING);
    }

    public TrumpCard(String imageFile, String imageName, String cardName, String cardDescription) {
        super(imageFile, imageName, cardName);
        setType(TYPE_STRING);
        this.cardDescription = cardDescription;
    }

    public void setCardDescription(String cardDescription) {
        this.cardDescription = cardDescription;
    }

    public String getCardDescription() {
        return cardDescription;
    }

    @Override
    public String toString() {
        String trumpString = super.toString();
        trumpString += "\nCard Description = " + cardDescription;
        return trumpString;
    }

    public String toShortString() {
        String trumpString = "Name: " + getCardName() + ", Trump Suit: " + getCardDescription();
        return trumpString;
    }
}
