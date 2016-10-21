package gui;

import cards.Card;
import gameControl.GUIGameRunner;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Stephanie on 22/10/2016.
 */
public class DeckPanel extends JPanel {
    ImageIcon image = new ImageIcon();
    Image scaledImage;
    ImageIcon cardImage = new ImageIcon();
    JLabel label = new JLabel("");
    String locationString;

    public DeckPanel() {
        setPreferredSize(new Dimension(250, 300));
        add(label);
        locationString = "src\\gui\\images\\Slide65.jpg";
        image = new ImageIcon(locationString);
        scaledImage = image.getImage().getScaledInstance(240, 300 ,Image.SCALE_DEFAULT);
        cardImage = new ImageIcon(scaledImage);
        label = new JLabel(cardImage);
        add(label);
        this.addMouseListener(GUIGameRunner.passListener);
    }

    public void clear() {
        remove(label);
    }
}
