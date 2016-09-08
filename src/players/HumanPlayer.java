package players;

/**
 * Created by Stephanie on 6/09/2016.
 */
public class HumanPlayer extends Player{

    public HumanPlayer(String name){
        super(name);
    }

    public void viewAllCards() {
        System.out.println("You have the following cards in your hand");
        for (int i = 1; i <= getHandSize(); ++i) {
            System.out.print(i + ".\t");
            System.out.println(getCardAt(i -1).getCardName());
        }
    }
}
