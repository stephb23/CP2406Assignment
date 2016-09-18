import cards.Card;
import cards.PlayCard;

/**
 * Created by Stephanie on 8/09/2016.
 */
public class MessageDisplayer {
    public MessageDisplayer() {
    }

    public void displayWelcome(){
        System.out.println("Hello and welcome to Supertrump Minerals!!");
    }

    public void displayNameConfirmation(String playerName) {
       System.out.println("Nice to meet you, " + playerName + ".");
    }

    public void displayMenu() {
        System.out.println("Here's what you can do: ");
        System.out.println("1. Start a Supertrump Game");
        System.out.println("2. View Instructions");
        System.out.println("3. About the Supertrump Game");
        System.out.println("4. Exit the program");
    }

    public void displayInstructions() {
        System.out.println("Instructions go here probably");

        // TODO: Insert actual instructions
    }

    public void displayAboutGame() {
        System.out.println("This game teaches you about rocks.");
        // TODO: Insert actual about game
    }

    public void startGame() {
        System.out.print("Great! You've chosen to start a game. To begin, \n" +
                "we just need to know how many AI players you want to verse! \n" +
                "Please enter a number between 1-4: ");
    }

    public void displayCardToBeatInformation(Card currentCard, String currentCategory) {
        PlayCard playCard = (PlayCard) currentCard;
        System.out.print("The current category is " + currentCategory + " and you must play a card with \n" +
                currentCategory + " greater than ");
        if (currentCategory.equals("hardness")) {
            System.out.print(playCard.getHardness());
        } else if (currentCategory.equals("specific gravity")) {
            System.out.print(playCard.getSpecificGravity());
        } else if (currentCategory.equals("cleavage")) {
            System.out.print(playCard.getCleavage());
        } else if (currentCategory.equals("crustal abundances")) {
            System.out.print(playCard.getCrustalAbundances());
        } else if (currentCategory.equals("economic value")) {
            System.out.print(playCard.getEconomicValue());
        }
    }
}
