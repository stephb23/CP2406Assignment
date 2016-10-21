package gui;

import cards.Card;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by Stephanie on 20/10/2016.
 */
public class GamePanel extends JPanel {
    CardPanel cardPanel = new CardPanel();
    CurrentCardPanel currentCardPanel = new CurrentCardPanel();
    PassQuitPanel passQuitPanel = new PassQuitPanel();

    public GamePanel () {
        add(currentCardPanel);
        add(cardPanel);
        add(passQuitPanel);
    }

    public void drawCardPanel(ArrayList<Card> cards){
        cardPanel.populate(cards);
    }

    public void updateCurrentCard(Card currentCard) {
        currentCardPanel.update(currentCard);
    }
}
