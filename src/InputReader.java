import jdk.internal.util.xml.impl.Input;

import java.util.Scanner;
import java.util.stream.Collector;

/**
 * Created by Stephanie on 8/09/2016.
 */
public class InputReader {
    public InputReader() {

    }

    public String getUserName(){
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter the name you would like to be called: ");
        String userName = input.next();
        return userName;
    }

    public int getMenuChoice() {
        int menuChoice;
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the number of your choice: ");
        try {
            menuChoice = input.nextInt();
            while (menuChoice > 3 || menuChoice < 0) {
                System.out.print("Invalid input, choice must be between 1-3. Try again: ");
                menuChoice = input.nextInt();
            }
        } catch(Exception e) {
            System.out.println("You must enter an integer! Try again. ");
            menuChoice = getMenuChoice();
        }
        return menuChoice;
    }

    public char getChar() {
        Scanner input = new Scanner(System.in);
        String possibleCharacter = input.next();

        while (possibleCharacter.length() != 1) {
            System.out.println("Error! That's a word, not a character! Try again: ");
            possibleCharacter = input.next();
        }

        char[] characters = possibleCharacter.toCharArray();
        char character = characters[0];
        return character;
    }

    public int getInt(int min, int max) {
        int integer;
        Scanner input = new Scanner(System.in);
        try {
            integer = input.nextInt();
            while (integer > max || integer < min) {
                System.out.print("Invalid input, choice must be between 1-4. Try again: ");
                integer = input.nextInt();
            }
        } catch (Exception e) {
            System.out.print("You must enter an integer! Try again: ");
            integer = getInt(min, max);
        }

        return integer;
    }
}
