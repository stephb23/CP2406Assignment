package gui;

import guiGameControl.GUIGameRunner;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Stephanie on 23/10/2016.
 *
 * This dialog appears when a game has finished, and lists the winners of the game
 *
 */
public class GameOverDialog extends JDialog {
    private JLabel title = new JLabel("Game over!");
    private JPanel titlePanel = new JPanel();
    private JPanel[] allWinners;
    private GridLayout layout = new GridLayout(0, 1);
    private Button newGameButton = new Button("Back to Menu");
    private JPanel newGamePanel = new JPanel();
    private Button exitButton = new Button("Exit");
    private JPanel exitPanel = new JPanel();

    private Color background = new Color(28, 103, 116);

    public GameOverDialog(String[] winners) {
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setTitle("Game Over");
        setLayout(layout);
        setAlwaysOnTop(true);
        setSize(300, 300);
        setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

        allWinners = new JPanel[winners.length-1];

        title.setFont(new Font("Monotype Corsiva", Font.PLAIN, 24));
        title.setForeground(Color.WHITE);
        titlePanel.setBackground(background);
        titlePanel.add(title);
        add(titlePanel);

        System.out.println(allWinners.length);

        for (int i = 0; i < allWinners.length; ++i) {
            JLabel label = new JLabel((i + 1) + ". " + winners[i]);
            label.setFont(new Font("Arial", Font.PLAIN, 18));
            label.setForeground(Color.WHITE);
            allWinners[i] = new JPanel();
            allWinners[i].add(label);
            allWinners[i].setBackground(background);
            add(allWinners[i]);
        }

        newGameButton.setPreferredSize(new Dimension(100, 28));
        newGamePanel.setBackground(background);
        newGamePanel.add(newGameButton);
        add(newGamePanel);
        newGameButton.addActionListener(GUIGameRunner.quitListener);

        exitButton.setPreferredSize(new Dimension(100, 28));
        exitPanel.setBackground(background);
        exitPanel.add(exitButton);
        add(exitPanel);
        exitButton.addActionListener(GUIGameRunner.exitListener);

        setVisible(true);
    }
}
