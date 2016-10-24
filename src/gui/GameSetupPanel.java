package gui;

import guiGameControl.GUIGameRunner;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Stephanie on 19/10/2016.
 *
 * This panel allows for setting up a game
 *
 */
public class GameSetupPanel extends JPanel {
    private GridLayout layout = new GridLayout(0, 2);

    private JPanel nameQueryPanel = new JPanel();
    private JLabel nameQuery = new JLabel("What is your name?");
    private JPanel nameFieldPanel = new JPanel();
    private JTextField nameField = new JTextField("Tony Stark");

    private JPanel playersQueryPanel = new JPanel();
    private JLabel playersQuery = new JLabel("Select number of opponents: ");
    private JPanel comboBoxPanel = new JPanel();
    private String[] playerNumberOptions = {"2", "3", "4"};
    private JComboBox comboBox = new JComboBox(playerNumberOptions);

    private JPanel nextPanel = new JPanel();
    private Button next = new Button("Next");

    private JPanel blankPanel = new JPanel();

    private Color background = new Color(28, 103, 116);
    private Font font = new Font("Arial", Font.PLAIN, 24);

    private int numberOfPlayers;
    private String playerName;

    public GameSetupPanel() {
        setLayout(layout);

        nameQueryPanel.setBackground(background);
        nameFieldPanel.setBackground(background);
        playersQueryPanel.setBackground(background);
        comboBoxPanel.setBackground(background);
        blankPanel.setBackground(background);
        nextPanel.setBackground(background);

        nameQuery.setFont(font);
        nameQuery.setForeground(Color.WHITE);
        nameField.setPreferredSize(new Dimension(200, 28));
        nameField.setFont(font);

        nameQueryPanel.add(nameQuery);
        nameFieldPanel.add(nameField);

        playersQuery.setFont(font);
        playersQuery.setForeground(Color.WHITE);
        playersQueryPanel.add(playersQuery);

        comboBox.setFont(font);
        comboBox.setPreferredSize(new Dimension(200, 28));
        comboBoxPanel.add(comboBox);

        next.setFont(font);
        next.setPreferredSize(new Dimension(150, 28));
        nextPanel.add(next);

        add(nameQueryPanel);
        add(nameFieldPanel);
        add(playersQueryPanel);
        add(comboBoxPanel);
        add(blankPanel);
        add(nextPanel);

        next.addActionListener(GUIGameRunner.gameSetupListener);
    }

    public String getPlayerName() {
        playerName = nameField.getText();
        return playerName;
    }

    public int getNumberOfPlayers(){
        numberOfPlayers = Integer.parseInt(comboBox.getSelectedItem().toString());
        return numberOfPlayers;
    }

}
