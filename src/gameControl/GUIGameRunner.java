package gameControl;

import game.SupertrumpGame;
import gui.*;
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
        }
    };
}
