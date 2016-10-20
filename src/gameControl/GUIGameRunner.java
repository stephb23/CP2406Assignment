package gameControl;

import game.SupertrumpGame;
import gui.*;
import players.AIPlayer;
import players.HumanPlayer;
import players.Player;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Stephanie on 20/10/2016.
 */
public class GUIGameRunner {
    private static String playerName;
    private static ArrayList<Player> allPlayers = new ArrayList<>();
    private static int numberOfAIPlayers;
    private static int numberOfPlayers;
    private static SupertrumpGame game;
    private static MenuFrame menuFrame;
    private static InstructionsFrame instructionsFrame;
    private static AboutGameFrame aboutGameFrame;
    private static GameFrame gameFrame;

    public static void main(String[] args) {
        menuFrame = new MenuFrame();
        menuFrame.setVisible(true);
    }

    public static ActionListener startGameListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            gameFrame = new GameFrame();
            gameFrame.setVisible(true);
            menuFrame.setVisible(false);
        }
    };

    public static ActionListener instructionsListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            instructionsFrame = new InstructionsFrame();
            instructionsFrame.setVisible(true);
            menuFrame.setVisible(false);
        }
    };

    public static ActionListener aboutGameListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            aboutGameFrame = new AboutGameFrame();
            aboutGameFrame.setVisible(true);
            menuFrame.setVisible(false);
        }
    };

    public static ActionListener backToMenuListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            menuFrame.setVisible(true);
            if (instructionsFrame.isVisible()) {
                instructionsFrame.dispose();
            } else if (aboutGameFrame.isVisible()) {
                aboutGameFrame.dispose();
            }
        }
    };

    public static ActionListener gameSetupListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            playerName = gameFrame.getPlayerName();
            numberOfAIPlayers = gameFrame.getNumberOfPlayers();
            numberOfPlayers = numberOfAIPlayers + 1;

            System.out.println("Name is " + playerName);
            System.out.println("Number of players is " + numberOfPlayers);

            game = new SupertrumpGame(numberOfAIPlayers);

            // Create the players
            createPlayers(numberOfPlayers);
            System.out.println("\nPlayers in this game are: ");
            for (Player player : allPlayers) {
                System.out.println(player.getName());
            }

            // Create the deck
            game.createDeck();

            // Confirm that the deck has been created.
            System.out.println("\nDealing cards...");

            // Deal cards and show size of each player's hand
            for (Player player : allPlayers) {
                player.setPlayerHand(game.dealHand());
            }

            // Choose first player
            game.selectDealer(numberOfPlayers);
            System.out.println("\n"+allPlayers.get(game.getCurrentPlayer()).getName() + " will start the game!\n");

            gameFrame.prepareGamePanel();
            HumanPlayer player = (HumanPlayer) allPlayers.get(0);
            gameFrame.drawPlayerHand(player.getAllCards());
        }
    };

    // Create the human and AI players for the game
    // Naturally all AIs are from Iron Man comics or movies
    private static void createPlayers(int numberOfPlayers) {
        allPlayers.add(new HumanPlayer(playerName));
        allPlayers.add(new AIPlayer("AI Jarvis"));
        if (numberOfPlayers >= 3) {
            allPlayers.add(new AIPlayer("AI Plato"));
        }

        if (numberOfPlayers >= 4) {
            allPlayers.add(new AIPlayer("AI Virgil"));
        }

        if (numberOfPlayers == 5) {
            allPlayers.add(new AIPlayer("AI Jocasta"));
        }
    }
}
