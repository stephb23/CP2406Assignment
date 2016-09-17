package players;

import cards.Card;

import java.util.ArrayList;

/**
 * Created by Stephanie on 6/09/2016.
 */
public class Player {
    private String name;
    private ArrayList<Card> playerHand;
    private boolean inactive;
    private boolean finished;

    public Player(String name) {
        this.name = name;
        playerHand = new ArrayList<>();
        inactive = false;
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

    public boolean hasFinished() {
        if (getHandSize() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public void pass(){
        this.inactive = true;
    }

    public void activate() {
        if (!finished) {
            this.inactive = false;
        }
    }

    public boolean getInactive() {
        return inactive;
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

    public void pickUpCard(Card card) {
        playerHand.add(card);
    }

    public void finish() {
        finished = true;
        inactive = true;
    }

    public boolean isFinished(){
        return finished;
    }

    @Override
    public String toString(){
        String playerString = "Player's name is " + name;
        return playerString;
    }

}
