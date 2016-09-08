/**
 * Created by Stephanie on 8/09/2016.
 */
public class SupertrumpGame {
    private final int numberOfHumanPlayers = 1;
    private int numberOfPlayers, numberOfAIPlayers, currentPlayer;
    private boolean finished;

    public SupertrumpGame() {
        numberOfPlayers = 4;
        numberOfAIPlayers = numberOfPlayers - numberOfHumanPlayers;
        currentPlayer = numberOfHumanPlayers + (int)(Math.random() * numberOfPlayers);
    }

    public SupertrumpGame(int numberOfAIPlayers) {
        this.numberOfPlayers = numberOfAIPlayers + numberOfHumanPlayers;
        this.numberOfAIPlayers = numberOfAIPlayers;
        currentPlayer = numberOfHumanPlayers + (int)(Math.random() * numberOfPlayers);
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public boolean isFinished() {
        return this.finished;
    }
}
