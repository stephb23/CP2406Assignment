package gameControl;

import jdk.internal.util.xml.impl.Input;

import java.util.Scanner;
import java.util.concurrent.SynchronousQueue;
import java.util.stream.Collector;

/**
 * Created by Stephanie on 8/09/2016.
 */
public class InputReader {

    // Default constructor
    public InputReader() {
    }

    // Gets a user name for the human player
    public String getUserName(){
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter the name you would like to be called: ");
        String userName = input.next();
        return userName;
    }

    // Gets a menu choice from the human player
    public int getMenuChoice() {
        int menuChoice;
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the number of your choice: ");
        try {
            menuChoice = input.nextInt();
            while (menuChoice > 4 || menuChoice < 1) {
                System.out.print("Invalid input, choice must be between 1-4. Try again: ");
                menuChoice = input.nextInt();
            }
        } catch(Exception e) {
            System.out.print("You must enter an integer! Try again: ");
            menuChoice = getMenuChoice();
        }
        return menuChoice;
    }

    // Gets a character input from the human player
    public char getChar() {
        Scanner input = new Scanner(System.in);
        String possibleCharacter = input.next();

        while (possibleCharacter.length() != 1) {
            System.out.print("Error! That's a word, not a character! Try again: ");
            possibleCharacter = input.next();
        }

        char[] characters = possibleCharacter.toCharArray();
        char character = characters[0];
        return character;
    }

    // Gets an integer input from the human player
    public int getInt(int min, int max) {
        int integer;
        Scanner input = new Scanner(System.in);
        try {
            integer = input.nextInt();
            while (integer > max || integer < min) {
                System.out.print("Invalid input, choice must be between " + min + "-" + max + ". Try again: ");
                integer = input.nextInt();
            }
        } catch (Exception e) {
            System.out.print("You must enter an integer! Try again: ");
            integer = getInt(min, max);
        }

        return integer;
    }

    // Gets a card number from the human player
    public int getCardNumber(int handSize) {
        int chosenCardNumber;

        chosenCardNumber = getInt(1, handSize);

        return chosenCardNumber - 1;
    }

    // Gets a category choice from the user
    public String getCategory() {
        String chosenCategory;
        Scanner input = new Scanner(System.in);

        chosenCategory = input.nextLine().toLowerCase();

        while (!chosenCategory.equals("hardness") && !chosenCategory.equals("specific gravity") && !chosenCategory.equals("cleavage")
                && !chosenCategory.equals("crustal abundance") && !chosenCategory.equals("economic value")) {
            System.out.print("Invalid category! Type hardness, specific gravity, cleavage, crustal abundance, or economic value: ");
            chosenCategory = input.nextLine().toLowerCase();
        }

        return chosenCategory;
    }

    // Gets the user's choice between "pass" or "play"
    public String passPlayQuit() {
        String playChoice;
        Scanner input = new Scanner(System.in);

        playChoice = input.nextLine().toLowerCase();

        while (!playChoice.equals("play") && !playChoice.equals("pass") && !playChoice.equals("quit")) {
            System.out.print("Invalid choice! Please type 'pass', 'play' or 'quit': ");
            playChoice = input.nextLine().toLowerCase();
        }
        return playChoice;
    }

    // Gets a line of input from the user
    public String getLine() {
        String line;
        Scanner input = new Scanner(System.in);

        line = input.nextLine();

        return line;

    }
}
