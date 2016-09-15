package players;

import cards.Card;

import java.util.ArrayList;

/**
 * Created by Stephanie on 6/09/2016.
 */
public class Player {
    private String name;
    private ArrayList<Card> playerHand;
    private boolean hasPassed;

    public Player(String name) {
        this.name = name;
        playerHand = new ArrayList<>();
        hasPassed = false;
    }

    public void setPlayerHand(ArrayList<Card> playerHand) {
        this.playerHand = playerHand;
    }

    protected ArrayList<Card> getPlayerHand() {
        return playerHand;
    }

    protected void removeCard(int index) {
        playerHand.remove(index);
    }

    public void pass(){
        this.hasPassed = true;
    }

    public void activate() {
        this.hasPassed = false;
    }

    public boolean getPassed() {
        return hasPassed;
    }

    public int getHandSize() {
        return playerHand.size();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Card getCardAt(int index) {
        return playerHand.get(index);
    }

    protected int locationOf (String cardName) {
        for (int i = 0; i < getHandSize(); ++i) {
            if (playerHand.get(i).getCardName().equals(cardName)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public String toString(){
        String playerString = "Player's name is " + name;
        return playerString;
    }

}
