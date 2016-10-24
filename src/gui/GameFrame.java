package gui;

import cards.Card;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Stephanie on 19/10/2016.
 *
 * This frame holds the game set up and game play panels, and performs updates where required
 *
 */
public class GameFrame extends JFrame {
    private int numberOfPlayers;
    private String playerName;

    private Font titleFont = new Font("Monotype Corsiva", Font.PLAIN, 48);
    private JLabel setUpTitle = new JLabel("Set Up a Game");
    private JPanel setUpTitlePanel = new JPanel();

    private GameSetupPanel gameSetupPanel = new GameSetupPanel();
    private GamePanel gamePanel = null;

    private JPanel setUpPanel;

    private Color background = new Color(28, 103, 116);

    private boolean enabled;

    public GameFrame() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Set Up a Game");
        setResizable(false);
        setSize(700, 300);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

        setUpTitle.setFont(titleFont);
        setUpTitle.setForeground(Color.WHITE);
        setUpTitlePanel.setBackground(background);
        setUpTitlePanel.add(setUpTitle);

        setUpPanel = new JPanel();
        setUpPanel.setLayout(new FlowLayout());
        setUpPanel.setBackground(background);
        setUpPanel.add(setUpTitlePanel);
        setUpPanel.add(gameSetupPanel);

        add(setUpPanel);
        setVisible(true);
    }

    public String getPlayerName() {
        playerName = gameSetupPanel.getPlayerName();
        return playerName;
    }

    public int getNumberOfPlayers() {
        numberOfPlayers = gameSetupPanel.getNumberOfPlayers();
        return numberOfPlayers;
    }

    public void prepareGamePanel() {
        remove(setUpPanel);

        setTitle("GAME TIME!");
        gamePanel = new GamePanel();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(dim.width, dim.height);
        setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        add(gamePanel);
        setVisible(true);
    }


    public void drawPlayerHand(ArrayList<Card> playerHand) {
        gamePanel.drawCardPanel(playerHand);
        validate();
        repaint();
    }

    public void drawAIPlayers(int numberOfAIPlayers) {
        gamePanel.drawAIPlayers(numberOfAIPlayers);
    }

    public void drawActiveAIPlayer(int player) {
        gamePanel.drawActiveAIPlayer(player);
    }

    public void drawInactiveAIPlayer(int player) {
        gamePanel.drawInactiveAIPlayer(player);
    }

    public void drawOutAIPlayer(int player, String string) {
       gamePanel.drawOutAIPlayer(player, string);
    }

    public void updateCurrentCard(Card currentCard) {
        gamePanel.updateCurrentCard(currentCard);
        super.revalidate();
        super.repaint();
        revalidate();
        repaint();
    }

    public void updateMessage(String string) {
        gamePanel.updateMessage(string);
    }

    public void clearDeckPanel() {
        gamePanel.clearDeckPanel();
    }

    public void disablePanel() {
        enabled = false;
    }

    public void enablePanel() {
        enabled = true;
    }

    public boolean isPanelEnabled() {
        return enabled;
    }
}
