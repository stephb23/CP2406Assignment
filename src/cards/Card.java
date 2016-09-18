package cards;

/**
 * Created by Stephanie on 2/09/2016.
 *
 * This class is the base class for all cards and implements methods & variables that are relevant to all other
 * required cards.
 *
 */

public class Card {
    private String imageFile, imageName, cardName, type;

    // Default constructor
    public Card() {

    }

    // Constructor with card name only
    public Card(String cardName) {
        this.cardName = cardName;
    }

    // Constructor with image file, image name, and card name
    public Card(String imageFile, String imageName, String cardName) {
        this.imageFile = imageFile;
        this.imageName = imageName;
        this.cardName = cardName;
    }

    // Getters for all cards. No public setters provided as Cards should be immutable from the outside.
    public String getImageFile() {
        return imageFile;
    }

    public String getImageName(){
        return imageName;
    }

    public String getCardName() {
        return cardName;
    }

    public String getType() {
        return type;
    }

    protected void setType(String type) {this.type = type;}

    // Basic toString() method for the default card
    @Override
    public String toString() {
        return ("Card name: " + cardName);
    }
}
