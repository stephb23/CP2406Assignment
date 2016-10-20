package gui;

import cards.Card;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by Stephanie on 20/10/2016.
 */
public class GamePanel extends JPanel {
    CardPanel cardPanel = new CardPanel();

    public GamePanel () {
        add(cardPanel);
    }

    public void drawCardPanel(ArrayList<Card> cards){
        cardPanel.populate(cards);
        validate();
        repaint();
    }
}
