import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

/**
 * Created by Stephanie on 5/09/2016.
 */
public class XMLDeckBuilder implements DeckBuilder{

    public static void main(String[] args) {
        try {
            File xmlFile = new File("MstCards_151021.plist");
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(xmlFile);
            document.getDocumentElement().normalize();
            System.out.println("Root element: " + document.getDocumentElement().getNodeName());
            NodeList nodeList = document.getElementsByTagName("key");
            System.out.println(nodeList.item(0).getTextContent());

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
