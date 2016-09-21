package gameControl;

import cards.Card;
import cards.PlayCard;
import cards.TrumpCard;
import game.SupertrumpGame;
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
            int numberOfAIPlayers = inputReader.getInt(2, 4);
            numberOfPlayers = numberOfAIPlayers + 1;
            game = new SupertrumpGame(numberOfAIPlayers);

            // Create the players
            createPlayers(numberOfPlayers);
            System.out.println("\nPlayers in this game are: ");
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
            System.out.println("\n"+allPlayers.get(game.getCurrentPlayer()).getName() + " will start the game!\n");

            // While the game isn't finished, play rounds.
            while (!game.isFinished()) {
                System.out.println("\nNEW ROUND");
                playRound(game.getCurrentPlayer());

                // After a round has finished, reactivate all players who had passed
                for (Player player : allPlayers) {
                    player.activate();
                }

                // Reset round finished variable.
                game.setRoundFinished(false);
            }

            // When the game is finished, print out the order players finished in (not including the loser).
            System.out.println("\nThe winners are: ");
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

            // Ask for a card number and play that card
            messageDisplayer.displayCardNumberPrompt();
            game.setCurrentCard(player.playCard(inputReader.getCardNumber(player.getHandSize())));


            if (game.getCurrentCard().getType().equals("trump")) {
                // While the card to beat is a trump card, allow the player to continue playing cards until they
                // play a play card, or win the game (whichever comes first!)
                while (game.getCurrentCard().getType().equals("trump")) {

                    // If the player has finished, break out of the while loop
                    // If the game is also finished, return to main
                    if (isPlayerFinished()) {
                        if (game.isFinished()) {
                            return;
                        }
                        break;
                    }

                    boolean checkForWinningCombo = false;

                    // Cast the current card to a trump card
                    TrumpCard trumpCard = (TrumpCard) game.getCurrentCard();

                    // Set the current category
                    if (trumpCard.getCardDescription().equals("Change to trumps category of your choice")) {
                        messageDisplayer.displayChooseCategory();
                        game.setCurrentCategory(inputReader.getCategory());
                    } else if (trumpCard.getCardName().equals("The Geophysicist")){
                        // First card in the round winning combo - set a flag that identifies that it has been played
                        checkForWinningCombo = true;
                        game.setCurrentCategory(trumpCard.getCardDescription());
                    } else {
                        game.setCurrentCategory(trumpCard.getCardDescription());
                    }

                    // Display hand again and ask for a card number
                    messageDisplayer.displayTrumpCardPlayedMessage(game.getCurrentCategory());
                    player.viewAllCards();
                    messageDisplayer.displayCardNumberPrompt();
                    game.setCurrentCard(player.playCard(inputReader.getCardNumber(player.getHandSize())));

                    // If Magnetite is played after
                    if (game.getCurrentCard().getCardName().equals("Magnetite") && checkForWinningCombo) {
                        System.out.println(player.getName() + " played the round-winning combo! YAY!");
                        isPlayerFinished();
                        return;
                    }

                    // If the player is finished, and the game is consequently finished, return to main
                    if (isPlayerFinished() && game.isFinished()) {
                       return;
                    }
                }

            } else { // if card type is play card
                // Check whether the player is finished & if the game has finished as a result return
                if (isPlayerFinished() && game.isFinished()) {
                    return;
                }

                // Prompt the user to select the starting category
                messageDisplayer.displayChooseCategory();
                game.setCurrentCategory(inputReader.getCategory());
            }

            // Move on to the next player's turn
            game.nextTurn();

        } else if (!allPlayers.get(startingPlayer).isFinished()) {
            // Cast the player to an AI player & display their name
            AIPlayer player = (AIPlayer) allPlayers.get(startingPlayer);
            messageDisplayer.displayStartingPlayerName(player.getName());
            messageDisplayer.displayCardChoiceMessage(player.getName());

            // Get the AI player to choose a category
            game.setCurrentCategory(player.chooseCategory());

            // Get the AI player to play the first card of the round
            game.setCurrentCard(player.playFirstCard(game.getCurrentCategory()));

            // Display the current card
            System.out.println(game.getCurrentCard());

            // Check whether the player has finished and determine whether the game has finished too.
            if (isPlayerFinished() && game.isFinished()) {
                return;
            }

            // Flag for the Geologist + Magnetite combo
            boolean checkForWinningCombo;

            // Actions to perform while the card to beat is a trump card
            while (game.getCurrentCard().getType().equals("trump")) {

                // Display a message showing that the player has played a trump
                messageDisplayer.displayTrumpCardPlayedMessage(game.getCurrentCategory(), player.getName());

                // Default value for this flag is false
                checkForWinningCombo = false;

                // Determine which category the trump card is setting the game to
                TrumpCard trumpCard = (TrumpCard) game.getCurrentCard();
                if (trumpCard.getCardDescription().equals("Change to trumps category of your choice")) {
                    game.setCurrentCategory(player.chooseCategory());
                }  else if (trumpCard.getCardName().equals("The Geophysicist")){
                    // Set the winning combo flag to true
                    checkForWinningCombo = true;
                    game.setCurrentCategory(trumpCard.getCardDescription().toLowerCase());
                } else {
                    game.setCurrentCategory(trumpCard.getCardDescription().toLowerCase());
                }

                // Get the AI player to play another card
                game.setCurrentCard(player.playFirstCard(game.getCurrentCategory()));

                // If the new card is the Magnetite card, and the winning combo flag is true, then the player has won
                // the round!
                if (game.getCurrentCard().getCardName().equals("Magnetite") && checkForWinningCombo) {
                    messageDisplayer.displayWinningComboPlayedMessage(player.getName());
                    isPlayerFinished();
                    return;
                }

                // If the player and game have both finished, return to main
                if (isPlayerFinished()) {
                    if (game.isFinished()) {
                        return;
                    }
                    break;
                }
            }

            // Move on to the next player's game
            game.nextTurn();
        }

        // The following game logic outlines what is done until all players but one have passed (finishing the round)
        while (!game.isRoundFinished()) {
            Player player = allPlayers.get(game.getCurrentPlayer());

            // If the player isn't inactive, then they may play a card!
            if (!player.isInactive()) {

                // Logic for the human player
                if (game.getCurrentPlayer() == 0) {
                    HumanPlayer humanPlayer = (HumanPlayer) player;

                    // Display message indicating that it is the human player's turn
                    messageDisplayer.displayYourTurnMessage(humanPlayer.getName());

                    // If the current card is a trump card, display the current category.
                    // Otherwise, show a summary of the card to beat!
                    if (game.getCurrentCard().getType().equals("trump")) {
                        messageDisplayer.displayTrumpCategoryInformation(game.getCurrentCategory());
                    } else {
                        messageDisplayer.displayCardToBeatInformation(game.getCurrentCard(), game.getCurrentCategory());
                    }

                    // Display the player's cards for them
                    humanPlayer.viewAllCards();

                    // Check automatically whether the human player can play a card
                    if (!humanPlayer.canPlayCard(game.getCurrentCard(), game.getCurrentCategory())) {
                        // If the player can't play any of their cards, tell them this and ask for confirmation
                        messageDisplayer.displayYouShallPass();
                        inputReader.getLine();
                        humanPlayer.pass();

                        // If there are still cards in the deck, the player picks up
                        if (game.getDeckSize() != 0) {
                            humanPlayer.pickUpCard(game.dealSingleCard());
                        }
                    }  else {
                        // Give the user the option to pass, play or quit the game.
                        messageDisplayer.displayPassPlayQuit();
                        String passPlayQuitOption = inputReader.passPlayQuit();

                        // If the user passes, confirm this action and deal a card to them.
                        switch (passPlayQuitOption) {
                            case "pass":
                                messageDisplayer.displayPassConfirmation();
                                humanPlayer.pass();
                                // Deal a card only if the deck still contains cards
                                if (game.getDeckSize() != 0) {
                                    humanPlayer.pickUpCard(game.dealSingleCard());
                                }
                                break;
                            case "quit":
                                // Display message confirming the player has quit and exit the program.
                                messageDisplayer.displayQuitConfirmation();
                                exit(0);
                            default:
                                // Ask the user for a card number to play
                                messageDisplayer.displayCardNumberPrompt();
                                int cardIndex = inputReader.getCardNumber(player.getHandSize());
                                tempCard = humanPlayer.getCardAt(cardIndex);

                                // Check that the card that the player wants to play will beat the current card
                                while (!compareCards(tempCard, game.getCurrentCard(), game.getCurrentCategory())) {
                                    messageDisplayer.displayCardTooSmallError(game.getCurrentCategory());
                                    cardIndex = inputReader.getCardNumber(player.getHandSize());
                                    tempCard = humanPlayer.getCardAt(cardIndex);
                                }

                                // When the player has chosen a valid card, allow it to be played.
                                game.setCurrentCard(humanPlayer.playCard(cardIndex));

                                // If the player and game have finished, return to main
                                if (isPlayerFinished() && game.isFinished()) {
                                    return;
                                }

                                // If the card played by the human is a trump card, assign the correct category.
                                if (game.getCurrentCard().getType().equals("trump")) {
                                    // Activate all currently inactive players.
                                    for (Player p : allPlayers) {
                                       p.activate();
                                    }

                                    // Assign the correct category
                                    boolean checkForWinningCombo;
                                    while (game.getCurrentCard().getType().equals("trump")) {
                                        // If the player and game have both finished, return to main
                                        if (isPlayerFinished()) {
                                            if (game.isFinished()) {
                                                return;
                                            }
                                            break;
                                        }

                                        // Flag for Geophysicist + Magnetite combination
                                        checkForWinningCombo = false;

                                        // Change the trump category accordingly
                                        TrumpCard trumpCard = (TrumpCard) game.getCurrentCard();
                                        if (trumpCard.getCardDescription().equals("Change to trumps category of your choice")) {
                                            messageDisplayer.displayChooseCategory();
                                            game.setCurrentCategory(inputReader.getCategory());
                                        } else if (trumpCard.getCardDescription().equals("Crustal abundance")) {
                                            game.setCurrentCategory("crustal abundance");
                                        } else if (trumpCard.getCardName().equals("The Geophysicist")) {
                                            // Set a flag to identify that the geophysicist has been played
                                            checkForWinningCombo = true;
                                            game.setCurrentCategory(trumpCard.getCardDescription());
                                        } else {
                                            game.setCurrentCategory(trumpCard.getCardDescription());
                                        }

                                        // Prompt the user to choose their next card
                                        messageDisplayer.displayTrumpCardPlayedMessage(game.getCurrentCategory());
                                        humanPlayer.viewAllCards();
                                        messageDisplayer.displayCardNumberPrompt();
                                        game.setCurrentCard(humanPlayer.playCard(inputReader.getCardNumber(player.getHandSize())));

                                        // If the cards Geophysicist + Magnetite have been played, the round has been won.
                                        if (game.getCurrentCard().getCardName().equals("Magnetite") && checkForWinningCombo) {
                                            messageDisplayer.displayWinningComboPlayedMessage();
                                            isPlayerFinished();
                                            return;
                                        }

                                        if (isPlayerFinished() && game.isFinished()) {
                                            return;
                                        }
                                    }
                                }
                                break;
                        }
                    }

                    // Progress to the next player's turn
                    game.nextTurn();

                } else if (!player.isFinished()){
                    // AI PLAYER LOGIC

                    // Create the AI player
                    AIPlayer aiPlayer = (AIPlayer) player;

                    // If the card to beat is a trump card, the AI player uses "Choose First Card" logic
                    // Otherwise it uses normal play any card logic
                    if (game.getCurrentCard().getType().equals("trump")) {
                        tempCard = aiPlayer.playFirstCard(game.getCurrentCategory());
                    } else {
                        tempCard = aiPlayer.playCard(game.getCurrentCard(), game.getCurrentCategory());
                    }

                    // If the AI player can't play a card, it returns null. Any other return is an actual card.
                    if (tempCard != null) {
                        // Set the current card if not null.
                        game.setCurrentCard(tempCard);
                        messageDisplayer.displayCard(game.getCurrentCard().toString(), aiPlayer.getName());
                    } else {
                        // The player must pass if they return null. Pick up a card if the deck isn't empty.
                        if (game.getDeckSize() != 0) {
                            aiPlayer.pickUpCard(game.dealSingleCard());
                        }
                        aiPlayer.pass();
                        messageDisplayer.displayAIPassMessage(aiPlayer.getName());
                    }

                    // If the player has finished and the game has finished, return to main.
                    if (isPlayerFinished() && game.isFinished()) {
                        return;
                    }

                    // If the card played by the AI is a trump card, change the category and allow them to play
                    // another card.
                    if (game.getCurrentCard().getType().equals("trump")) {

                        // Activate all currently inactive players.
                        for (Player p : allPlayers) {
                            p.activate();
                        }

                        // Flag for checking if the Geophysicist + Magnetite combination play occurs
                        boolean checkForWinningCombo;

                        while (game.getCurrentCard().getType().equals("trump") && !aiPlayer.isFinished()) {
                            // If the player and game have both finished, return to main
                            if (isPlayerFinished()) {
                                if (game.isFinished()) {
                                    return;
                                }
                                break;
                            }

                            // Flag for winning combo
                            checkForWinningCombo = false;

                            // Set the category according to the trump card
                            TrumpCard trumpCard = (TrumpCard) game.getCurrentCard();
                            if (trumpCard.getCardDescription().equals("Change to trumps category of your choice")) {
                                game.setCurrentCategory(aiPlayer.chooseCategory());
                                messageDisplayer.displayAICategoryChoice(aiPlayer.getName(), game.getCurrentCategory());
                            } else if (trumpCard.getCardDescription().equals("Crustal abundance")) {
                                game.setCurrentCategory("crustal abundance");
                            } else if (trumpCard.getCardName().equals("The Geophysicist")){
                                // Flag that the first part of the round-winning combo has been played
                                checkForWinningCombo = true;
                                game.setCurrentCategory(trumpCard.getCardDescription().toLowerCase());
                            } else {
                                game.setCurrentCategory(trumpCard.getCardDescription().toLowerCase());
                            }

                            // Use play first card logic to get the AI player to pick another card to play
                            game.setCurrentCard(aiPlayer.playFirstCard(game.getCurrentCategory()));


                            // Delay for a second and display the current card.
                            delay(1000);
                            messageDisplayer.displayCard(game.getCurrentCard().toString(), aiPlayer.getName());

                            // Check whether the winning combination of Geophysicist + Magnetite has been played
                            if (game.getCurrentCard().getCardName().equals("Magnetite") && checkForWinningCombo) {
                                messageDisplayer.displayWinningComboPlayedMessage(aiPlayer.getName());
                                isPlayerFinished();
                                return;
                            }

                            if (isPlayerFinished() && game.isFinished()) {
                                return;
                            }
                        }
                        game.nextTurn();
                    }  else {
                        game.nextTurn();
                    }

                    // Create a timed delay so that the human player can see each AI Player's move
                    delay(1000);

                }
            } else {
                // Move on to the next players turn if this player has passed
                game.nextTurn();
            }

            // Count the number of players that have passed or finished
            int playersPassed = 0;
            for (Player p : allPlayers) {
                if (p.isInactive()) {
                    ++playersPassed;
                }
            }

            // If the number of players that have passed is equal to the number of players minus one, then the round
            // has finished.
            if (playersPassed == numberOfPlayers - 1) {
                for (int i = 0; i < allPlayers.size(); ++i) {
                    if (!allPlayers.get(i).isInactive()){
                        messageDisplayer.displayRoundWinner(allPlayers.get(i).getName());
                        game.setCurrentPlayer(i);
                    }
                }
                game.setRoundFinished(true);
            }
        }
    }

    // Delay function to give the appearance that the AI is "thinking" and so human can see all moves made
    private static void delay(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            for (int i = 0; i < 1000000; i++);
        }
    }

    // Compare two cards to identify whether the first beats the second in the given current category
    private static boolean compareCards(Card tempCard, Card currentCard, String currentCategory) {
        // If either of the cards are trump cards, then the temporary card can be played
        if (currentCard.getType().equals("trump") || tempCard.getType().equals("trump")) {
            return true;
        } else {
            PlayCard tempPlayCard = (PlayCard) tempCard;
            PlayCard currentPlayCard = (PlayCard) currentCard;

            // Work out which category is being compared and make the comparison
            // In any case, if the temporary card is greater tha the current card, return true as it can be played.
            switch (currentCategory) {
                case "hardness":
                    if (tempPlayCard.getHardnessAsDouble() > currentPlayCard.getHardnessAsDouble()) {
                        return true;
                    }
                    break;
                case "specific gravity":
                    if (tempPlayCard.getSpecificGravityAsDouble() > currentPlayCard.getSpecificGravityAsDouble()) {
                        return true;
                    }
                    break;
                case "cleavage":
                    if (tempPlayCard.getCleavageAsInt() > currentPlayCard.getCleavageAsInt()) {
                        return true;
                    }
                    break;
                case "crustal abundance":
                    if (tempPlayCard.getCrustalAbundanceAsInt() > currentPlayCard.getCrustalAbundanceAsInt()) {
                        return true;
                    }
                    break;
                case "economic value":
                    if (tempPlayCard.getEconomicValueAsInt() > currentPlayCard.getEconomicValueAsInt()) {
                        return true;
                    }
                    break;
            }
        }

        // If the code reaches here then the temporary card will NOT beat the current card, return false.
        return false;
    }


    // This code handles the menu and it's choices
    private static int menuHandler() {
        int menuOption;

        // Display the menu and get the user's choice
        messageDisplayer.displayMenu();
        menuOption = inputReader.getMenuChoice();

        // Display instructions or about game
        if (menuOption == 2) {
            messageDisplayer.displayInstructions();
        } else if (menuOption == 3) {
            messageDisplayer.displayAboutGame();
        }

        // Display option to return to main menu or exit the game
        if (menuOption == 2 || menuOption == 3) {
            System.out.println("Type 'r' to return to the menu or 'e' to exit.");
            char choice = inputReader.getChar();
            while (choice != 'r' && choice != 'e') {
                System.out.println("Invalid input! Type 'r' to return to menu or 'e' to exit: ");
                choice = inputReader.getChar();
            }
            if (choice == 'r') {
                menuHandler();
            } else {
                System.out.println("Goodbye! See you next time!");
                exit(0);
            }
        }

        // Return the menu option
        return menuOption;
    }

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

    // Return true if a player has finished
    private static boolean isPlayerFinished() {
        Player player = allPlayers.get(game.getCurrentPlayer());
        // Check whether the player has any cards left in their hand; if not they are finished
        if (player.getHandSize() == 0) {
            player.finish();
            game.addWinner(game.getCurrentPlayer()); // add the player to the win list
            messageDisplayer.displayPlayerFinished(player.getName());
            return true;
        }
        return false;
    }
}
