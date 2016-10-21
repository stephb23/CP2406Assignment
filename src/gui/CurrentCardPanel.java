package gui;

import cards.Card;
import gameControl.GUIGameRunner;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Stephanie on 21/10/2016.
 */
public class CurrentCardPanel extends JPanel {
    ImageIcon image = new ImageIcon();
    Image scaledImage;
    ImageIcon cardImage = new ImageIcon();
    JLabel label = new JLabel("");
    String locationString;

    public CurrentCardPanel() {
        setPreferredSize(new Dimension(250, 300));
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
    }
}
