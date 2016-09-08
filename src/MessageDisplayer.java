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
        System.out.println("1. Start a SupertrumpGame");
        System.out.println("2. View Instructions");
        System.out.println("3. About the SupertrumpGame");
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
}
