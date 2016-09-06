package cards;

import cards.Card;

/**
 * Created by Stephanie on 5/09/2016.
 */
public class TrumpCard extends Card {
    private String cardDescription;

    public TrumpCard() {
        // let all set to null
    }

    public TrumpCard(String cardName, String cardDescription) {
        super(cardName);
        this.cardDescription = cardDescription;
    }

    public TrumpCard(String imageFile, String imageName, String cardName, String cardDescription) {
        super(imageFile, imageName, cardName);
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
        trumpString += "\ncards.Card Description = " + cardDescription;
        return trumpString;
    }
}
