import cards.Card;
import cards.PlayCard;
import decks.Deck;
import decks.DeckBuilder;
import decks.XMLDeckBuilder;

import java.util.ArrayList;

/**
 * Created by Stephanie on 8/09/2016.
 */
public class SupertrumpGame {
    private final int numberOfHumanPlayers = 1;
    private int numberOfPlayers, numberOfAIPlayers, currentPlayer;
    private Deck deck;
    private boolean finished;
    private int currentTurn;
    private String currentCategory;
    private Card currentCard;

    public SupertrumpGame() {
        numberOfPlayers = 4;
        numberOfAIPlayers = numberOfPlayers - numberOfHumanPlayers;
        currentPlayer = numberOfHumanPlayers + (int)(Math.random() * numberOfPlayers);
    }

    public SupertrumpGame(int numberOfAIPlayers) {
        this.numberOfPlayers = numberOfAIPlayers + numberOfHumanPlayers;
        this.numberOfAIPlayers = numberOfAIPlayers;
        currentPlayer = numberOfHumanPlayers + (int)(Math.random() * numberOfPlayers);
        currentCard = new PlayCard();
    }

    public void createDeck() {
        XMLDeckBuilder builder = new XMLDeckBuilder();
        deck = builder.toDeck();
        deck.shuffle();
    }

    public ArrayList<Card> dealHand() {
        return deck.dealHand();
    }

    public int selectDealer(int numberOfPlayers) {
        currentTurn = (int) (Math.random() * numberOfPlayers);
        return currentTurn;
    }

    public int getCurrentTurn() {
        return currentTurn;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public boolean isFinished() {
        return finished;
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
}
