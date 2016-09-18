package decks;

import cards.PlayCard;
import cards.TrumpCard;
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
 *
 * Builds a deck object from the provided XML file
 *
 */
public class XMLDeckBuilder implements DeckBuilder {

    // Enum for identifying the fields relevant to play cards
    private enum PlayCardField {
        FILENAME, IMAGE_NAME, CARD_TITLE, CHEMISTRY, CLASSIFICATION,
        CRYSTAL_SYSTEM, OCCURRENCE, HARDNESS, SPECIFIC_GRAVITY, CLEAVAGE, CRUSTAL_ABUNDANCE, ECONOMIC_VALUE
    }

    // Enum for identifying the fields relevant to trump cards
    private enum TrumpCardField {
        FILENAME, IMAGE_NAME, CARD_NAME, DESCRIPTION
    }

    public XMLDeckBuilder() {
        final int cardTypeIndex = 3; // index where the card type can be found.
        String fileName, imageName, cardTitle, chemistry, classification, crystalSystem, hardness, specificGravity,
                cleavage, crustalAbundance, economicValue;
        ArrayList<String> occurrences;
        int shiftValue; // shift value alters where the parameters after occurrences are read from
        String description;
        PlayCard playCard;
        TrumpCard trumpCard;

        try {
            // Read our XML file into a Java Document
            File xmlFile = new File("MstCards_151021.plist");
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(xmlFile);

            // These are the nodes you're looking for *jedi mind trick wave*
            // Item 0 in this list is a write-off; it's the whole file.
            NodeList nodeList = document.getElementsByTagName("dict");

            // Iterate through the list of nodes
            for (int i = 1; i < nodeList.getLength(); ++i) {
                Node node = nodeList.item(i);
                if (node.getNodeType()== Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    NodeList allKeys = element.getElementsByTagName("key"); // get a list of all things ID'd as a key
                    NodeList allValues = element.getElementsByTagName("string"); // get a list of all things ID'd as a string

                    if (allKeys.item(cardTypeIndex).getTextContent().equals("play")){
                        // Create an array list for occurrences
                        occurrences = new ArrayList<>();

                        // Add the first occurrence value
                        occurrences.add(allValues.item(PlayCardField.OCCURRENCE.ordinal()).getTextContent());

                        /* Number of occurrences varies between 1-3; checking the length of the list of strings allows
                        for identification of the number present in each card.
                        */
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

                        // Extract all other parameters from the XML file
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

                        // Create a play card using the parameters found
                        playCard = new PlayCard(fileName, imageName, cardTitle, chemistry, classification, crystalSystem,
                                occurrences, hardness, specificGravity, cleavage, crustalAbundance, economicValue);

                        // Add the play card to the deck
                        cardDeck.add(playCard);

                    } else if (allKeys.item(cardTypeIndex).getTextContent().equals("trump")) {
                        // Extract all parameters from the XML file
                        fileName = allValues.item(TrumpCardField.FILENAME.ordinal()).getTextContent();
                        imageName = allValues.item(TrumpCardField.IMAGE_NAME.ordinal()).getTextContent();
                        cardTitle = allValues.item(TrumpCardField.CARD_NAME.ordinal()).getTextContent();
                        description = allValues.item(TrumpCardField.DESCRIPTION.ordinal()).getTextContent();

                        // Create new trump card using the parameters found
                        trumpCard = new TrumpCard(fileName, imageName, cardTitle, description);

                        // Add the trump card to the deck
                        cardDeck.add(trumpCard);
                    }
                }
            }
        } catch (Exception e) {
            // If an error occurs, break out of the program with an error message.
            System.out.println("Error reading the XML File");
        }
    }

    // Return a deck object.
    public Deck toDeck() {
        return new Deck(cardDeck);
    }
}
