package gui;

import cards.Card;
import gameControl.GUIGameRunner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * Created by Stephanie on 20/10/2016.
 */
public class CardPanel extends JPanel {
    ImageIcon image = new ImageIcon();
    Image scaledImage;
    ImageIcon cardImage = new ImageIcon();
    ArrayList<JLabel> allCardImages = new ArrayList<>();
    GridLayout cardLayout = new GridLayout (1, 0);

    public CardPanel() {
        setLayout(cardLayout);
        setPreferredSize(new Dimension(1000, 400));
    }

    public void populate(ArrayList<Card> cards) {
        removeAll();
        String locationString;
        for (Card card : cards) {
            locationString = "src\\gui\\images\\";
            locationString += card.getImageFile();
            System.out.println(locationString);
            image = new ImageIcon(locationString);
            scaledImage = image.getImage().getScaledInstance(240, 300 ,Image.SCALE_DEFAULT);
            cardImage = new ImageIcon(scaledImage);
            JLabel label = new JLabel(cardImage);
            label.setName(card.getCardName());
            label.addMouseListener(GUIGameRunner.cardListener);
            allCardImages.add(label);
            add(label);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.add(new CardPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setVisible(true);
    }


}
