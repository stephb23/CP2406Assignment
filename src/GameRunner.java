
import cards.Card;
import cards.TrumpCard;
import players.AIPlayer;
import players.HumanPlayer;
import players.Player;

import java.util.ArrayList;

/**
 * Created by Stephanie on 6/09/2016.
 */
public class GameRunner {
    private static String playerName;
    private static ArrayList<Player> allPlayers = new ArrayList<>();
    private static MessageDisplayer messageDisplayer = new MessageDisplayer();
    private static InputReader inputReader = new InputReader();
    private static int numberOfAIPlayers, numberOfPlayers;
    private static SupertrumpGame game;

    public static void main(String[] args) {

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
        createPlayers(numberOfPlayers);
        System.out.println("Players in this game are: ");
        for (Player player : allPlayers) {
            System.out.println(player.getName());
        }

        // Create the deck and deal cards
        game.createDeck();
        for (Player player : allPlayers) {
            player.setPlayerHand(game.dealHand());
        }

        // Choose first player
        game.selectDealer(numberOfPlayers);
        System.out.println(allPlayers.get(game.getCurrentPlayer()).getName() + " will start the game!\n");

        // Play round 1
        while (!game.isFinished()){
            System.out.println("\n\nNEW ROUND");
            playRound(game.getCurrentPlayer());
            for (int i = 0; i < allPlayers.size(); ++i) {
                allPlayers.get(i).activate();
            }
            game.setRoundFinished(false);
        }

        System.out.println("The winning player is " + allPlayers.get(game.getCurrentPlayer()).getName());

        // The following code will only run if the player chooses to play a game while menuHandler() is running
        System.out.println("YAY PLAY A GAME");

    }

    public static void playRound(int startingPlayer) {
        Card tempCard;
        if (startingPlayer == 0) {
            String passOrPlay;

            System.out.println("You go first, " + playerName);
            HumanPlayer player = (HumanPlayer) allPlayers.get(0);
            player.viewAllCards();
            System.out.print("Do you wish to 'pass' or 'play'? ");
            passOrPlay = inputReader.passOrPlay();
            if (passOrPlay.equals("pass")) {
                player.pass();
                player.pickUpCard(game.dealSingleCard());
                System.out.println("You chose to pass.");
            } else {
                System.out.print("Enter the number of the card you wish to play: ");
                game.setCurrentCard(player.playCard(inputReader.getCardNumber(player.getHandSize())));

                if (game.getCurrentCard().getType().equals("trump")) {
                    TrumpCard trumpCard = (TrumpCard) game.getCurrentCard();
                    if (trumpCard.getCardDescription().equals("Change to trumps category of your choice")) {
                        System.out.print("Choose a trump category: ");
                        game.setCurrentCategory(inputReader.getCategory());
                    } else if (trumpCard.getCardDescription().equals("Crustal abundance")) {
                        game.setCurrentCategory("crustal abundances");
                    } else {
                        game.setCurrentCategory(trumpCard.getCardDescription());
                    }

                    System.out.print("You played a trump card! Now choose another card to play: ");
                    game.setCurrentCard(player.playCard(inputReader.getCardNumber(player.getHandSize())));
                } else {
                    System.out.print("Choose a trump category: ");
                    game.setCurrentCategory(inputReader.getCategory());
                }
            }

            game.nextTurn();

        } else {
            tempCard = null;
            AIPlayer player = (AIPlayer) allPlayers.get(startingPlayer);
            System.out.println(player.getName() + " goes first");
            game.setCurrentCategory(player.chooseCategory());
            tempCard = player.playFirstCard(game.getCurrentCategory());
            if (tempCard != null) {
                game.setCurrentCard(tempCard);
            } else {
                System.out.println(player.getName() + " has passed.");
            }

            game.nextTurn();
            System.out.println(game.getCurrentCard().toString());
        }

        while (!game.isFinished() && !game.isRoundFinished()) {
            Player player = allPlayers.get(game.getCurrentPlayer());
            if (!player.getPassed()) {
                if (game.getCurrentPlayer() == 0) {
                    // human stuff
                    game.nextTurn();
                } else {
                    AIPlayer aiPlayer = (AIPlayer) player;
                    System.out.println(player.getName() + " chose: ");
                    if (game.getCurrentCard().getType().equals("trump")) {
                        tempCard = aiPlayer.playFirstCard(game.getCurrentCategory());
                    } else {
                        tempCard = aiPlayer.playCard(game.getCurrentCard(), game.getCurrentCategory());
                    }

                    if (tempCard != null) {
                        game.setCurrentCard(tempCard);
                    } else {
                        aiPlayer.pass();
                        aiPlayer.pickUpCard(game.dealSingleCard());
                        System.out.println(aiPlayer.getName() + " passed");
                    }

                    if (game.getCurrentCard().getType().equals("trump")) {
                        for (int i = 0; i < allPlayers.size(); ++i) {
                            allPlayers.get(i).activate();
                        }

                        TrumpCard trumpCard = (TrumpCard) game.getCurrentCard();
                        if (trumpCard.getCardDescription().equals("Change to trumps category of your choice")) {
                            game.setCurrentCategory(aiPlayer.chooseCategory());
                        } else if (trumpCard.getCardDescription().equals("Crustal abundance")) {
                            game.setCurrentCategory("crustal abundances");
                        } else {
                            game.setCurrentCategory(trumpCard.getCardDescription().toLowerCase());
                        }
                    } else {
                        game.nextTurn();
                    }

                    System.out.println(game.getCurrentCard().toString());

                    if (aiPlayer.getHandSize() == 0) {
                        game.setFinished(true);
                    }
                }
            }

            int playersPassed = 0;

            for (int i = 0; i < allPlayers.size(); ++i) {
                if (allPlayers.get(i).getPassed()) {
                    ++playersPassed;
                }
            }

            if (playersPassed == numberOfPlayers - 1) {
                for (int i = 1; i < allPlayers.size(); ++i) {
                    if (!allPlayers.get(i).getPassed()){
                        System.out.println(allPlayers.get(i).getName() + " wins this round!");
                        game.setCurrentPlayer(i);
                    }
                }

                game.setRoundFinished(true);
            }
        }
    }

    public static void menuHandler() {
        int menuOption;
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

    public static void createPlayers(int numberOfPlayers) {
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
