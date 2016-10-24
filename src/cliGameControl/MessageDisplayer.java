package cliGameControl;

import cards.Card;
import cards.PlayCard;

/**
 * Created by Stephanie on 8/09/2016.
 */
public class MessageDisplayer {

    // Default constructor
    public MessageDisplayer() {

    }

    // Welcomes the user
    public void displayWelcome(){
        System.out.println("Hello and welcome to Supertrump Minerals!!");
    }

    // Display confirmation of the user's name
    public void displayNameConfirmation(String playerName) {
       System.out.println("\nNice to meet you, " + playerName + ".");
    }

    // Display the starting menu
    public void displayMenu() {
        System.out.println("\nHere's what you can do: ");
        System.out.println("1. Start a Supertrump Game");
        System.out.println("2. View Instructions");
        System.out.println("3. About the Supertrump Game");
        System.out.println("4. Exit the program");
    }

    // Display the instructions for the game.
    public void displayInstructions() {
        System.out.println("\n~ HOW TO PLAY ~");
        System.out.println("1. You will be asked to choose the number of players you want to verse");
        System.out.println("2. A dealer will be chosen randomly, and each player given 8 cards");
        System.out.println("3. The dealer will play first, choosing a card and a trump category");
        System.out.println("4. Each player after the dealer must play a card with a higher value in that trump category,\n" +
                "   play a trump card, or choose to pass");
        System.out.println("5. If a player passes, they pick up a card and remain out of the game until the next round \n" +
                "   starts, or until a trump card is played");
        System.out.println("6. If a trump card is played, the category is changed to the category specified on the card");
        System.out.println("7. The player who throws a trump card then immediately gets to play another card");
        System.out.println("8. If a player plays The Geophysicist followed by Magnetite, they win the round immediately");
        System.out.println("9. A round continues until all but one player has passed; they become the winning player");
        System.out.println("10. A game continues until all but one player has lost all of their cards");
        System.out.println();
    }

    // Display information about the game
    public void displayAboutGame() {
        System.out.println("\n~ ABOUT THE GAME ~");
        System.out.println("Mineral Supertrumps is designed to teach players about the properties and uses of common rocks.");
        System.out.println("Each pack contains 54 mineral cards and 6 'supertrump' cards. Each mineral card contains \n" +
                "information including chemical formula, classification, crystal system, and where the mineral commonly found.");
        System.out.println("Additionally, information in the five playing categories - hardness, specific gravity, cleavage,\n" +
                "crustal abundance and economic value, is included in each card.");
        System.out.println("This game is designed for 3-5 players, and the objective is to be the first player to lose" +
                "\nall of your cards!");
        System.out.println("\n\n~ ABOUT THE TRUMP CATEGORIES ~");
        System.out.println("Hardness: relates to Moh's hardness scale, from 1-10.");
        System.out.println("Specific Gravity: the density of the mineral with respect to pure water");
        System.out.println("Cleavage: the number of cleavage planes and how well the planes are expressed in the crystal");
        System.out.println("Crustal abundance: how commonly the mineral is found in the Earth's crust");
        System.out.println("Economic value: the monetary worth of the mineral\n");
    }

    // Display information about starting the game
    public void startGame() {
        System.out.print("\nGreat! You've chosen to start a game. To begin, \n" +
                "we just need to know how many AI players you want to verse! \n" +
                "Please enter a number between 2-4: ");
    }

    // Display a summary of the trump category
    public void displayTrumpCategoryInformation(String currentCategory) {
        System.out.println("The current category is " + currentCategory + " and you may play any card!");
    }

    // Display a summary of the card to beat
    public void displayCardToBeatInformation(Card currentCard, String currentCategory) {
        PlayCard playCard = (PlayCard) currentCard;
        System.out.print("The current category is " + currentCategory + " and you must play a card with " +
                currentCategory + " greater than ");
        if (currentCategory.equals("hardness")) {
            System.out.print(playCard.getHardness());
        } else if (currentCategory.equals("specific gravity")) {
            System.out.print(playCard.getSpecificGravity());
        } else if (currentCategory.equals("cleavage")) {
            System.out.print(playCard.getCleavage());
        } else if (currentCategory.equals("crustal abundance")) {
            System.out.print(playCard.getCrustalAbundance());
        } else if (currentCategory.equals("economic value")) {
            System.out.print(playCard.getEconomicValue());
        }
        System.out.println();
    }

    // Display prompt for selecting a trump suit
    public void displayChooseCategory() {
        System.out.print("Choose a trump category: ");
    }

    // Display trump card played message
    public void displayTrumpCardPlayedMessage(String currentCategory) {
        System.out.println("\nYou played a trump card! The trump category is now " + currentCategory);
    }

    // Overridden trump card played message for use with AI players
    public void displayTrumpCardPlayedMessage(String currentCategory, String currentPlayer) {
        System.out.println(currentPlayer + " played a trump card! The trump category is now " + currentCategory);
    }

    // Display prompt for choosing card number
    public void displayCardNumberPrompt() {
        System.out.print("Enter the number of the card that you wish to play: ");
    }

    // Display starting player
    public void displayStartingPlayerName(String name) {
        System.out.println(name + " goes first!");
    }

    // Display winning combo message
    public void displayWinningComboPlayedMessage() {
        System.out.println("\nYou played The Geophysicist + Magnetite cards and have won the round!");
    }

    public void displayWinningComboPlayedMessage(String currentPlayer) {
        System.out.println("\n" + currentPlayer + " played The Geophysicist + Magnetite and has won the round!");
    }

    // Display "your turn" message
    public void displayYourTurnMessage(String name) {
        System.out.println("\nYour turn, " + name + "!");
    }

    // Display pass/play/quit
    public void displayPassPlayQuit() {
        System.out.print("Do you want to 'pass', 'play', or 'quit'? ");
    }

    // Display confirmation of passing
    public void displayPassConfirmation() {
        System.out.println("You have chosen to pass.");
    }

    // Display message informing the player that they have to pass
    public void displayYouShallPass() {
        System.out.print("You have no cards that you can play! Press enter to pass.");
    }

    // Display message confirming choice to quit the game.
    public void displayQuitConfirmation() {
        System.out.println("You have chosen to quit the game.");
    }

    // Display message telling the user that they tried to play an invalid card
    public void displayCardTooSmallError(String currentCategory) {
        System.out.print("Invalid input! You must play a card with a HIGHER " + currentCategory);
        System.out.print(" Try again: ");
    }

    // Display a message confirming that an AI player has passed
    public void displayAIPassMessage(String name) {
        System.out.println("\n" + name + " chose to pass.");
    }

    // Display the choice of category that an AI player has made
    public void displayAICategoryChoice(String name, String currentCategory) {
        System.out.println(name + " set the current category to " + currentCategory);
    }

    // Display a card
    public void displayCard(String cardString, String playerName) {
        System.out.println("\n" + playerName + " played the following card: ");
        System.out.println(cardString);
    }

    // Display the winner of a round
    public void displayRoundWinner(String name) {
        System.out.println("\n" + name + " wins this round!");
    }

    public void displayPlayerFinished(String name) {
        System.out.println("\n" + name + " has finished the game!");
    }

    public void displayCardChoiceMessage(String name) {
        System.out.println(name + " chose the following card: ");
    }

    public void displayDeckSize(int deckSize) {
        System.out.println("\nThe deck has " + deckSize + " cards remaining");
    }

    public void displayPlayerHandSize(String name, int handSize) {
        System.out.println(name + " has " + handSize + " cards in their hand.");
    }
}
