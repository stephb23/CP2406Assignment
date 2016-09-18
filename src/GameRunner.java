
import cards.Card;
import cards.PlayCard;
import cards.TrumpCard;
import players.AIPlayer;
import players.HumanPlayer;
import players.Player;

import java.util.ArrayList;

import static java.lang.System.exit;

/**
 * Created by Stephanie on 6/09/2016.
 *
 * This class is the center of game logic, setting up a game, handling the first player's turn, and running
 * the rest of each round. It determines when a player is finished, when a round is finished, and finally when the
 * entire game is finished. At this point, it displays the winner/s in order.
 *
 */

public class GameRunner {
    // These variables are required by all functions in this file.
    private static String playerName;
    private static ArrayList<Player> allPlayers = new ArrayList<>();
    private static MessageDisplayer messageDisplayer = new MessageDisplayer();
    private static InputReader inputReader = new InputReader();
    private static int numberOfPlayers;
    private static SupertrumpGame game;

    /* The 'main' method primarily deals with game set up, and handles checking whether a game has finished or not after
    a round has been completed.
    */
    public static void main(String[] args) {

        // Welcome the player & display menu
        messageDisplayer.displayWelcome();
        playerName = inputReader.getUserName();
        messageDisplayer.displayNameConfirmation(playerName);

        while (menuHandler() != 4) {
            // Start the game
            messageDisplayer.startGame();
            int numberOfAIPlayers = inputReader.getInt(1, 4);
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

            // Test the Geophysicist + Magnetite combination for the human player
            //allPlayers.get(0).pickUpCard(new TrumpCard("The Geophysicist", "Specific gravity"));
            //allPlayers.get(0).pickUpCard(new PlayCard("Magnetite"));

            // Test the Geophysicist + Magnetite combination for the first AI player
            //allPlayers.get(1).pickUpCard(new TrumpCard("The Geophysicist", "Specific gravity"));
            //allPlayers.get(1).pickUpCard(new PlayCard("Magnetite"));

            // Choose first player
            game.selectDealer(numberOfPlayers);
            System.out.println(allPlayers.get(game.getCurrentPlayer()).getName() + " will start the game!\n");

            // While the game isn't finished, play rounds.
            while (!game.isFinished()) {
                System.out.println("\n\nNEW ROUND");
                playRound(game.getCurrentPlayer());

                // After a round has finished, reactivate all players who had passed
                for (int i = 0; i < allPlayers.size(); ++i) {
                    allPlayers.get(i).activate();
                }

                // Reset round finished variable.
                game.setRoundFinished(false);
            }

            // When the game is finished, print out the order players finished in (not including the loser).
            System.out.println("The winners are: ");
            for (int i = 0; i < allPlayers.size() - 1; ++i) {
                System.out.println((i+1) + ". " + allPlayers.get(game.getWinner(i)).getName());
            }

            // Clear allPlayers in case a new game is started.
            allPlayers.clear();
        }
    }

    // The playRound method handles the game logic for each round.
    private static void playRound(int startingPlayer) {
        Card tempCard;

        // Human player is found at index 0.
        if (startingPlayer == 0 && !allPlayers.get(0).isFinished()) {

            // Indicate to the player that it's their turn and show them their cards.
            System.out.println("You go first, " + playerName);
            HumanPlayer player = (HumanPlayer) allPlayers.get(0);
            player.viewAllCards();

            // Ask for a card number
            System.out.print("Enter the number of the card you wish to play: ");
            game.setCurrentCard(player.playCard(inputReader.getCardNumber(player.getHandSize())));

            System.out.println(game.getCurrentCard().getType());
            if (game.getCurrentCard().getType().equals("trump")) {

                System.out.println("Entering here");

                while (game.getCurrentCard().getType().equals("trump")) {
                    System.out.println("HI HERE I AM");
                    boolean checkForWinningCombo = false;

                    TrumpCard trumpCard = (TrumpCard) game.getCurrentCard();
                    if (trumpCard.getCardDescription().equals("Change to trumps category of your choice")) {
                        System.out.print("Choose a trump category: ");
                        game.setCurrentCategory(inputReader.getCategory());
                    } else if (trumpCard.getCardDescription().equals("Crustal abundance")) {
                        game.setCurrentCategory("crustal abundances");
                    } else if (trumpCard.getCardName().equals("The Geophysicist")){
                        System.out.println("the card was played");
                        checkForWinningCombo = true;
                        game.setCurrentCategory(trumpCard.getCardDescription());
                    } else {
                        game.setCurrentCategory(trumpCard.getCardDescription());
                    }

                    System.out.println("You played a trump card! Your hand is now: ");
                    player.viewAllCards();
                    System.out.print("Enter the card number you'd like to play: ");
                    game.setCurrentCard(player.playCard(inputReader.getCardNumber(player.getHandSize())));

                    if (game.getCurrentCard().getCardName().equals("Magnetite") && checkForWinningCombo) {
                        System.out.println(player.getName() + " played the round-winning combo! YAY!");
                        if (player.getHandSize() == 0) {
                            player.finish();
                            game.addWinner(game.getCurrentPlayer());
                        }
                        return;
                    }

                    if (player.getHandSize() == 0) {
                        player.finish();
                        game.addWinner(game.getCurrentPlayer());
                        if (game.isFinished()) {
                            return;
                        }
                    }
                }

            } else {
                if (player.getHandSize() == 0) {
                    player.finish();
                    game.addWinner(game.getCurrentPlayer());
                    if (game.isFinished()) {
                        return;
                    }
                }

                System.out.print("Choose a trump category: ");
                game.setCurrentCategory(inputReader.getCategory());
            }

            game.nextTurn();

        } else if (!allPlayers.get(startingPlayer).isFinished()) {
            AIPlayer player = (AIPlayer) allPlayers.get(startingPlayer);
            System.out.println("MY HAND IS: " + player.getHandSize());

            System.out.println(player.getName() + " goes first");
            game.setCurrentCategory(player.chooseCategory());
            tempCard = player.playFirstCard(game.getCurrentCategory());
            game.setCurrentCard(tempCard);

            System.out.println(game.getCurrentCard());

            if (player.getHandSize() == 0) {
                player.finish();
                game.addWinner(game.getCurrentPlayer());
                if (game.isFinished()) {
                    return;
                }
            }

            boolean checkForWinningCombo;

            while (game.getCurrentCard().getType().equals("trump")) {
                System.out.println(player.getName() + "played a trump card! Playing again...");
                game.setCurrentCard(player.playFirstCard(game.getCurrentCategory()));

                checkForWinningCombo = false;
                TrumpCard trumpCard = (TrumpCard) game.getCurrentCard();
                if (trumpCard.getCardDescription().equals("Change to trumps category of your choice")) {
                    game.setCurrentCategory(player.chooseCategory());
                } else if (trumpCard.getCardDescription().equals("Crustal abundance")) {
                    game.setCurrentCategory("crustal abundances");
                } else if (trumpCard.getCardName().equals("The Geophysicist")){
                    checkForWinningCombo = true;
                    game.setCurrentCategory(trumpCard.getCardDescription().toLowerCase());
                } else {
                    game.setCurrentCategory(trumpCard.getCardDescription().toLowerCase());
                }

                game.setCurrentCard(player.playFirstCard(game.getCurrentCategory()));

                if (game.getCurrentCard().getCardName().equals("Magnetite") && checkForWinningCombo) {
                    System.out.println(player.getName() + " played the round-winning combo!");
                    if (player.getHandSize() == 0) {
                        player.finish();
                        game.addWinner(game.getCurrentPlayer());
                    }
                    return;
                }

                if (player.getHandSize() == 0) {
                    player.finish();
                    game.addWinner(game.getCurrentPlayer());
                    if (game.isFinished()) {
                        return;
                    }
                }
            }


            game.nextTurn();
        }

        while (!game.isRoundFinished()) {
            Player player = allPlayers.get(game.getCurrentPlayer());
            if (!player.isInactive()) {
                if (game.getCurrentPlayer() == 0) {
                    HumanPlayer humanPlayer = (HumanPlayer) player;
                    System.out.print("Your turn! ");
                    if (game.getCurrentCard().getType().equals("trump")) {
                        System.out.print("The current category is " + game.getCurrentCategory() + " and you may play any card!");
                    } else {
                        messageDisplayer.displayCardToBeatInformation(game.getCurrentCard(), game.getCurrentCategory());
                    }
                    System.out.println("\nYour hand is: ");
                    humanPlayer.viewAllCards();
                    if (!humanPlayer.canPlayCard(game.getCurrentCard(), game.getCurrentCategory())) {
                        System.out.print("You have no cards that you can play! Press enter to pass.");
                        inputReader.getLine();
                        humanPlayer.pass();
                        if (game.getDeckSize() != 0) {
                            humanPlayer.pickUpCard(game.dealSingleCard());
                        }
                    } else {
                        System.out.print("Do you want to 'pass' or 'play'? ");
                        if (inputReader.passOrPlay().equals("pass")) {
                            System.out.println("You have chosen to pass.");
                            humanPlayer.pass();
                            if (game.getDeckSize() != 0) {
                                humanPlayer.pickUpCard(game.dealSingleCard());
                            }
                        } else {
                            System.out.print("Enter the number of the card you wish to play: ");
                            int cardIndex = inputReader.getCardNumber(player.getHandSize());
                            tempCard = humanPlayer.getCardAt(cardIndex);

                            while (!compareCards(tempCard, game.getCurrentCard(), game.getCurrentCategory())){
                                System.out.print("Invalid input! You must play a card with a HIGHER " + game.getCurrentCategory());
                                System.out.print(" Try again: ");
                                cardIndex = inputReader.getCardNumber(player.getHandSize());
                                tempCard = humanPlayer.getCardAt(cardIndex);
                            }

                            game.setCurrentCard(humanPlayer.playCard(cardIndex));

                            if (humanPlayer.getHandSize() == 0) {
                                humanPlayer.finish();
                                game.addWinner(game.getCurrentPlayer());
                                if (game.isFinished()) {
                                    return;
                                }
                            }


                            if (game.getCurrentCard().getType().equals("trump")) {
                                boolean checkForWinningCombo;
                                while(game.getCurrentCard().getType().equals("trump")) {
                                    checkForWinningCombo = false;
                                    TrumpCard trumpCard = (TrumpCard) game.getCurrentCard();
                                    if (trumpCard.getCardDescription().equals("Change to trumps category of your choice")) {
                                        System.out.print("Choose a trump category: ");
                                        game.setCurrentCategory(inputReader.getCategory());
                                    } else if (trumpCard.getCardDescription().equals("Crustal abundance")) {
                                        game.setCurrentCategory("crustal abundances");
                                    } else if (trumpCard.getCardName().equals("The Geophysicist")) {
                                        System.out.println("the card was played");
                                        checkForWinningCombo = true;
                                        game.setCurrentCategory(trumpCard.getCardDescription());
                                    } else {
                                        game.setCurrentCategory(trumpCard.getCardDescription());
                                    }

                                    System.out.println("You played a trump card! Your hand is now: ");
                                    humanPlayer.viewAllCards();
                                    System.out.print("Enter the number of the card you want to play next: ");
                                    game.setCurrentCard(humanPlayer.playCard(inputReader.getCardNumber(player.getHandSize())));

                                    if (game.getCurrentCard().getCardName().equals("Magnetite") && checkForWinningCombo) {
                                        System.out.println(player.getName() + " played the round-winning combo! YAY!");
                                        if (player.getHandSize() == 0) {
                                            player.finish();
                                            game.addWinner(game.getCurrentPlayer());
                                        }
                                        return;
                                    }

                                    if (humanPlayer.getHandSize() == 0) {
                                        humanPlayer.finish();
                                        game.addWinner(game.getCurrentPlayer());
                                        if (game.isFinished()) {
                                            return;
                                        }
                                    }
                                }
                            }
                        }
                    }
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
                        if (game.getDeckSize() != 0) {
                            aiPlayer.pickUpCard(game.dealSingleCard());
                        }

                        aiPlayer.pass();
                        System.out.println(aiPlayer.getName() + " passed");
                    }

                    if (aiPlayer.getHandSize() == 0) {
                        aiPlayer.finish();
                        game.addWinner(game.getCurrentPlayer());
                        if (game.isFinished()) {
                            return;
                        }
                    }

                    if (game.getCurrentCard().getType().equals("trump")) {
                        for (int i = 0; i < allPlayers.size(); ++i) {
                            allPlayers.get(i).activate();
                        }

                        boolean checkForWinningCombo;

                        while (game.getCurrentCard().getType().equals("trump")) {
                            checkForWinningCombo = false;
                            TrumpCard trumpCard = (TrumpCard) game.getCurrentCard();
                            if (trumpCard.getCardDescription().equals("Change to trumps category of your choice")) {
                                game.setCurrentCategory(aiPlayer.chooseCategory());
                            } else if (trumpCard.getCardDescription().equals("Crustal abundance")) {
                                game.setCurrentCategory("crustal abundances");
                            } else if (trumpCard.getCardName().equals("The Geophysicist")){
                                checkForWinningCombo = true;
                                game.setCurrentCategory(trumpCard.getCardDescription().toLowerCase());
                            } else {
                                game.setCurrentCategory(trumpCard.getCardDescription().toLowerCase());
                            }

                            game.setCurrentCard(aiPlayer.playFirstCard(game.getCurrentCategory()));

                            if (game.getCurrentCard().getCardName().equals("Magnetite") && checkForWinningCombo) {
                                System.out.println(aiPlayer.getName() + " played the round-winning combo!");
                                if (player.getHandSize() == 0) {
                                    player.finish();
                                    game.addWinner(game.getCurrentPlayer());
                                }
                                return;
                            }
                        }
                    } else {
                        game.nextTurn();
                    }

                    System.out.println(game.getCurrentCard().toString());
                }
            } else {
                game.nextTurn();
            }

            int playersPassed = 0;

            for (int i = 0; i < allPlayers.size(); ++i) {
                if (allPlayers.get(i).isInactive()) {
                    ++playersPassed;
                }
            }

            if (playersPassed == numberOfPlayers - 1) {
                for (int i = 1; i < allPlayers.size(); ++i) {
                    if (!allPlayers.get(i).isInactive()){
                        System.out.println(allPlayers.get(i).getName() + " wins this round!");
                        game.setCurrentPlayer(i);
                    }
                }

                game.setRoundFinished(true);
            }
        }
    }

    private static boolean compareCards(Card tempCard, Card currentCard, String currentCategory) {
        if (currentCard.getType().equals("trump") || tempCard.getType().equals("trump")) {
            return true;
        } else {
            PlayCard tempPlayCard = (PlayCard) tempCard;
            PlayCard currentPlayCard = (PlayCard) currentCard;

            if (currentCategory.equals("hardness")) {
                if (tempPlayCard.getHardnessAsDouble() > currentPlayCard.getHardnessAsDouble()) {
                    return true;
                }
            } else if (currentCategory.equals("specific gravity")) {
                if (tempPlayCard.getSpecificGravityAsDouble() > currentPlayCard.getSpecificGravityAsDouble()) {
                    return true;
                }
            } else if (currentCategory.equals("cleavage")) {
                if (tempPlayCard.getCleavageAsInt() > currentPlayCard.getCleavageAsInt()) {
                    return true;
                }
            } else if (currentCategory.equals("crustal abundances")) {
                if (tempPlayCard.getCrustalAbundancesAsInt() > currentPlayCard.getCrustalAbundancesAsInt()) {
                    return true;
                }
            } else if (currentCategory.equals("economic value")) {
                if (tempPlayCard.getEconomicValueAsInt() > currentPlayCard.getEconomicValueAsInt()) {
                    return true;
                }
            }
        }
        return false;
    }

    public static int menuHandler() {
        int menuOption;
        messageDisplayer.displayMenu();
        menuOption = inputReader.getMenuChoice();

        if (menuOption == 2) {
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
                exit(0);
            }
        }

        return menuOption;
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
