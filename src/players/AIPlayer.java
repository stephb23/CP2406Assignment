package players;

import cards.*;
import enums.Cleavage;
import enums.CrustalAbundance;
import enums.EconomicValue;

/**
 * Created by Stephanie on 8/09/2016.
 *
 * This class provides the means to create AIPlayers and provides their "brains" - all AI logic is contained within
 * the playCard and playFirstCard methods (with the support of some helper methods).
 *
 */

public class AIPlayer extends Player {

    public AIPlayer(String name) {
        super(name);
    }

    // Logic for playing a card that leads out the game or round
    public Card playFirstCard(String currentCategory) {
        Card chosenCard = getStartingCard(currentCategory);

        // If the chosen card isn't null, remove it
        if (chosenCard != null) {
            removeCard(locationOf(chosenCard.getCardName()));
        } else if (getHandSize() == 1) {
            // If there's only one card left, play it to win the game!
            chosenCard = getCardAt(0);
            removeCard(0);
        }

        // Return the chosen card
        return chosenCard;
    }

    // Logic for playing a card mid-round
    public Card playCard(Card currentCard, String currentCategory) {
        Card chosenCard = new Card();
        PlayCard currentPlayCard;

        // Check whether the hand contains the round-winning combo, if so return The Geophysicist
        if (locationOf("The Geophysicist") != -1 && locationOf("Magnetite") != -1) {
            chosenCard = getCardAt(locationOf("The Geophysicist"));
            return chosenCard;
        }

        // If the card to beat is a play card, choose the card to beat it with.
        if (currentCard.getType().equals("play")) {
            currentPlayCard = (PlayCard) currentCard;

            if (currentCategory.equals("hardness")) {
                chosenCard = chooseBestHardnessCard(currentPlayCard);
            } else if (currentCategory.equals("specific gravity")) {
                chosenCard = chooseBestSpecificGravityCard(currentPlayCard);
            } else if (currentCategory.equals("cleavage")) {
                chosenCard = chooseBestCleavageCard(currentPlayCard);
            } else if (currentCategory.equals("crustal abundance")) {
                chosenCard = chooseBestCrustalAbundanceCard(currentPlayCard);
            } else if (currentCategory.equals("economic value")) {
                chosenCard = chooseBestEconomicValueCard(currentPlayCard);
            } else {
                chosenCard = null;
            }

            if (chosenCard == null) {
                chosenCard = getTrumpCard();
            }
        } else if (currentCard.getType().equals("trump")) {
            // If the card to beat is a trump card, act as if leading out a round.
            chosenCard = getStartingCard(currentCategory);
        }

        // If the chosen card isn't null, remove it from the hand
        if (chosenCard != null) {
            removeCard(locationOf(chosenCard.getCardName()));
        }

        // Return the chosen card
        return chosenCard;
    }

    // Logic for identifying the best starting card.
    private Card getStartingCard(String currentCategory) {
        Card chosenCard;

        // Create a null card (all values initialized to lowest values)
        PlayCard nullCard = new PlayCard();

        // Find the lowest card in the chosen category
        if (currentCategory.equals("hardness")) {
            chosenCard = chooseBestHardnessCard(nullCard);
        } else if (currentCategory.equals("specific gravity")) {
            /* If the category is specific gravity, there's a chance that The Geophysicist has been played.
            If the hand contains Magnetite, play it
            Won't result in a winning combo every time, but will result in a winning combo if
            the AIPlayer held and played The Geophysicist.
            */
            if (locationOf("Magnetite") != -1) {
                chosenCard = getCardAt(locationOf("Magnetite"));
            } else {
                chosenCard = chooseBestSpecificGravityCard(nullCard);
            }
        } else if (currentCategory.equals("cleavage")) {
            chosenCard = chooseBestCleavageCard(nullCard);
        } else if (currentCategory.equals("crustal abundance")) {
            chosenCard = chooseBestCrustalAbundanceCard(nullCard);
        } else if (currentCategory.equals("economic value")) {
            chosenCard = chooseBestEconomicValueCard(nullCard);
        } else {
            chosenCard = null;
        }

        // If no suitable play card was found, attempt to find a trump card
        if (chosenCard == null) {
            chosenCard = getTrumpCard();
        }

        // If no trump card was found, pass.
        if (chosenCard == null) {
            pass();
        }

        // Return the chosen card (or null if the player passed).
        return chosenCard;
    }

    // Choose the category that is most beneficial to the player
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

        // Sum the hardness, specific gravity, etc of each card held and count the number of play cards held
        for (int i = 0; i < getHandSize(); ++i) {
            Card card = getPlayerHand().get(i);
            if (card.getType().equals("play")) {
                PlayCard playCard = (PlayCard) card;
                hardnessTotal += playCard.getHardnessAsDouble();
                specificGravityTotal += playCard.getSpecificGravityAsDouble();
                cleavageTotal += playCard.getCleavageAsInt();
                crustalAbundanceTotal += playCard.getCrustalAbundanceAsInt();
                economicValueTotal += playCard.getEconomicValueAsInt();
                ++playCardCounter;
            }
        }

        // Find an average hardness, specific gravity, etc. as a percentage
        double hardnessPercentage = (hardnessTotal/playCardCounter)/maxHardness * 100;
        double specificGravityPercentage = (specificGravityTotal/playCardCounter)/maxSpecificGravity * 100;
        double cleavagePercentage = (cleavageTotal/playCardCounter)/maxCleavage * 100;
        double crustalAbundancePercentage = (crustalAbundanceTotal/playCardCounter)/maxCrustalAbundance * 100;
        double economicValuePercentage = (economicValueTotal/playCardCounter)/maxEconomicValue * 100;

        // Gather all calculated percentages
        double[] allPercentages = {hardnessPercentage, specificGravityPercentage, cleavagePercentage,
                crustalAbundancePercentage, economicValuePercentage};

        // Arbitrarily set the maximum to being the first percentage
        double currentMax = hardnessPercentage;
        int indexOfMax = 0;

        // Iterate through the percentages array to find the maximum average
        for (int i = 1; i < allPercentages.length; ++i) {
            if (allPercentages[i] > currentMax) {
                currentMax = allPercentages[i];
                indexOfMax = i;
            }
        }

        // Relate the maximum percentage to a String category choice
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
                chosenCategory = "crustal abundance";
                break;
            case 4:
                chosenCategory = "economic value";
                break;
            default:
                chosenCategory = "hardness";
                break;
        }

        // Return the chosen category
        return chosenCategory;
    }

    // Choose the 'best' hardness card
    private Card chooseBestHardnessCard(PlayCard currentCard) {
        PlayCard playCard;
        double currentCardHardness = currentCard.getHardnessAsDouble();
        double bestHardness = 10;
        int indexOfBestHardness = -1;

        // Find the card with the lowest hardness that is still greater than the card to beat
        for (int i = 0; i < getHandSize(); ++i) {
            if (getCardAt(i).getType().equals("play")) {
                playCard = (PlayCard) getCardAt(i);
                if (playCard.getHardnessAsDouble() > currentCardHardness && playCard.getHardnessAsDouble() < bestHardness) {
                    bestHardness = playCard.getHardnessAsDouble();
                    indexOfBestHardness = i;
                }
            }
        }

        // If no card is found, let play card equal null; otherwise create a play card based on the index of the maximum
        if (indexOfBestHardness == -1) {
            playCard = null;
        } else {
            playCard = (PlayCard) getPlayerHand().get(indexOfBestHardness);
        }

        // Return the selected PlayCard
        return playCard;
    }

    // Choose the card with the 'best' specific gravity
    private Card chooseBestSpecificGravityCard(PlayCard currentCard) {
        PlayCard playCard;
        double currentCardSpecificGravity = currentCard.getSpecificGravityAsDouble();
        double bestSpecificGravity = 19.3;
        int indexOfBestSpecificGravity = -1;

        // Find the card with the lowest specific gravity that is greater than that of the card to beat
        for (int i = 0; i < getHandSize(); ++i) {
            if (getCardAt(i).getType().equals("play")) {
                playCard = (PlayCard) getCardAt(i);
                if (playCard.getSpecificGravityAsDouble() > currentCardSpecificGravity && playCard.getSpecificGravityAsDouble() < bestSpecificGravity) {
                    bestSpecificGravity = playCard.getSpecificGravityAsDouble();
                    indexOfBestSpecificGravity = i;
                }
            }
        }

        // If no such card is found, return null; otherwise return the card.
        if (indexOfBestSpecificGravity == -1) {
            playCard = null;
        } else {
            playCard = (PlayCard) getPlayerHand().get(indexOfBestSpecificGravity);
        }

        return playCard;
    }

    // Choose the 'best' card for cleavage category
    private Card chooseBestCleavageCard(PlayCard currentCard) {
        PlayCard playCard;
        double currentCardCleavage = currentCard.getCleavageAsInt();
        int bestCleavage = Cleavage.PERFECT6.ordinal();
        int indexOfBestCleavage = -1;

        // Find the card with the lowest cleavage that is greater than the card to beat
        for (int i = 0; i < getHandSize(); ++i) {
            if (getCardAt(i).getType().equals("play")) {
                playCard = (PlayCard) getCardAt(i);
                if (playCard.getCleavageAsInt() > currentCardCleavage && playCard.getCleavageAsInt() < bestCleavage) {
                    bestCleavage = playCard.getCleavageAsInt();
                    indexOfBestCleavage = i;
                }
            }
        }

        // If no such card is found, return null; else return the card chosen.
        if (indexOfBestCleavage == -1) {
            playCard = null;
        } else {
            playCard = (PlayCard) getPlayerHand().get(indexOfBestCleavage);
        }

        return playCard;
    }

    // Choose the card with the 'best' crustal abundance.
    private Card chooseBestCrustalAbundanceCard(PlayCard currentCard) {
        PlayCard playCard;
        int currentCardHardness = currentCard.getCrustalAbundanceAsInt();
        int bestCrustalAbundance = CrustalAbundance.VERY_HIGH.ordinal();
        int indexOfBestCrustalAbundance = -1;

        // Find the card with the lowest crustal abundance that is still greater than the current card to beat.
        for (int i = 0; i < getHandSize(); ++i) {
            if (getCardAt(i).getType().equals("play")) {
                playCard = (PlayCard) getCardAt(i);
                if (playCard.getCrustalAbundanceAsInt() > currentCardHardness && playCard.getCrustalAbundanceAsInt() < bestCrustalAbundance) {
                    bestCrustalAbundance = playCard.getCrustalAbundanceAsInt();
                    indexOfBestCrustalAbundance = i;
                }
            }
        }

        // If no such card is found, return null; otherwise return the chosen card.
        if (indexOfBestCrustalAbundance == -1) {
            playCard = null;
        } else {
            playCard = (PlayCard) getPlayerHand().get(indexOfBestCrustalAbundance);
        }

        return playCard;
    }

    // Choose the 'best' card for the economic value trump suit
    private Card chooseBestEconomicValueCard(PlayCard currentCard) {
        PlayCard playCard;
        double currentCardEconomicValue = currentCard.getEconomicValueAsInt();
        int bestEconomicValue = EconomicValue.I_AM_RICH.ordinal();
        int indexOfBestEconomicValue = -1;

        // Find the card with the lowest economic value that is greater than the economic value of the card to beat.
        for (int i = 0; i < getHandSize(); ++i) {
            if (getCardAt(i).getType().equals("play")) {
                playCard = (PlayCard) getCardAt(i);
                if (playCard.getEconomicValueAsInt() > currentCardEconomicValue && playCard.getEconomicValueAsInt() < bestEconomicValue) {
                    bestEconomicValue = playCard.getEconomicValueAsInt();
                    indexOfBestEconomicValue = i;
                }
            }
        }

        // If no such card is found, return null.. otherwise return the card.
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

}
