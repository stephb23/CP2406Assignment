/**
 * Created by Stephanie on 2/09/2016.
 */
public class Card {
    private String imageFile, imageName, cardName;

    public Card() {
        // let all set to null
    }

    public Card(String cardName) {
        this.cardName = cardName;
    }

    public Card(String imageFile, String imageName, String cardName) {
        this.imageFile = imageFile;
        this.imageName = imageName;
        this.cardName = cardName;
    }

    public void setImageFile(String imageFile) {
        this.imageFile = imageFile;
    }

    public String getImageFile() {
        return imageFile;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageName(){
        return imageName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardName() {
        return cardName;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Card Name = " + cardName);
        builder.append(", Image Name = " + imageName);
        builder.append(", Image File = " + imageFile);

        return builder.toString();
    }
}
