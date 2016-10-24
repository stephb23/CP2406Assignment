package gui;

import cards.Card;
import guiGameControl.GUIGameRunner;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Stephanie on 20/10/2016.
 *
 * This panel shows the human player's hand
 *
 */
public class CardPanel extends JPanel {
    private ImageIcon image = new ImageIcon();
    private Image scaledImage;
    private ImageIcon cardImage = new ImageIcon();
    private ArrayList<JLabel> allCardImages = new ArrayList<>();
    private GridLayout cardLayout = new GridLayout (1, 0);

    public CardPanel() {
        setLayout(cardLayout);
        setPreferredSize(new Dimension(1000, 400));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setPreferredSize(new Dimension(dim.width - 200, 300));
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
