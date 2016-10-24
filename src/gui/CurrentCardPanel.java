package gui;

import cards.Card;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Stephanie on 21/10/2016.
 *
 * This is the panel that shows the current card to beat
 *
 */
public class CurrentCardPanel extends JPanel {
    private ImageIcon image = new ImageIcon();
    private Image scaledImage;
    private ImageIcon cardImage = new ImageIcon();
    private JLabel label = new JLabel("");
    private String locationString;

    public CurrentCardPanel() {
        image = new ImageIcon("src\\gui\\images\\Slide66.jpg");
        scaledImage = image.getImage().getScaledInstance(240, 300 ,Image.SCALE_DEFAULT);
        cardImage = new ImageIcon(scaledImage);
        setPreferredSize(new Dimension(240, 300));
        label = new JLabel(cardImage);
        add(label);
    }

    public void update(Card currentCard) {
        locationString = "src\\gui\\images\\";
        locationString += currentCard.getImageFile();
        System.out.println(locationString);
        image = new ImageIcon(locationString);
        scaledImage = image.getImage().getScaledInstance(240, 300 ,Image.SCALE_DEFAULT);
        cardImage = new ImageIcon(scaledImage);
        remove(label);
        label = new JLabel(cardImage);
        add(label);
        repaint();
    }
}

