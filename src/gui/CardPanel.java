package gui;

import cards.Card;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * Created by Stephanie on 20/10/2016.
 */
public class CardPanel extends JPanel implements MouseListener {
    ImageIcon image = new ImageIcon();
    ImageIcon cardImage = new ImageIcon();
    ArrayList<JLabel> allCardImages = new ArrayList<>();
    GridLayout cardLayout = new GridLayout (1, 0);

    public CardPanel() {
        setLayout(cardLayout);
        setPreferredSize(new Dimension(800, 800));
        setVisible(true);
    }

    public void populate(ArrayList<Card> cards) {
        String locationString;
        for (Card card : cards) {
            locationString = "src\\gui\\images\\";
            locationString += card.getImageFile();
            System.out.println(locationString);
            image = new ImageIcon(locationString);
            Image scaledImage = image.getImage().getScaledInstance(300, 420 ,Image.SCALE_DEFAULT);
            cardImage = new ImageIcon(scaledImage);
            this.add(new JLabel(cardImage));
        }

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.add(new CardPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
