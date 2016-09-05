import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.File;

/**
 * Created by Stephanie on 5/09/2016.
 */
public class XMLDeckBuilder implements DeckBuilder{

    public static void main(String[] args) {
        try {
            // Read our XML file into a Java Document
            File xmlFile = new File("MstCards_151021.plist");
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(xmlFile);

            // These are the nodes you're looking for *jedi wave*
            // Item 0 in this list is a write-off; it's the whole bloody file.
            // Last three items are game info cards, not playing cards. IGNORE.
            NodeList nodeList = document.getElementsByTagName("dict");
            for (int i = 1; i < nodeList.getLength() - 3; ++i) {
                Node node = nodeList.item(i);
                System.out.println("Node: " + node.getTextContent());
                if (node.getNodeType()== Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    System.out.println(element.getElementsByTagName("string").item(2).getTextContent());
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
