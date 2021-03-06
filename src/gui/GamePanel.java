package gui;

import cards.Card;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Stephanie on 20/10/2016.
 *
 * This is the panel for gameplay
 *
 */
public class GamePanel extends JPanel {
    private CardPanel cardPanel = new CardPanel();
    private CurrentCardPanel currentCardPanel = new CurrentCardPanel();
    private DeckPanel deckPanel = new DeckPanel();
    private GridBagLayout layout = new GridBagLayout();
    private GridBagConstraints constraints = new GridBagConstraints();
    private JPanel messagePanel = new JPanel();
    private JLabel message = new JLabel("Starting Game!");

    AIPlayerPanel[] aiPlayers = new AIPlayerPanel[4];

    Color background = new Color(28, 103, 116);
    Font font = new Font("Arial", Font.PLAIN, 24);


    public GamePanel () {
        setLayout(layout);
        setBackground(background);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

        setPreferredSize(new Dimension(dim.width, dim.height));

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.PAGE_START;
        constraints.ipady = 50;
        constraints.weightx = 0;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 4;
        message.setFont(font);
        message.setForeground(Color.WHITE);
        messagePanel.add(message);
        messagePanel.setBackground(background);
        add(messagePanel, constraints);

        // JPanel setup
        aiPlayers[0] = new AIPlayerPanel(0, "Jarvis");
        aiPlayers[1] = new AIPlayerPanel(1, "Plato");
        aiPlayers[2] = new AIPlayerPanel(0, "Virgil");
        aiPlayers[3] = new AIPlayerPanel(1, "Jocasta");
        for (int i = 0; i < aiPlayers.length; i++) {
            aiPlayers[i].setBackground(background);
        }

        // Second row
        constraints.ipady = 100;
        constraints.gridwidth = 1;
        constraints.weightx = 0.36;
        constraints.gridx = 0;
        constraints.gridy = 1;
        add(aiPlayers[0], constraints);
        constraints.gridheight = 2;
        constraints.ipady = 200;
        constraints.gridx = 1;
        constraints.anchor = GridBagConstraints.LINE_END;
        deckPanel.setBackground(background);
        add(deckPanel, constraints);
        constraints.gridx = 2;
        currentCardPanel.setBackground(background);
        add(currentCardPanel, constraints);
        constraints.gridheight = 1;
        constraints.anchor = GridBagConstraints.PAGE_START;
        constraints.gridx = 3;
        add(aiPlayers[1], constraints);

        // Third Row
        constraints.ipady = 100;
        constraints.weightx = 0.5;
        constraints.gridx = 0;
        constraints.gridy = 2;
        add(aiPlayers[2], constraints);
        constraints.gridx = 3;
        add(aiPlayers[3], constraints);

        // Final row
        constraints.ipady = 50;
        constraints.weightx = 0;
        constraints.gridwidth = 4;
        constraints.gridx = 0;
        constraints.gridy = 3;
        cardPanel.setBackground(background);
        add(cardPanel, constraints);

    }

    public void drawCardPanel(ArrayList<Card> cards){
        cardPanel.populate(cards);
    }

    public void updateCurrentCard(Card currentCard) {
        currentCardPanel.update(currentCard);
    }

    public void clearDeckPanel() {
        deckPanel.clear();
    }

    public void drawAIPlayers(int numberOfAIPlayers) {
        if (numberOfAIPlayers == 2) {
            aiPlayers[2].setNotInGame();
            aiPlayers[2].setPlayerName("");
            aiPlayers[3].setNotInGame();
            aiPlayers[3].setPlayerName("");
        } else if (numberOfAIPlayers == 3) {
            aiPlayers[3].setNotInGame();
            aiPlayers[3].setPlayerName("");
        }

        repaint();
    }

    public void drawOutAIPlayer(int player, String string) {
        aiPlayers[player-1].setInactive(string);
    }

    public void drawActiveAIPlayer(int player) {
        aiPlayers[player-1].setPlaying();
        repaint();
    }

    public void drawInactiveAIPlayer(int player) {
        if (player == 0) {
            return;
        }

        aiPlayers[player-1].setNotPlaying();
        repaint();
    }

    public void updateMessage(String string) {
        message.setText(string);
        repaint();
    }
}
