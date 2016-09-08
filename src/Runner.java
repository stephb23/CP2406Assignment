import jdk.internal.util.xml.impl.Input;
import sun.plugin2.message.Message;

import java.util.Scanner;

/**
 * Created by Stephanie on 6/09/2016.
 */
public class Runner {
    private static String playerName;

    public static void main(String[] args) {
        MessageDisplayer messageDisplayer = new MessageDisplayer();
        InputReader inputReader = new InputReader();

        // Welcome the player & display menu
        messageDisplayer.displayWelcome();
        playerName = inputReader.getUserName();
        messageDisplayer.displayNameConfirmation(playerName);
        menuHandler();
        playGame();

        // The following code will only run if the player chooses to play a game while menuHandler() is running
        System.out.println("YAY PLAY A GAME");

    }

    public static void playGame() {
        int numberOfAIPlayers;
        SupertrumpGame game;
        MessageDisplayer messageDisplayer = new MessageDisplayer();
        InputReader inputReader = new InputReader();

        messageDisplayer.startGame();
        numberOfAIPlayers = inputReader.getInt(1, 4);
        game = new SupertrumpGame(numberOfAIPlayers);

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

}
