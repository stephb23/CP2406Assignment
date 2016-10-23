package gui;

import gameControl.GUIGameRunner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Stephanie on 19/10/2016.
 */
public class GameSetupPanel extends JPanel {
    GridLayout layout = new GridLayout(0, 2);

    JPanel nameQueryPanel = new JPanel();
    JLabel nameQuery = new JLabel("What is your name?");
    JPanel nameFieldPanel = new JPanel();
    JTextField nameField = new JTextField("Tony Stark");

    JPanel playersQueryPanel = new JPanel();
    JLabel playersQuery = new JLabel("Select the number of players: ");
    JPanel comboBoxPanel = new JPanel();
    String[] playerNumberOptions = {"2", "3", "4"};
    JComboBox comboBox = new JComboBox(playerNumberOptions);

    JPanel nextPanel = new JPanel();
    Button next = new Button("Next");

    JPanel blankPanel = new JPanel();

    Color background = new Color(28, 103, 116);
    Font font = new Font("Arial", Font.PLAIN, 24);

    int numberOfPlayers;
    String playerName;

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
