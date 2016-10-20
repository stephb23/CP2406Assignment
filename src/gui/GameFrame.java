package gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Stephanie on 19/10/2016.
 */
public class GameFrame extends JFrame {
    int numberOfPlayers;
    String playerName;

    Font titleFont = new Font("Monotype Corsiva", Font.PLAIN, 45);
    JLabel setUpTitle = new JLabel("Set Up a Game");
    JPanel setUpTitlePanel = new JPanel();

    GameSetupPanel gameSetupPanel = new GameSetupPanel();

    public GameFrame() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Set Up a Game");
        setResizable(false);
        setSize(500, 300);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

        setUpTitle.setFont(titleFont);

        add(gameSetupPanel);
        isVisible();
    }

    public String getPlayerName() {
        playerName = gameSetupPanel.getPlayerName();
        return playerName;
    }

    public int getNumberOfPlayers() {
        numberOfPlayers = gameSetupPanel.getNumberOfPlayers();
        return numberOfPlayers;
    }
}
