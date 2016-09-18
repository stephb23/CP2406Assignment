package players;

import cards.Card;

import java.util.ArrayList;

/**
 * Created by Stephanie on 6/09/2016.
 *
 * This acts as the base class for all players, providing methods and variables that both human and AI players require
 *
 */

public class Player {
    private String name;
    private ArrayList<Card> playerHand;
    private boolean inactive;
    private boolean finished;

    // Constructor
    public Player(String name) {
        this.name = name;
        playerHand = new ArrayList<>();
        inactive = false;
    }

    // This allows cards to be dealt to the player
    public void setPlayerHand(ArrayList<Card> playerHand) {
        this.playerHand = playerHand;
    }

    // This allows the player hand to be retrieved by classes that extend from Player only
    protected ArrayList<Card> getPlayerHand() {
        return playerHand;
    }

    // This allows players to remove a card from their hand when they play that card.
    protected void removeCard(int index) {
        playerHand.remove(index);
    }

    // This function allows the players to pass.
    public void pass(){
        this.inactive = true;
    }

    // This activates a player who has NOT finished the game
    // Used when a new round starts or trump card is thrown
    public void activate() {
        if (!finished) {
            this.inactive = false;
        }
    }

    // Returns true if a player has passed or is finished
    public boolean isInactive() {
        return inactive;
    }

    // Returns the size of the player's hand
    public int getHandSize() {
        return playerHand.size();
    }

    // Returns the name of the player
    public String getName() {
        return name;
    }

    // Returns the card at a specific location
    public Card getCardAt(int index) {
        return playerHand.get(index);
    }

    /* Attempts to find a card by name in the player's hand. If it finds the card, it returns it's index. Otherwise,
    it returns -1 */
    protected int locationOf (String cardName) {
        for (int i = 0; i < getHandSize(); ++i) {
            if (playerHand.get(i).getCardName().equals(cardName)) {
                return i;
            }
        }
        return -1;
    }

    // Adds the card picked up to the player's hand when they pick up from a deck.
    public void pickUpCard(Card card) {
        playerHand.add(card);
    }

    // Called when the player's hand reaches 0.
    public void finish() {
        finished = true;
        inactive = true;
    }

    // Returns true if the player has finished the game (has 0 cards left)
    public boolean isFinished(){
        return finished;
    }

    // Very simple toString() that simply returns the player's name.
    @Override
    public String toString(){
        return "Player's name is " + name;
    }

}
