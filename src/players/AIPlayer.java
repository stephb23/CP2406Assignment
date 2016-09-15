package players;

import cards.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by Stephanie on 8/09/2016.
 */
public class AIPlayer extends Player {

    public AIPlayer(String name) {
        super(name);
    }

    public void playCard(Card currentCard) {

    }

    public Card playCard(Card currentCard, String currentCategory) {
        Card chosenCard = new Card();
        PlayCard currentPlayCard;

        if (currentCard.getType().equals("play")) {
            currentPlayCard = (PlayCard) currentCard;

            if (currentCategory.equals("hardness")) {
                chosenCard = chooseBestHardnessCard(currentPlayCard);
            } else if (currentCategory.equals("specific gravity")) {
                chosenCard = chooseBestSpecificGravityCard(currentPlayCard);
            } else if (currentCategory.equals("cleavage")) {
                chosenCard = chooseBestCleavageCard(currentPlayCard);
            } else if (currentCategory.equals("crustal abundances")) {
                chosenCard = chooseBestCrustalAbundancesCard(currentPlayCard);
            } else if (currentCategory.equals("economic value")) {
                chosenCard = chooseBestEconomicValueCard(currentPlayCard);
            } else {
                chosenCard = null;
            }

            if (chosenCard == null) {
                chosenCard = getTrumpCard();
            }
        }

        if (currentCard.getType().equals("trump")) {
            chosenCard = playFirstCard(currentCategory);
        }

        return chosenCard;
    }

    public Card playFirstCard(String currentCategory) {
        Card chosenCard = new Card();

        return chosenCard;
    }

    public String chooseCategory() {
        String chosenCategory;

        double hardnessTotal = 0;
        double specificGravityTotal = 0;
        double cleavageTotal = 0;
        double crustalAbundanceTotal = 0;
        double economicValueTotal = 0;

        double maxHardness = 10;
        double maxSpecificGravity = 7; // note this isn't actually the max, but anything above this appears to be an outlier
        double maxCleavage = Cleavage.PERFECT6.ordinal();
        double maxCrustalAbundance = CrustalAbundance.VERY_HIGH.ordinal();
        double maxEconomicValue = EconomicValue.I_AM_RICH.ordinal();

        int playCardCounter = 0;

        for (int i = 0; i < getHandSize(); ++i) {
            Card card = getPlayerHand().get(i);
            if (card.getType().equals("play")) {
                PlayCard playCard = (PlayCard) card;
                hardnessTotal += playCard.getHardnessAsDouble();
                specificGravityTotal += playCard.getSpecificGravityAsDouble();
                cleavageTotal += playCard.getCleavageAsInt();
                crustalAbundanceTotal += playCard.getCrustalAbundancesAsInt();
                economicValueTotal += playCard.getEconomicValueAsInt();
                ++playCardCounter;
            }
        }
        System.out.println("Hardness total: " + hardnessTotal);

        double hardnessPercentage = (hardnessTotal/playCardCounter)/maxHardness * 100;
        double specificGravityPercentage = (specificGravityTotal/playCardCounter)/maxSpecificGravity * 100;
        double cleavagePercentage = (cleavageTotal/playCardCounter)/maxCleavage * 100;
        double crustalAbundancePercentage = (crustalAbundanceTotal/playCardCounter)/maxCrustalAbundance * 100;
        double economicValuePercentage = (economicValueTotal/playCardCounter)/maxEconomicValue * 100;

        double[] allPercentages = {hardnessPercentage, specificGravityPercentage, cleavagePercentage,
                crustalAbundancePercentage, economicValuePercentage};

        System.out.println("Hardness %: " + hardnessPercentage);
        System.out.println("Specific gravity %: " + specificGravityPercentage);
        System.out.println("Cleavage %: " + cleavagePercentage);
        System.out.println("Crustal Abundances %: " + crustalAbundancePercentage);
        System.out.println("Economic: %: " + economicValuePercentage);

        double currentMax = hardnessPercentage;
        int indexOfMax = 0;

        for (int i = 1; i < allPercentages.length; ++i) {
            if (allPercentages[i] > currentMax) {
                currentMax = allPercentages[i];
                indexOfMax = i;
            }
        }

        switch (indexOfMax) {
            case 0:
                chosenCategory = "hardness";
                break;
            case 1:
                chosenCategory = "specific gravity";
                break;
            case 2:
                chosenCategory = "cleavage";
                break;
            case 3:
                chosenCategory = "crustal abundances";
                break;
            case 4:
                chosenCategory = "economic value";
                break;
            default:
                chosenCategory = null;
                break;
        }

        return chosenCategory;
    }

    private Card chooseBestHardnessCard(PlayCard currentCard) {
        PlayCard playCard;
        double currentCardHardness = currentCard.getHardnessAsDouble();
        double bestHardness = 10;
        int indexOfBestHardness = -1;

        for (int i = 0; i < getHandSize(); ++i) {
            if (getCardAt(i).getType().equals("play")) {
                playCard = (PlayCard) getCardAt(i);
                if (playCard.getHardnessAsDouble() > currentCardHardness && playCard.getHardnessAsDouble() < bestHardness) {
                    bestHardness = playCard.getHardnessAsDouble();
                    indexOfBestHardness = i;
                }
            }
        }

        if (indexOfBestHardness == -1) {
            playCard = null;
        } else {
            playCard = (PlayCard) getPlayerHand().get(indexOfBestHardness);
        }

        return playCard;
    }

    private Card chooseBestSpecificGravityCard(PlayCard currentCard) {
        PlayCard playCard;
        double currentCardSpecificGravity = currentCard.getSpecificGravityAsDouble();
        double bestSpecificGravity = 19.3;
        int indexOfBestSpecificGravity = -1;

        for (int i = 0; i < getHandSize(); ++i) {
            if (getCardAt(i).getType().equals("play")) {
                playCard = (PlayCard) getCardAt(i);
                if (playCard.getSpecificGravityAsDouble() > currentCardSpecificGravity && playCard.getSpecificGravityAsDouble() < bestSpecificGravity) {
                    bestSpecificGravity = playCard.getSpecificGravityAsDouble();
                    indexOfBestSpecificGravity = i;
                }
            }
        }

        if (indexOfBestSpecificGravity == -1) {
            playCard = null;
        } else {
            playCard = (PlayCard) getPlayerHand().get(indexOfBestSpecificGravity);
        }

        return playCard;
    }

    private Card chooseBestCleavageCard(PlayCard currentCard) {
        PlayCard playCard;
        double currentCardCleavage = currentCard.getCleavageAsInt();
        int bestCleavage = Cleavage.PERFECT6.ordinal();
        int indexOfBestCleavage = -1;

        for (int i = 0; i < getHandSize(); ++i) {
            if (getCardAt(i).getType().equals("play")) {
                playCard = (PlayCard) getCardAt(i);
                if (playCard.getCleavageAsInt() > currentCardCleavage && playCard.getCleavageAsInt() < bestCleavage) {
                    bestCleavage = playCard.getCleavageAsInt();
                    indexOfBestCleavage = i;
                }
            }
        }

        if (indexOfBestCleavage == -1) {
            playCard = null;
        } else {
            playCard = (PlayCard) getPlayerHand().get(indexOfBestCleavage);
        }

        return playCard;
    }

    private Card chooseBestCrustalAbundancesCard(PlayCard currentCard) {
        PlayCard playCard;
        int currentCardHardness = currentCard.getCrustalAbundancesAsInt();
        int bestCrustalAbundances = CrustalAbundance.VERY_HIGH.ordinal();
        int indexOfBestCrustalAbundances = -1;

        for (int i = 0; i < getHandSize(); ++i) {
            if (getCardAt(i).getType().equals("play")) {
                playCard = (PlayCard) getCardAt(i);
                if (playCard.getCrustalAbundancesAsInt() > currentCardHardness && playCard.getCrustalAbundancesAsInt() < bestCrustalAbundances) {
                    bestCrustalAbundances = playCard.getCrustalAbundancesAsInt();
                    indexOfBestCrustalAbundances = i;
                }
            }
        }

        if (indexOfBestCrustalAbundances == -1) {
            playCard = null;
        } else {
            playCard = (PlayCard) getPlayerHand().get(indexOfBestCrustalAbundances);
        }

        return playCard;
    }

    private Card chooseBestEconomicValueCard(PlayCard currentCard) {
        PlayCard playCard;
        double currentCardEconomicValue = currentCard.getEconomicValueAsInt();
        int bestEconomicValue = EconomicValue.I_AM_RICH.ordinal();
        int indexOfBestEconomicValue = -1;

        for (int i = 0; i < getHandSize(); ++i) {
            if (getCardAt(i).getType().equals("play")) {
                playCard = (PlayCard) getCardAt(i);
                if (playCard.getHardnessAsDouble() > currentCardEconomicValue && playCard.getHardnessAsDouble() < bestEconomicValue) {
                    bestEconomicValue = playCard.getEconomicValueAsInt();
                    indexOfBestEconomicValue = i;
                }
            }
        }

        if (indexOfBestEconomicValue == -1) {
            playCard = null;
        } else {
            playCard = (PlayCard) getPlayerHand().get(indexOfBestEconomicValue);
        }

        return playCard;
    }

    private Card getTrumpCard() {
        TrumpCard trumpCard;

        for (int i = 0; i < getHandSize(); ++i) {
            if (getCardAt(i).getType().equals("trump")) {
                trumpCard = (TrumpCard) getCardAt(i);
                return trumpCard;
            }
        }

        return null;
    }

    // for debugging ONLY
    // TODO: DELETE THIS
    public void viewAllCards() {
        System.out.println("You have the following cards in your hand");
        for (int i = 1; i <= getHandSize(); ++i) {
            System.out.print(i + ".\t");
            Card card = getCardAt(i -1);
            if (card.getType().equals("play")) {
                System.out.println(((PlayCard) card).toShortString());
            } else if (card.getType().equals("trump")) {
                System.out.println(((TrumpCard) card).toShortString());
            }
        }
    }

}
