package gui;

import gameControl.GUIGameRunner;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Stephanie on 22/10/2016.
 */
public class EndOfRoundDialog extends JDialog {
    JLabel roundWinner = new JLabel();
    String winMessage;
    Button button = new Button("Next Round");
    GridLayout layout = new GridLayout();

    JPanel roundWinnerPanel = new JPanel();
    JPanel buttonPanel = new JPanel();

    public EndOfRoundDialog() {
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setPreferredSize(new Dimension(300, 300));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

        roundWinnerPanel.add(roundWinner);
        buttonPanel.add(button);

        add(roundWinnerPanel);
        add(buttonPanel);

        button.addActionListener(GUIGameRunner.newRoundListener);
    }

    public void setRoundOverMessage(String playerName) {
        winMessage = playerName + " is the winner of this round!";
        roundWinner.setText(winMessage);
        revalidate();
        repaint();
    }
}
