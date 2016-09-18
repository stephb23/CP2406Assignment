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

    public SupertrumpGame() {
        numberOfPlayers = 4;
        numberOfAIPlayers = numberOfPlayers - numberOfHumanPlayers;
        currentPlayer = numberOfHumanPlayers + (int)(Math.random() * numberOfPlayers);
        winOrder = new int[4];
    }

    public SupertrumpGame(int numberOfAIPlayers) {
        this.numberOfPlayers = numberOfAIPlayers + numberOfHumanPlayers;
        this.numberOfAIPlayers = numberOfAIPlayers;
        currentPlayer = numberOfHumanPlayers + (int)(Math.random() * numberOfPlayers);
        currentCard = new PlayCard();
        winOrder = new int[numberOfPlayers];
    }

    public void createDeck() {
        XMLDeckBuilder builder = new XMLDeckBuilder();
        deck = builder.toDeck();
        deck.shuffle();
    }

    public int getDeckSize() {
        return deck.length();
    }

    public ArrayList<Card> dealHand() {
        return deck.dealHand();
    }

    public Card dealSingleCard() {
        return deck.dealCard();
    }

    public void selectDealer(int numberOfPlayers) {
        currentPlayer = (int) (Math.random() * numberOfPlayers);
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setRoundFinished(boolean roundFinished) {
        this.roundFinished = roundFinished;
    }

    public boolean isRoundFinished() {
        return roundFinished;
    }

    public void setCurrentCategory(String currentCategory) {
        this.currentCategory = currentCategory;
    }

    public String getCurrentCategory() {
        return currentCategory;
    }

    public void setCurrentCard(Card currentCard) {
        this.currentCard = currentCard;
    }

    public Card getCurrentCard() {
        return currentCard;
    }

    public void nextTurn() {
        ++currentPlayer;
        if (currentPlayer >= numberOfPlayers) {
            currentPlayer = 0;
        }
    }

    public void addWinner(int index) {
        winOrder[playersFinishedCounter] = index;
        ++playersFinishedCounter;

        if (playersFinishedCounter == numberOfAIPlayers) {
            finished = true;
        }
    }

    public int getWinner(int index) {
        return winOrder[index];
    }
}
