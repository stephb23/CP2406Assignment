package game;

import cards.Card;
import cards.PlayCard;
import decks.Deck;
import decks.XMLDeckBuilder;

import java.util.ArrayList;

/**
 * Created by Stephanie on 8/09/2016.
 */
public class SupertrumpGame {
    private final int numberOfHumanPlayers = 1;
    private int numberOfPlayers, numberOfAIPlayers, currentPlayer;
    private Deck deck;
    private boolean finished, roundFinished;
    private String currentCategory;
    private Card currentCard;
    private int[] winOrder;
    private int playersFinishedCounter = 0;

    // Default supertrump game constructor
    public SupertrumpGame() {
        numberOfPlayers = 4;
        numberOfAIPlayers = numberOfPlayers - numberOfHumanPlayers;
        currentPlayer = numberOfHumanPlayers + (int)(Math.random() * numberOfPlayers);
        winOrder = new int[4];
    }

    // Supertrump game constructor that takes a custom number of AI players
    public SupertrumpGame(int numberOfAIPlayers) {
        this.numberOfPlayers = numberOfAIPlayers + numberOfHumanPlayers;
        this.numberOfAIPlayers = numberOfAIPlayers;
        currentPlayer = numberOfHumanPlayers + (int)(Math.random() * numberOfPlayers);
        currentCard = new PlayCard();
        winOrder = new int[numberOfPlayers];
    }

    // Creates and stores a deck using the XML deck builder
    public void createDeck() {
        XMLDeckBuilder builder = new XMLDeckBuilder();
        deck = builder.toDeck();
        deck.shuffle();
    }

    // Returns the size of the deck
    public int getDeckSize() {
        return deck.length();
    }

    // Returns an array of 8 cards from the top of the deck
    public ArrayList<Card> dealHand() {
        return deck.dealHand();
    }

    // Returns a single card from the top of the deck
    public Card dealSingleCard() {
        return deck.dealCard();
    }

    // Randomly chooses a dealer (the player who goes first)
    public void selectDealer(int numberOfPlayers) {
        currentPlayer = (int) (Math.random() * numberOfPlayers);
    }

    // Returns the index of the player who's turn it currently is
    public int getCurrentPlayer() {
        return currentPlayer;
    }

    // Sets the index of the current player
    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    // Returns true if the game is finished, false otherwise
    public boolean isFinished() {
        return finished;
    }

    // Sets the round to finished
    public void setRoundFinished(boolean roundFinished) {
        this.roundFinished = roundFinished;
    }

    // Returns true if the round is finished, false otherwise
    public boolean isRoundFinished() {
        return roundFinished;
    }

    // Sets the current category
    public void setCurrentCategory(String currentCategory) {
        this.currentCategory = currentCategory;
    }

    // Returns the current category
    public String getCurrentCategory() {
        return currentCategory;
    }

    // Sets the current card to beat
    public void setCurrentCard(Card currentCard) {
        this.currentCard = currentCard;
    }

    // Returns the current card to beat
    public Card getCurrentCard() {
        return currentCard;
    }

    // Increments the current player counter, effectively stepping to the next player's turn
    public void nextTurn() {
        ++currentPlayer;
        if (currentPlayer >= numberOfPlayers) {
            currentPlayer = 0;
        }
    }

    // Allows a winner to be added to the winner's array
    public void addWinner(int index) {
        winOrder[playersFinishedCounter] = index;
        ++playersFinishedCounter;

        if (playersFinishedCounter == numberOfAIPlayers) {
            finished = true;
        }
    }

    // Returns the winner at a particular index
    public int getWinner(int index) {
        return winOrder[index];
    }

}
