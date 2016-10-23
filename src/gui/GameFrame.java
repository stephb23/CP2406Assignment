package gui;

import cards.Card;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Stephanie on 19/10/2016.
 */
public class GameFrame extends JFrame {
    int numberOfPlayers;
    String playerName;

    Font titleFont = new Font("Monotype Corsiva", Font.PLAIN, 24);
    JLabel setUpTitle = new JLabel("Set Up a Game");
    JPanel setUpTitlePanel = new JPanel();

    GameSetupPanel gameSetupPanel = new GameSetupPanel();
    GamePanel gamePanel = null;

    Color background = new Color(28, 103, 116);

    boolean enabled;

    public GameFrame() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBackground(background);
        setTitle("Set Up a Game");
        setResizable(true);
        setSize(700, 300);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

        setUpTitle.setFont(titleFont);
        setUpTitle.setForeground(Color.WHITE);
        setUpTitlePanel.setBackground(background);
        setUpTitlePanel.add(setUpTitle);

        add(setUpTitlePanel);
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

    public void prepareGamePanel() {
        remove(gameSetupPanel);
        remove(setUpTitlePanel);

        gamePanel = new GamePanel();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(dim.width, dim.height);
        setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        setResizable(false);
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
