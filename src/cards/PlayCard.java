package cards;

import enums.Cleavage;
import enums.CrustalAbundance;
import enums.EconomicValue;

import java.util.ArrayList;

/**
 * Created by Stephanie on 2/09/2016.
 *
 * This class is used to create the "play" type cards for the Supertrump Game.
 * It provides means to get a card's value in any category, either as a string or as a comparable numeric
 * value.
 *
 */

public class PlayCard extends Card {
    private final String TYPE_STRING = "play";
    private String chemistry, classification, crystalSystem;
    private ArrayList<String> occurrences = new ArrayList<>();
    private String hardness, specificGravity, cleavage, crustalAbundances, economicValue;

    // Default constructor - sets minimum values for each category.
    public PlayCard() {
        this.hardness = "0";
        this.specificGravity = "0";
        this.cleavage = "none";
        this.crustalAbundances = "ultratrace";
        this.economicValue = "low";
        setType(TYPE_STRING);
    }

    //  Constructor for card name only, sets everything else to minimum values.
    public PlayCard(String cardName) {
        super(cardName);
        this.hardness = "0";
        this.specificGravity = "0";
        this.cleavage = "none";
        this.crustalAbundances = "ultratrace";
        this.economicValue = "low";
        setType(TYPE_STRING);
    }

    // Constructor with all PlayCard parameters
    public PlayCard(String imageFile, String imageName, String cardName, String chemistry, String classification,
                    String crystalSystem, ArrayList<String> occurrences, String hardness, String specificGravity,
                    String cleavage, String crystalAbundances, String economicValue) {
        super(imageFile, imageName, cardName);
        setType(TYPE_STRING);
        this.chemistry = chemistry;
        this.classification = classification;
        this.crystalSystem = crystalSystem;
        this.occurrences = occurrences;
        this.hardness = hardness;
        this.specificGravity = specificGravity;
        this.cleavage = cleavage;
        this.crustalAbundances = crystalAbundances;
        this.economicValue = economicValue;
    }

    // Getters for card information - no setters required
    public String getChemistry() {
        return chemistry;
    }

    public String getClassification() {
        return classification;
    }

    public String getCrystalSystem() {
        return crystalSystem;
    }

    public ArrayList<String> getOccurrences() {
        return occurrences;
    }

    // Getters for hardness trump category
    public String getHardness() {
        return hardness;
    }

    public Double getHardnessAsDouble() {
        String[] hardnessRange;
        // Handles different formats that the hardness range was in.
        if (hardness.contains(" - ")) {
            hardnessRange = hardness.split(" - ");
        } else if (hardness.contains("-")) {
            hardnessRange = hardness.split("-");
        } else {
            hardnessRange = hardness.split(" ");
        }

        return Double.parseDouble(hardnessRange[hardnessRange.length - 1]);
    }

    // Getters for specific gravity trump category
    public String getSpecificGravity() {
        return specificGravity;
    }

    public Double getSpecificGravityAsDouble() {
        String[] specificGravityRange;
        // Handles different formats that the hardness range was in.
        if (specificGravity.contains(" - ")) {
            specificGravityRange = specificGravity.split(" - ");
        } else if (specificGravity.contains("-")) {
            specificGravityRange = specificGravity.split("-");
        } else {
            specificGravityRange = specificGravity.split(" ");
        }

        return Double.parseDouble(specificGravityRange[specificGravityRange.length - 1]);
    }

    // Getters for cleavage trump category.
    public String getCleavage() {
        return cleavage;
    }

    public int getCleavageAsInt() {
        int cleavageRank;

        if (cleavage.equals("none")) {
            cleavageRank = Cleavage.NONE.ordinal();
        } else if (cleavage.equals("poor/none")) {
            cleavageRank = Cleavage.POOR_NONE.ordinal();
        } else if (cleavage.equals("1 poor")) {
            cleavageRank = Cleavage.POOR1.ordinal();
        } else if (cleavage.equals("2 poor")) {
            cleavageRank = Cleavage.POOR2.ordinal();
        } else if (cleavage.equals("1 good")) {
            cleavageRank = Cleavage.GOOD1.ordinal();
        } else if (cleavage.equals("1 good, 1 poor")) {
            cleavageRank = Cleavage.GOOD1_POOR1.ordinal();
        } else if (cleavage.equals("2 good")) {
            cleavageRank = Cleavage.GOOD2.ordinal();
        } else if (cleavage.equals("3 good")) {
            cleavageRank = Cleavage.GOOD3.ordinal();
        } else if (cleavage.equals("1 perfect")) {
            cleavageRank = Cleavage.PERFECT1.ordinal();
        } else if (cleavage.equals("1 perfect, 1 good")) {
            cleavageRank = Cleavage.PERFECT1_GOOD1.ordinal();
        } else if (cleavage.equals("1 perfect, 2 good")) {
            cleavageRank = Cleavage.PERFECT1_GOOD2.ordinal();
        } else if (cleavage.equals("2 perfect, 1 good")) {
            cleavageRank = Cleavage.PERFECT2_GOOD1.ordinal();
        } else if (cleavage.equals("3 perfect")) {
            cleavageRank = Cleavage.PERFECT3.ordinal();
        } else if (cleavage.equals("4 perfect")) {
            cleavageRank = Cleavage.PERFECT4.ordinal();
        } else if (cleavage.equals("6 perfect")) {
            cleavageRank = Cleavage.PERFECT6.ordinal();
        } else {
            cleavageRank = -1;
        }

        return cleavageRank;
    }

    // Getters for crustal abundances trump category
    public String getCrustalAbundances(){
        return crustalAbundances;
    }

    public int getCrustalAbundancesAsInt() {
        int crustalAbundancesRank;
        if (crustalAbundances.equals("ultratrace")) {
            crustalAbundancesRank = CrustalAbundance.ULTRATRACE.ordinal();
        } else if (crustalAbundances.equals("trace")) {
            crustalAbundancesRank = CrustalAbundance.TRACE.ordinal();
        } else if (crustalAbundances.equals("low")) {
            crustalAbundancesRank = CrustalAbundance.LOW.ordinal();
        } else if (crustalAbundances.equals("moderate")) {
            crustalAbundancesRank = CrustalAbundance.MODERATE.ordinal();
        } else if (crustalAbundances.equals("high")) {
            crustalAbundancesRank = CrustalAbundance.HIGH.ordinal();
        } else if (crustalAbundances.equals("very high")) {
            crustalAbundancesRank = CrustalAbundance.VERY_HIGH.ordinal();
        } else {
            crustalAbundancesRank = -1;
        }
        return crustalAbundancesRank;
    }

    // Getters for economic value
    public String getEconomicValue() {
        return economicValue;
    }

    public int getEconomicValueAsInt() {
        int economicValueRank;

        if (economicValue.equals("trivial")) {
            economicValueRank = EconomicValue.TRIVIAL.ordinal();
        } else if (economicValue.equals("low")) {
            economicValueRank = EconomicValue.LOW.ordinal();
        } else if (economicValue.equals("moderate")) {
            economicValueRank = EconomicValue.MODERATE.ordinal();
        } else if (economicValue.equals("high")) {
            economicValueRank = EconomicValue.HIGH.ordinal();
        } else if (economicValue.equals("very high")) {
            economicValueRank = EconomicValue.VERY_HIGH.ordinal();
        } else if (economicValue.equals("I'm rich!")) {
            economicValueRank = EconomicValue.I_AM_RICH.ordinal();
        } else {
            economicValueRank = -1;
        }
        return economicValueRank;
    }

    // Overriden toString() method
    @Override
    public String toString() {
        String playCardString = super.toString();
        playCardString += ", Chemistry = " + chemistry + ", Classification = " + classification + ", Crystal System = "
                + crystalSystem + ", Occurences = " + occurrences.toString() + ",\nHardness = " + hardness +
                ", Specific Gravity = " + specificGravity + ", Cleavage = " + cleavage + ", Crustal Abundances = " +
                crustalAbundances + " and Economic Value = " + economicValue;
        return playCardString;

    }

    // Shorter string for more concise info
    public String toShortString() {
        return "Name: "  + getCardName() + ", Hardness = " + hardness + ", Specific Gravity = " +
                specificGravity + ", Cleavage: " + cleavage + ", Crustal Abundances: " + crustalAbundances + " and " +
                "Economic Value: " + economicValue;
    }
}
