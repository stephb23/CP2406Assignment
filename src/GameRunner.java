
import players.AIPlayer;
import players.HumanPlayer;
import players.Player;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Stephanie on 6/09/2016.
 */
public class GameRunner {
    private static String playerName;

    public static void main(String[] args) {
        MessageDisplayer messageDisplayer = new MessageDisplayer();
        InputReader inputReader = new InputReader();
        int numberOfAIPlayers, numberOfPlayers;
        SupertrumpGame game;
        ArrayList<Player> allPlayers;

        // Welcome the player & display menu
        messageDisplayer.displayWelcome();
        playerName = inputReader.getUserName();
        messageDisplayer.displayNameConfirmation(playerName);
        menuHandler();

        // Start the game
        messageDisplayer.startGame();
        numberOfAIPlayers = inputReader.getInt(1, 4);
        numberOfPlayers = numberOfAIPlayers + 1;
        game = new SupertrumpGame(numberOfAIPlayers);

        // Create the players
        allPlayers = createPlayers(numberOfPlayers);
        System.out.println("Players in this game are: ");
        for (Player player : allPlayers) {
            System.out.println(player.toString());
        }

        // Create the deck and deal cards
        game.createDeck();
        for (Player player : allPlayers) {
            player.setHand(game.dealHand());
        }

        // Choose first player
        int startingPlayer = game.selectDealer(numberOfPlayers);
        System.out.println(allPlayers.get(startingPlayer).getName() + " will start the game!\n");

        // Begin round 1
        if (startingPlayer == 0) {
            System.out.println("You go first, " + playerName);
            HumanPlayer player = (HumanPlayer) allPlayers.get(0);
            player.viewAllCards();
        }

        // The following code will only run if the player chooses to play a game while menuHandler() is running
        System.out.println("YAY PLAY A GAME");

    }

    public static void playGame() {


    }

    public static void menuHandler() {
        int menuOption;
        MessageDisplayer messageDisplayer = new MessageDisplayer();
        InputReader inputReader = new InputReader();
        messageDisplayer.displayMenu();
        menuOption = inputReader.getMenuChoice();

        if (menuOption == 1) {
            return;
        } else if (menuOption == 2) {
            messageDisplayer.displayInstructions();
        } else if (menuOption == 3) {
            messageDisplayer.displayAboutGame();
        }

        if (menuOption == 2 || menuOption == 3) {
            System.out.println("Type 'r' to return to the menu or 'e' to exit.");
            char choice = inputReader.getChar();
            while (choice != 'r' && choice != 'e') {
                System.out.println("Invalid input! Type 'r' to return to menu or 'e' to exit: ");
                choice = inputReader.getChar();
            }
            if (choice == 'r') {
                menuHandler();
            } else if (choice == 'e') {
                System.out.println("Goodbye! See you next time!");
                System.exit(0);
            }
        }
    }

    public static ArrayList<Player> createPlayers(int numberOfPlayers) {
        ArrayList<Player> allPlayers = new ArrayList<>();
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
        return allPlayers;
    }

}
