package gui;

import guiGameControl.GUIGameRunner;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Stephanie on 22/10/2016.
 *
 * This panel shows the deck
 *
 */
public class DeckPanel extends JPanel {
    private ImageIcon image = new ImageIcon();
    private Image scaledImage;
    private ImageIcon cardImage = new ImageIcon();
    private JLabel label = new JLabel("");
    private String locationString;

    public DeckPanel() {
        setPreferredSize(new Dimension(240, 300));
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
