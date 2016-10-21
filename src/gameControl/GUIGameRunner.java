package gameControl;

import cards.Card;
import cards.PlayCard;
import cards.TrumpCard;
import game.SupertrumpGame;
import gui.*;
import players.AIPlayer;
import players.HumanPlayer;
import players.Player;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
    private static MessageDisplayer messageDisplayer;

    public static void main(String[] args) {
        menuFrame = new MenuFrame();
        menuFrame.setVisible(true);
        messageDisplayer = new MessageDisplayer();
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

            gameFrame.prepareGamePanel();

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

            HumanPlayer player = (HumanPlayer) allPlayers.get(0);
            gameFrame.drawPlayerHand(player.getAllCards());

            // Choose first player
            game.selectDealer(numberOfPlayers);
            System.out.println("\n"+allPlayers.get(game.getCurrentPlayer()).getName() + " will start the game!\n");


            if (game.getCurrentPlayer() != 0) {
                    // Cast the player to an AI player & display their name
                    AIPlayer aiPlayer = (AIPlayer) allPlayers.get(game.getCurrentPlayer());
                    messageDisplayer.displayStartingPlayerName(aiPlayer.getName());


                    // Get the AI player to choose a category
                    game.setCurrentCategory(aiPlayer.chooseCategory());
                    messageDisplayer.displayAICategoryChoice(aiPlayer.getName(), game.getCurrentCategory());

                    // Get the AI player to play the first card of the round
                    game.setCurrentCard(aiPlayer.playFirstCard(game.getCurrentCategory()));

                    // Display the current card
                    messageDisplayer.displayCardChoiceMessage(aiPlayer.getName());
                    System.out.println(game.getCurrentCard());
                    gameFrame.updateCurrentCard(game.getCurrentCard());

                    // Check whether the player has finished and determine whether the game has finished too.
                    if (isPlayerFinished() && game.isFinished()) {
                        return;
                    }

                    // Flag for the Geologist + Magnetite combo
                    boolean checkForWinningCombo;

                    // Actions to perform while the card to beat is a trump card
                    while (game.getCurrentCard().getType().equals("trump")) {

                        // Display a message showing that the player has played a trump
                        messageDisplayer.displayTrumpCardPlayedMessage(game.getCurrentCategory(), aiPlayer.getName());

                        // Default value for this flag is false
                        checkForWinningCombo = false;

                        // Determine which category the trump card is setting the game to
                        TrumpCard trumpCard = (TrumpCard) game.getCurrentCard();
                        if (trumpCard.getCardDescription().equals("Change to trumps category of your choice")) {
                            game.setCurrentCategory(aiPlayer.chooseCategory());
                        }  else if (trumpCard.getCardName().equals("The Geophysicist")){
                            // Set the winning combo flag to true
                            checkForWinningCombo = true;
                            game.setCurrentCategory(trumpCard.getCardDescription().toLowerCase());
                        } else {
                            game.setCurrentCategory(trumpCard.getCardDescription().toLowerCase());
                        }

                        // Get the AI player to play another card
                        game.setCurrentCard(aiPlayer.playFirstCard(game.getCurrentCategory()));

                        // If the new card is the Magnetite card, and the winning combo flag is true, then the player has won
                        // the round!
                        if (game.getCurrentCard().getCardName().equals("Magnetite") && checkForWinningCombo) {
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

                if (game.getCurrentPlayer() != 0) {
                    for (int i = game.getCurrentPlayer(); i < numberOfPlayers; ++i) {
                        if (!allPlayers.get(i).isFinished() && !game.isRoundFinished() && !game.isFinished()) {
                            performAILogic(allPlayers.get(i));
                        }
                    }
                }
            }
        }
    };

    private static void performAILogic(Player player) {
        System.out.println("HERE");
        // AI PLAYER LOGIC

        // Create the AI player
        AIPlayer aiPlayer = (AIPlayer) player;
        Card tempCard;

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
            System.out.println(game.getCurrentCard().getCardName());
            gameFrame.updateCurrentCard(game.getCurrentCard());
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
                gameFrame.updateCurrentCard(game.getCurrentCard());

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

    public static MouseListener cardListener = new MouseListener() {

        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            JLabel label = (JLabel) mouseEvent.getSource();
            String cardName = label.getText();
            HumanPlayer player = (HumanPlayer) allPlayers.get(0);
            System.out.println(player.getIndexOf(cardName));
        }

        @Override
        public void mousePressed(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseReleased(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseEntered(MouseEvent mouseEvent) {
        }

        @Override
        public void mouseExited(MouseEvent mouseEvent) {

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

    private static boolean isPlayerFinished() {
        Player player = allPlayers.get(game.getCurrentPlayer());
        // Check whether the player has any cards left in their hand; if not they are finished
        if (player.getHandSize() == 0) {
            player.finish();
            game.addWinner(game.getCurrentPlayer()); // add the player to the win list
            System.out.println(player.getName() + " finished");
            return true;
        }
        return false;
    }

    // Delay function to give the appearance that the AI is "thinking" and so human can see all moves made
    private static void delay(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            for (int i = 0; i < 1000000; i++);
        }
    }
}
