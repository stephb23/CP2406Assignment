import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by Stephanie on 5/09/2016.
 */
public class XMLDeckBuilder implements DeckBuilder{

    public enum CardField {
        FILENAME, IMAGE_NAME, CARD_TITLE
    }

    public enum PlayCardField {
        FILENAME, IMAGE_NAME, CARD_TITLE, CHEMISTRY, CLASSIFICATION,
        CRYSTAL_SYSTEM, OCCURRENCE, HARDNESS, SPECIFIC_GRAVITY, CLEAVAGE, CRUSTAL_ABUNDANCE, ECONOMIC_VALUE
    }

    public enum TrumpCardField {
        FILENAME, IMAGE_NAME, CARD_NAME, DESCRIPTION
    }

    public static void main(String[] args) {
        final int cardTypeIndex = 3;
        String fileName, imageName, cardTitle, chemistry, classification, crystalSystem, hardness, specificGravity,
                cleavage, crustalAbundance, economicValue;
        ArrayList<String> occurrences = new ArrayList<>();
        int shiftValue = 0;
        PlayCard playCard;
        TrumpCard trumpCard;

        try {
            // Read our XML file into a Java Document
            File xmlFile = new File("MstCards_151021.plist");
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(xmlFile);

            // These are the nodes you're looking for *jedi wave*
            // Item 0 in this list is a write-off; it's the whole file.
            // Last three items are game info cards, not playing cards. IGNORE.
            NodeList nodeList = document.getElementsByTagName("dict");
            for (int i = 1; i < nodeList.getLength() - 3; ++i) {
                Node node = nodeList.item(i);
                if (node.getNodeType()== Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    NodeList allKeys = element.getElementsByTagName("key");
                    NodeList allValues = element.getElementsByTagName("string");

                    if (allKeys.item(cardTypeIndex).getTextContent().equals("play")){
                        occurrences.add(allValues.item(PlayCardField.OCCURRENCE.ordinal()).getTextContent());

                        if (allValues.getLength() == 14) {
                            shiftValue = 2;
                            occurrences.add(allValues.item(PlayCardField.OCCURRENCE.ordinal() + 1).getTextContent());
                            occurrences.add(allValues.item(PlayCardField.OCCURRENCE.ordinal() + 2).getTextContent());
                        } else if (allValues.getLength() == 13) {
                            shiftValue = 1;
                            occurrences.add(allValues.item(PlayCardField.OCCURRENCE.ordinal() + 1).getTextContent());
                        } else {
                            shiftValue = 0;
                        }

                        fileName = allValues.item(PlayCardField.FILENAME.ordinal()).getTextContent();
                        imageName = allValues.item(PlayCardField.IMAGE_NAME.ordinal()).getTextContent();
                        cardTitle = allValues.item(PlayCardField.CARD_TITLE.ordinal()).getTextContent();
                        chemistry = allValues.item(PlayCardField.CHEMISTRY.ordinal()).getTextContent();
                        classification = allValues.item(PlayCardField.CLASSIFICATION.ordinal()).getTextContent();
                        crystalSystem = allValues.item(PlayCardField.CRYSTAL_SYSTEM.ordinal()).getTextContent();
                        hardness = allValues.item(PlayCardField.HARDNESS.ordinal() + shiftValue).getTextContent();
                        specificGravity = allValues.item(PlayCardField.SPECIFIC_GRAVITY.ordinal() + shiftValue).getTextContent();
                        cleavage = allValues.item(PlayCardField.CLEAVAGE.ordinal() + shiftValue).getTextContent();
                        crustalAbundance = allValues.item(PlayCardField.CRUSTAL_ABUNDANCE.ordinal() + shiftValue).getTextContent();
                        economicValue = allValues.item(PlayCardField.ECONOMIC_VALUE.ordinal() + shiftValue).getTextContent();

                        playCard = new PlayCard(fileName, imageName, cardTitle, chemistry, classification, crystalSystem,
                                occurrences, hardness, specificGravity, cleavage, crustalAbundance, economicValue);

                        System.out.println(playCard.toString()+ "\n");
                        cardDeck.add(playCard);
                    }
                }
            }



        } catch (Exception e) {
            System.out.println("Error reading the XML File");
        }
    }

    public void addCard(Card card) {
        cardDeck.add(card);
    }

    public void addCardAt(Card card, int index) {
        cardDeck.add(index, card);
    }

    public Deck toDeck() {
        return new Deck(cardDeck);
    }
}
