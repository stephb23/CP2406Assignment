package gui;

import guiGameControl.GUIGameRunner;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Stephanie on 22/10/2016.
 *
 * This dialog indicates the end of a round
 *
 */

public class EndOfRoundDialog extends JDialog {
    private JLabel roundWinner = new JLabel();
    private String winMessage;
    private Button button = new Button("Next Round");
    private GridLayout layout = new GridLayout(0, 1);

    private JPanel roundWinnerPanel = new JPanel();
    private JPanel buttonPanel = new JPanel();

    private Color background = new Color(28, 103, 116);

    public EndOfRoundDialog(String playerName) {
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setTitle("Round over!");
        setLayout(layout);
        setSize(new Dimension(500, 150));
        setAlwaysOnTop(true);
        setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

        winMessage = playerName + " is the winner of this round!";
        roundWinner.setText(winMessage);
        roundWinner.setFont(new Font("Arial", Font.PLAIN, 16));
        roundWinner.setForeground(Color.WHITE);
        roundWinnerPanel.setBackground(background);
        roundWinnerPanel.add(roundWinner);

        buttonPanel.setBackground(background);
        buttonPanel.add(button);

        add(roundWinnerPanel);
        add(buttonPanel);

        button.addActionListener(GUIGameRunner.newRoundListener);
    }
}
