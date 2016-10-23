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

import static java.lang.System.exit;

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
    private static CategoryDialog categoryDialog;
    private static MessageDisplayer messageDisplayer;
    private static boolean firstPlayerFlag = true;
    private static EndOfRoundDialog endOfRoundDialog;
    private static GameOverDialog gameOverDialog;

    public static void main(String[] args) {
        menuFrame = new MenuFrame();
        menuFrame.setVisible(true);
        categoryDialog = new CategoryDialog();
        categoryDialog.setVisible(false);
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

    public static MouseListener passListener = new MouseListener() {
        @Override
        public void  mouseClicked(MouseEvent mouseEvent) {
            new Thread() {
                @Override
                public void run() {
                    if (!gameFrame.isPanelEnabled()) {
                        return;
                    }

                    if (game.isFinished()){
                        return;
                    }

                    gameFrame.disablePanel();
                    firstPlayerFlag = false;
                    HumanPlayer humanPlayer = (HumanPlayer) allPlayers.get(0);
                    humanPlayer.pass();
                    if (game.getDeckSize() != 0) {
                        humanPlayer.pickUpCard(game.dealSingleCard());
                    }

                    gameFrame.drawPlayerHand(humanPlayer.getAllCards());
                    game.nextTurn();

                    if (game.isFinished()) {
                        gameOverDialog = new GameOverDialog(getWinners());
                        gameOverDialog.setVisible(true);
                        return;
                    }

                    if (isRoundFinished()) {
                        System.out.println("ROUND OVER BABY");
                        endOfRoundDialog = new EndOfRoundDialog(allPlayers.get(game.getCurrentPlayer()).getName());
                        endOfRoundDialog.setVisible(true);
                        return;
                    }

                    boolean trumpPlayed = false;
                    int handSize;

                    while (!game.isRoundFinished() && !trumpPlayed) {
                        if (game.getCurrentPlayer() == 0) {
                            game.nextTurn();
                        }

                        for (int i = game.getCurrentPlayer(); i < numberOfPlayers; ++i) {
                            if (game.getCurrentPlayer() != 0 && !allPlayers.get(i).isInactive()
                                    && !game.isRoundFinished() && !game.isFinished()) {
                                handSize = allPlayers.get(i).getHandSize();
                                gameFrame.drawActiveAIPlayer(i);
                                performAILogic(allPlayers.get(i));
                                if (!allPlayers.get(i).isInactive()) {
                                    gameFrame.drawInactiveAIPlayer(i);
                                }
                                delay(1000);
                                if (handSize -  allPlayers.get(i).getHandSize() > 1) {
                                    System.out.println("TRUMP ALERT");
                                    trumpPlayed = true;
                                    System.out.println(handSize - allPlayers.get(i).getHandSize());
                                    gameFrame.setEnabled(true);
                                }

                            } else if (game.isRoundFinished() || game.isFinished()) {
                                if (game.isFinished()) {
                                    gameFrame.updateMessage("Game over");
                                    gameOverDialog = new GameOverDialog(getWinners());
                                    gameOverDialog.setVisible(true);
                                }
                                return;
                            }
                        }
                    }
                    gameFrame.setEnabled(true);
                }
            }.start();
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

    public static ActionListener quitListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            game = null;
            allPlayers.clear();
            gameFrame.dispose();
            firstPlayerFlag = true;
            if (gameOverDialog != null) {
                gameOverDialog.dispose();
            }
            menuFrame.setVisible(true);
        }
    };

    public static ActionListener exitListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            exit(0);
        }
    };

    public static ActionListener categoryListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            game.setCurrentCategory(categoryDialog.getCategory());
            gameFrame.updateMessage("Category is " + game.getCurrentCategory());
            categoryDialog.setVisible(false);
            gameFrame.enablePanel();
        }
    };

    public static ActionListener gameSetupListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            new Thread() {
                public void run() {

                    gameFrame.prepareGamePanel();
                    gameFrame.disablePanel();

                    playerName = gameFrame.getPlayerName();
                    numberOfAIPlayers = gameFrame.getNumberOfPlayers();
                    numberOfPlayers = numberOfAIPlayers + 1;

                    game = new SupertrumpGame(numberOfAIPlayers);

                    // Create the players
                    createPlayers(numberOfPlayers);
                    System.out.println("\nPlayers in this game are: ");
                    for (Player player : allPlayers) {
                        System.out.println(player.getName());
                    }

                    gameFrame.drawAIPlayers(numberOfAIPlayers);

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
                    System.out.println("\n" + allPlayers.get(game.getCurrentPlayer()).getName() + " will start the game!\n");

                    if (game.getCurrentPlayer() == 0) {
                        categoryDialog.setVisible(true);
                    } else if (game.getCurrentPlayer() != 0) {
                        gameFrame.drawActiveAIPlayer(game.getCurrentPlayer());
                        gameFrame.disablePanel();
                        firstPlayerFlag = false;
                        // Cast the player to an AI player & display their name
                        AIPlayer aiPlayer = (AIPlayer) allPlayers.get(game.getCurrentPlayer());
                        messageDisplayer.displayStartingPlayerName(aiPlayer.getName());


                        // Get the AI player to choose a category
                        game.setCurrentCategory(aiPlayer.chooseCategory());
                        messageDisplayer.displayAICategoryChoice(aiPlayer.getName(), game.getCurrentCategory());
                        gameFrame.updateMessage("Category is " + game.getCurrentCategory());

                        // Get the AI player to play the first card of the round
                        game.setCurrentCard(aiPlayer.playFirstCard(game.getCurrentCategory()));

                        // Display the current card
                        messageDisplayer.displayCardChoiceMessage(aiPlayer.getName());
                        System.out.println(game.getCurrentCard());
                        gameFrame.updateCurrentCard(game.getCurrentCard());

                        delay(1000);

                        // Check whether the player has finished and determine whether the game has finished too.
                        if (isPlayerFinished() && game.isFinished()) {
                            gameFrame.enablePanel();
                            return;
                        }

                        // Flag for the Geologist + Magnetite combo
                        boolean checkForWinningCombo;

                        // Actions to perform while the card to beat is a trump card
                        if (game.getCurrentCard().getType().equals("trump")) {

                            // Display a message showing that the player has played a trump
                            messageDisplayer.displayTrumpCardPlayedMessage(game.getCurrentCategory(), aiPlayer.getName());

                            // Default value for this flag is false
                            checkForWinningCombo = false;

                            // Determine which category the trump card is setting the game to
                            TrumpCard trumpCard = (TrumpCard) game.getCurrentCard();
                            if (trumpCard.getCardDescription().equals("Change to trumps category of your choice")) {
                                game.setCurrentCategory(aiPlayer.chooseCategory());
                            } else if (trumpCard.getCardName().equals("The Geophysicist")) {
                                // Set the winning combo flag to true
                                checkForWinningCombo = true;
                                game.setCurrentCategory(trumpCard.getCardDescription().toLowerCase());
                            } else {
                                game.setCurrentCategory(trumpCard.getCardDescription().toLowerCase());
                            }

                            gameFrame.updateMessage("Category is " + game.getCurrentCategory());

                            // Get the AI player to play another card
                            game.setCurrentCard(aiPlayer.playFirstCard(game.getCurrentCategory()));

                            // If the new card is the Magnetite card, and the winning combo flag is true, then the player has won
                            // the round!
                            if (game.getCurrentCard().getCardName().equals("Magnetite") && checkForWinningCombo) {
                                isPlayerFinished();
                                gameFrame.enablePanel();
                                return;
                            }

                            // If the player and game have both finished, return to main
                            if (isPlayerFinished()) {
                                if (game.isFinished()) {
                                    return;
                                }
                                gameFrame.enablePanel();
                            }
                        }

                        // Move on to the next player's game
                        if (!allPlayers.get(game.getCurrentPlayer()).isInactive()) {
                            gameFrame.drawInactiveAIPlayer(game.getCurrentPlayer());
                        }
                        game.nextTurn();

                        if (game.getCurrentPlayer() != 0) {
                                    for (int i = game.getCurrentPlayer(); i < numberOfPlayers; ++i) {
                                        if (game.getCurrentPlayer() != 0 && !allPlayers.get(i).isInactive()
                                                && !game.isRoundFinished() && !game.isFinished()) {
                                            gameFrame.drawActiveAIPlayer(i);
                                            performAILogic(allPlayers.get(i));
                                            if (!allPlayers.get(i).isInactive()) {
                                                gameFrame.drawInactiveAIPlayer(i);
                                            }
                                        } else if (game.isRoundFinished() || game.isFinished()) {
                                            return;
                                        }
                                    }
                        }
                        gameFrame.enablePanel();
                    }
                }
            }.start();
        }
    };

    public static ActionListener newRoundListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            game.setRoundFinished(false);
            new Thread() {
                @Override
                public void run() {

                    endOfRoundDialog.dispose();

                    gameFrame.disablePanel();

                    for (int i = 0; i < allPlayers.size(); i++) {
                        Player player = allPlayers.get(i);
                        player.activate();
                        System.out.println("Player " + player.getName() + " inactive = " + player.isInactive());
                        if (!player.isInactive() && i != 0) {
                            gameFrame.drawInactiveAIPlayer(i);
                        }
                    }


                    if (game.getCurrentPlayer() == 0) {
                        categoryDialog.setVisible(true);
                        gameFrame.enablePanel();
                        game.setCurrentCard(new PlayCard());
                    } else if (game.getCurrentPlayer() != 0) {
                        gameFrame.disablePanel();
                        gameFrame.drawActiveAIPlayer(game.getCurrentPlayer());
                        firstPlayerFlag = false;
                        // Cast the player to an AI player & display their name
                        AIPlayer aiPlayer = (AIPlayer) allPlayers.get(game.getCurrentPlayer());
                        messageDisplayer.displayStartingPlayerName(aiPlayer.getName());


                        // Get the AI player to choose a category
                        game.setCurrentCategory(aiPlayer.chooseCategory());
                        messageDisplayer.displayAICategoryChoice(aiPlayer.getName(), game.getCurrentCategory());
                        gameFrame.updateMessage("Category is " + game.getCurrentCategory());

                        // Get the AI player to play the first card of the round
                        game.setCurrentCard(aiPlayer.playFirstCard(game.getCurrentCategory()));

                        // Display the current card
                        messageDisplayer.displayCardChoiceMessage(aiPlayer.getName());
                        System.out.println(game.getCurrentCard());
                        gameFrame.updateCurrentCard(game.getCurrentCard());

                        delay(1000);

                        // Check whether the player has finished and determine whether the game has finished too.
                        if (isPlayerFinished() && game.isFinished()) {
                            System.out.println("GAME OVER");
                            gameOverDialog = new GameOverDialog(getWinners());
                            gameOverDialog.setVisible(true);
                            gameFrame.disablePanel();
                            return;
                        }

                        // Flag for the Geologist + Magnetite combo
                        boolean checkForWinningCombo;

                        // Actions to perform while the card to beat is a trump card
                        if (game.getCurrentCard().getType().equals("trump")) {

                            // Display a message showing that the player has played a trump
                            messageDisplayer.displayTrumpCardPlayedMessage(game.getCurrentCategory(), aiPlayer.getName());

                            // Default value for this flag is false
                            checkForWinningCombo = false;

                            // Determine which category the trump card is setting the game to
                            TrumpCard trumpCard = (TrumpCard) game.getCurrentCard();
                            if (trumpCard.getCardDescription().equals("Change to trumps category of your choice")) {
                                game.setCurrentCategory(aiPlayer.chooseCategory());
                            } else if (trumpCard.getCardName().equals("The Geophysicist")) {
                                // Set the winning combo flag to true
                                checkForWinningCombo = true;
                                game.setCurrentCategory(trumpCard.getCardDescription().toLowerCase());
                            } else {
                                game.setCurrentCategory(trumpCard.getCardDescription().toLowerCase());
                            }

                            gameFrame.updateMessage("Category is " + game.getCurrentCategory());

                            // Get the AI player to play another card
                            if (!aiPlayer.isFinished()) {
                                game.setCurrentCard(aiPlayer.playFirstCard(game.getCurrentCategory()));
                            }

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
                                gameFrame.enablePanel();
                            }
                        }

                        // Move on to the next player's game
                        if (!allPlayers.get(game.getCurrentPlayer()).isInactive()) {
                            gameFrame.drawInactiveAIPlayer(game.getCurrentPlayer());
                        }
                        game.nextTurn();

                        if (game.getCurrentPlayer() != 0) {
                            for (int i = game.getCurrentPlayer(); i < numberOfPlayers; ++i) {
                                if (game.getCurrentPlayer() != 0 && !allPlayers.get(i).isInactive()
                                        && !game.isRoundFinished() && !game.isFinished()) {
                                    gameFrame.drawActiveAIPlayer(i);
                                    performAILogic(allPlayers.get(i));
                                    if (!allPlayers.get(i).isInactive()) {
                                        gameFrame.drawInactiveAIPlayer(i);
                                    }
                                } else if (game.isRoundFinished() || game.isFinished()) {
                                    gameFrame.disablePanel();
                                    return;
                                }
                            }
                        }

                        if (allPlayers.get(0).isFinished()) {
                            System.out.println("here");
                            gameFrame.disablePanel();
                            // Go on without me!!
                            while (!game.isFinished()) {
                                for (int i = 1; i < numberOfPlayers; i++) {
                                    if (game.getCurrentPlayer() != 0 && !allPlayers.get(game.getCurrentPlayer()).isInactive() &&
                                            !game.isRoundFinished() &&  !game.isFinished()) {
                                        if (!allPlayers.get(i).isInactive()) {
                                            gameFrame.drawActiveAIPlayer(i);
                                        }
                                        performAILogic(allPlayers.get(game.getCurrentPlayer()));
                                        if (!allPlayers.get(i).isInactive()) {
                                            gameFrame.drawInactiveAIPlayer(i);
                                        }
                                    } else if (game.isRoundFinished() || game.isFinished()) {
                                        gameFrame.disablePanel();
                                        return;
                                    } else {
                                        game.nextTurn();
                                    }
                                }
                            }

                            gameOverDialog = new GameOverDialog(getWinners());
                            gameOverDialog.setVisible(true);
                            return;
                        }

                        gameFrame.enablePanel();
                    }
                }
            }.start();

        }
    };

    public static MouseListener cardListener = new MouseListener() {

        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            new Thread() {
                @Override
                public void run() {
                    if (!gameFrame.isPanelEnabled()) {
                        return;
                    }

                    gameFrame.disablePanel();

                    if (game.isFinished()) {
                        gameFrame.disablePanel();
                        gameOverDialog = new GameOverDialog(getWinners());
                        gameOverDialog.setVisible(true);
                        return;
                    }

                    JLabel label = (JLabel) mouseEvent.getSource();
                    String cardName = label.getName();
                    HumanPlayer humanPlayer = (HumanPlayer) allPlayers.get(0);
                    Card tempCard;

                    int cardIndex = humanPlayer.getIndexOf(cardName);
                    tempCard = humanPlayer.getCardAt(cardIndex);

                    if (firstPlayerFlag) {
                        firstPlayerFlag = false;
                        game.setCurrentCard(tempCard);
                        gameFrame.updateCurrentCard(humanPlayer.playCard(cardIndex));
                        gameFrame.drawPlayerHand(humanPlayer.getAllCards());

                        isPlayerFinished(0);

                        if (allPlayers.get(0).isInactive() && !allPlayers.get(0).isFinished()) {
                            System.out.println("here");
                            gameFrame.disablePanel();
                            // Go on without me!!
                            while (!game.isRoundFinished()) {
                                for (int i = 1; i < numberOfPlayers; i++) {
                                    if (game.getCurrentPlayer() != 0 && !allPlayers.get(game.getCurrentPlayer()).isInactive() &&
                                            !game.isRoundFinished() &&  !game.isFinished()) {
                                        if (!allPlayers.get(i).isInactive()) {
                                            gameFrame.drawActiveAIPlayer(i);
                                        }
                                        performAILogic(allPlayers.get(game.getCurrentPlayer()));
                                        if (!allPlayers.get(i).isInactive()) {
                                            gameFrame.drawInactiveAIPlayer(i);
                                        }
                                    } else if (game.isRoundFinished() || game.isFinished()) {
                                        return;
                                    } else {
                                        game.nextTurn();
                                    }
                                }
                            }
                            return;
                        }

                        if (allPlayers.get(0).isFinished()) {
                            System.out.println("here");
                            gameFrame.disablePanel();
                            // Go on without me!!
                            while (!game.isFinished()) {
                                for (int i = 1; i < numberOfPlayers; i++) {
                                    if (game.getCurrentPlayer() != 0 && !allPlayers.get(game.getCurrentPlayer()).isInactive() &&
                                            !game.isRoundFinished() &&  !game.isFinished()) {
                                        if (!allPlayers.get(i).isInactive()) {
                                            gameFrame.drawActiveAIPlayer(i);
                                        }
                                        performAILogic(allPlayers.get(game.getCurrentPlayer()));
                                        if (!allPlayers.get(i).isInactive()) {
                                            gameFrame.drawInactiveAIPlayer(i);
                                        }
                                    } else if (game.isRoundFinished() || game.isFinished()) {
                                        return;
                                    } else {
                                        game.nextTurn();
                                    }
                                }
                            }

                            gameOverDialog = new GameOverDialog(getWinners());
                            gameOverDialog.setVisible(true);
                            return;
                        }

                        if (game.isFinished()) {
                            gameFrame.updateMessage("Game finished");
                            gameOverDialog = new GameOverDialog(getWinners());
                            gameOverDialog.setVisible(true);
                            gameFrame.disablePanel();
                            return;
                        }

                        if (isRoundFinished()) {
                            endOfRoundDialog = new EndOfRoundDialog(allPlayers.get(game.getCurrentPlayer()).getName());
                            endOfRoundDialog.setVisible(true);
                            return;
                        }

                        if (tempCard.getType().equals("trump")) {
                            gameFrame.enablePanel();
                            return;
                        } else {
                            game.nextTurn();
                            for (int i = game.getCurrentPlayer(); i < numberOfPlayers; ++i) {
                                if (game.getCurrentPlayer() != 0 && !allPlayers.get(i).isInactive()
                                        && !game.isRoundFinished() && !game.isFinished()) {
                                    gameFrame.drawActiveAIPlayer(i);
                                    performAILogic(allPlayers.get(i));
                                    if (!allPlayers.get(i).isInactive()) {
                                        gameFrame.drawInactiveAIPlayer(i);
                                    }
                                } else if (game.isRoundFinished() || game.isFinished()) {
                                    gameFrame.disablePanel();
                                    return;
                                } else {
                                    game.nextTurn();
                                }
                            }
                            gameFrame.enablePanel();
                            return;
                        }
                    }

                    // Check that the card that the player wants to play will beat the current card
                    if (!compareCards(tempCard, game.getCurrentCard(), game.getCurrentCategory())) {
                        System.out.println("Nope try again"); //TODO: ERROR PANEL
                        gameFrame.enablePanel();
                        return;
                    }
                    System.out.println("I am here");

                    // When the player has chosen a valid card, allow it to be played.
                    game.setCurrentCard(tempCard);
                    gameFrame.updateCurrentCard(humanPlayer.playCard(cardIndex));
                    gameFrame.drawPlayerHand(humanPlayer.getAllCards());
                    delay(1000);

                    isPlayerFinished(0);

                    if (allPlayers.get(0).isInactive() && !allPlayers.get(0).isFinished()) {
                        System.out.println("here");
                        gameFrame.disablePanel();
                        // Go on without me!!
                        while (!game.isRoundFinished()) {
                            for (int i = 1; i < numberOfPlayers; i++) {
                                if (game.getCurrentPlayer() != 0 && !allPlayers.get(game.getCurrentPlayer()).isInactive() &&
                                        !game.isRoundFinished() &&  !game.isFinished()) {
                                    gameFrame.drawActiveAIPlayer(i);
                                    performAILogic(allPlayers.get(game.getCurrentPlayer()));
                                    if (!allPlayers.get(i).isInactive()) {
                                        gameFrame.drawInactiveAIPlayer(i);
                                    }
                                } else if (game.isRoundFinished() || game.isFinished()) {
                                    return;
                                } else {
                                    game.nextTurn();
                                }
                            }
                        }
                        return;
                    }



                    if (allPlayers.get(0).isFinished()) {
                        System.out.println("here");
                        gameFrame.disablePanel();
                        // Go on without me!!
                        while (!game.isFinished()) {
                            for (int i = 1; i < numberOfPlayers; i++) {
                                if (game.getCurrentPlayer() != 0 && !allPlayers.get(game.getCurrentPlayer()).isInactive() &&
                                        !game.isRoundFinished() &&  !game.isFinished()) {
                                    gameFrame.drawActiveAIPlayer(i);
                                    performAILogic(allPlayers.get(game.getCurrentPlayer()));
                                    if (!allPlayers.get(i).isInactive()) {
                                        gameFrame.drawInactiveAIPlayer(i);
                                    }
                                } else if (game.isRoundFinished() || game.isFinished()) {
                                    return;
                                } else {
                                    game.nextTurn();
                                }
                            }
                        }

                        gameOverDialog = new GameOverDialog(getWinners());
                        gameOverDialog.setVisible(true);
                        return;
                    }

                    // If the player and game have finished, return to main
                    if (isPlayerFinished() && game.isFinished()) {
                        gameFrame.disablePanel();
                        return;
                    }

                    System.out.println("I am here 2");

                    // If the card played by the human is a trump card, assign the correct category.
                    if (game.getCurrentCard().getType().equals("trump")) {
                        // Activate all currently inactive players.
                        for (int i = 0; i < allPlayers.size(); i++) {
                            Player player = allPlayers.get(i);
                            player.activate();
                            if (!player.isInactive() && i != 0) {
                                gameFrame.drawInactiveAIPlayer(i);
                            }
                        }

                        if (game.getCurrentCard().getType().equals("trump")) {
                            // If the player and game have both finished, return to main
                            if (isPlayerFinished()) {
                                if (game.isFinished()) {
                                    gameFrame.disablePanel();
                                    return;
                                }
                            }

                            // Change the trump category accordingly
                            TrumpCard trumpCard = (TrumpCard) game.getCurrentCard();
                            if (trumpCard.getCardDescription().equals("Change to trumps category of your choice")) {
                                messageDisplayer.displayChooseCategory();
                                categoryDialog.setVisible(true);
                            } else if (trumpCard.getCardName().equals("The Geophysicist")) {
                                game.setCurrentCategory(trumpCard.getCardDescription().toLowerCase());
                            } else {
                                game.setCurrentCategory(trumpCard.getCardDescription().toLowerCase());
                            }

                            gameFrame.updateMessage("Category is " + game.getCurrentCategory());

                            if (!isPlayerFinished()) {
                                gameFrame.enablePanel();
                                return;
                            }
                        }
                        gameFrame.enablePanel();
                        return;
                    }

                    System.out.println("I am here 3");
                    game.nextTurn();

                    for (int i = game.getCurrentPlayer(); i < numberOfPlayers; i++) {
                        if (game.getCurrentPlayer() != 0 && !allPlayers.get(game.getCurrentPlayer()).isInactive() &&
                                !game.isRoundFinished() &&  !game.isFinished()) {
                            gameFrame.drawActiveAIPlayer(i);
                            performAILogic(allPlayers.get(game.getCurrentPlayer()));
                            if (!allPlayers.get(i).isInactive()) {
                                gameFrame.drawInactiveAIPlayer(i);
                            }
                        } else if (game.isRoundFinished() || game.isFinished()) {
                            gameFrame.disablePanel();
                            return;
                        } else {
                            game.nextTurn();
                        }
                    }

                    gameFrame.enablePanel();

                    isPlayerFinished(0);

                    if (allPlayers.get(0).isInactive() && !allPlayers.get(0).isFinished()) {
                        System.out.println("here");
                        gameFrame.disablePanel();
                        // Go on without me!!
                        while (!game.isRoundFinished()) {
                            for (int i = 1; i < numberOfPlayers; i++) {
                                if (game.getCurrentPlayer() != 0 && !allPlayers.get(game.getCurrentPlayer()).isInactive() &&
                                        !game.isRoundFinished() &&  !game.isFinished()) {
                                    gameFrame.drawActiveAIPlayer(i);
                                    performAILogic(allPlayers.get(game.getCurrentPlayer()));
                                    if (!allPlayers.get(i).isInactive()) {
                                        gameFrame.drawInactiveAIPlayer(i);
                                    }
                                } else if (game.isRoundFinished() || game.isFinished()) {
                                    return;
                                } else {
                                    game.nextTurn();
                                }
                            }
                        }
                        return;
                    }

                    if (allPlayers.get(0).isFinished()) {
                        gameFrame.disablePanel();
                        // Go on without me!!
                        while (!game.isFinished()) {
                            for (int i = 1; i < numberOfPlayers; i++) {
                                if (game.getCurrentPlayer() != 0 && !allPlayers.get(game.getCurrentPlayer()).isInactive() &&
                                        !game.isRoundFinished() &&  !game.isFinished()) {
                                    gameFrame.drawActiveAIPlayer(i);
                                    performAILogic(allPlayers.get(game.getCurrentPlayer()));
                                    if (!allPlayers.get(i).isInactive()) {
                                        gameFrame.drawInactiveAIPlayer(i);
                                    }
                                } else if (game.isRoundFinished() || game.isFinished()) {
                                    gameFrame.disablePanel();
                                    return;
                                } else {
                                    game.nextTurn();
                                }
                            }
                        }

                        gameOverDialog = new GameOverDialog(getWinners());
                        gameOverDialog.setVisible(true);
                    }
                }
            }.start();
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

    private static void performAILogic(Player player) {
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
            delay(1000);
            gameFrame.enablePanel();messageDisplayer.displayCard(game.getCurrentCard().toString(), aiPlayer.getName());
        } else {
            // The player must pass if they return null. Pick up a card if the deck isn't empty.
            if (game.getDeckSize() != 0) {
                aiPlayer.pickUpCard(game.dealSingleCard());
            }
            aiPlayer.pass();
            gameFrame.drawOutAIPlayer(game.getCurrentPlayer(), "P");
            messageDisplayer.displayAIPassMessage(aiPlayer.getName());
        }

        if (isPlayerFinished()) {
            game.nextTurn();
        }

        if (game.isFinished()) {
            gameOverDialog = new GameOverDialog(getWinners());
            gameOverDialog.setVisible(true);
            gameFrame.disablePanel();
            return;
        }

        if (isRoundFinished()) {
            System.out.println("round finished");
            endOfRoundDialog = new EndOfRoundDialog(allPlayers.get(game.getCurrentPlayer()).getName());
            endOfRoundDialog.setVisible(true);
            return;
        }

        // If the card played by the AI is a trump card, change the category and allow them to play
        // another card.
        if (game.getCurrentCard().getType().equals("trump")) {
            // Activate all currently inactive players.
            for (int i = 0; i < allPlayers.size(); i++) {
                Player p = allPlayers.get(i);
                p.activate();
                if (!p.isInactive() && i != 0) {
                    gameFrame.drawInactiveAIPlayer(i);
                }
            }

            // Flag for checking if the Geophysicist + Magnetite combination play occurs
            boolean checkForWinningCombo;

            while (game.getCurrentCard().getType().equals("trump") && !aiPlayer.isFinished()) {
                // If the player and game have both finished, return to main
                if (isPlayerFinished()) {
                    if (game.isFinished()) {
                        gameFrame.disablePanel();
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

                gameFrame.updateMessage("Category is " + game.getCurrentCategory());

                // Use play first card logic to get the AI player to pick another card to play
                game.setCurrentCard(aiPlayer.playFirstCard(game.getCurrentCategory()));


                // Delay for a second and display the current card.
                messageDisplayer.displayCard(game.getCurrentCard().toString(), aiPlayer.getName());
                gameFrame.updateCurrentCard(game.getCurrentCard());
                delay(1000);

                // Check whether the winning combination of Geophysicist + Magnetite has been played
                if (game.getCurrentCard().getCardName().equals("Magnetite") && checkForWinningCombo) {
                    messageDisplayer.displayWinningComboPlayedMessage(aiPlayer.getName());
                    isPlayerFinished();
                    return;
                }

                if (isPlayerFinished() && game.isFinished()) {
                    gameFrame.disablePanel();
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
        if (player.isFinished()) {
            return true;
        }

        if (player.getHandSize() == 0) {
            player.finish();
            gameFrame.drawOutAIPlayer(game.getCurrentPlayer(), "F");
            game.addWinner(game.getCurrentPlayer()); // add the player to the win list
            System.out.println(player.getName() + " finished");
            return true;
        }
        return false;
    }

    private static boolean isPlayerFinished(int playerNumber) {
        Player player = allPlayers.get(playerNumber);
        // Check whether the player has any cards left in their hand; if not they are finished
        if (player.isFinished()) {
            return true;
        }

        if (player.getHandSize() == 0) {
            player.finish();
            if (playerNumber != 0) {
                gameFrame.drawOutAIPlayer(playerNumber, "F");
            }
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

    public static boolean isRoundFinished() {
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
                    System.out.println(i);
                    if (i == 0) {
                        firstPlayerFlag = true;
                    }
                }
            }
            game.setRoundFinished(true);
            return true;
        }

        return false;
    }


    private static String[] getWinners() {
        int[] winnerIndices = game.getWinners();
        String[] winners = new String[winnerIndices.length];

        for (int i = 0; i < winnerIndices.length; ++i) {
            winners[i] = allPlayers.get(winnerIndices[i]).getName();
        }

        return winners;
    }


}
