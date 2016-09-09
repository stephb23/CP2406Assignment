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

    public void setHand(ArrayList<Card> playerHand) {
        this.playerHand = playerHand;
    }

    public void playCard(int index) {
        playerHand.remove(index);
    }

    public void setPassed(boolean hasPassed){
        this.hasPassed = hasPassed;
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

    @Override
    public String toString(){
        String playerString = "Player's name is " + name;
        return playerString;
    }

}
