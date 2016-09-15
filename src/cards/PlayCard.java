package cards;

import java.util.ArrayList;

/**
 * Created by Stephanie on 2/09/2016.
 */
public class PlayCard extends Card {
    private final String TYPE_STRING = "play";
    private String chemistry, classification, crystalSystem;
    private ArrayList<String> occurrences = new ArrayList<>();
    private String hardness, specificGravity, cleavage, crustalAbundances, economicValue;

    public PlayCard() {
        setType(TYPE_STRING);
    }

    public PlayCard(String cardName) {
        super(cardName);
        setType(TYPE_STRING);
    }

    public PlayCard(String imageFile, String imageName, String cardName) {
        super(imageFile, imageName, cardName);
        setType(TYPE_STRING);
    }

    public PlayCard(String cardName, String chemistry, String classification, String crystalSystem,
                    ArrayList<String> occurrences, String hardness, String specificGravity, String cleavage,
                    String crustalAbundances, String economicValue) {
        super(cardName);
        setType(TYPE_STRING);
        this.chemistry = chemistry;
        this.classification = classification;
        this.crystalSystem = crystalSystem;
        this.occurrences = occurrences;
        this.hardness = hardness;
        this.specificGravity = specificGravity;
        this.cleavage = cleavage;
        this.crustalAbundances = crustalAbundances;
        this.economicValue = economicValue;
    }

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

    public void setChemistry(String chemistry) {
        this.chemistry = chemistry;
    }

    public String getChemistry() {
        return chemistry;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getClassification() {
        return classification;
    }

    public void setCrystalSystem(String crystalSystem) {
        this.crystalSystem = crystalSystem;
    }

    public String getCrystalSystem() {
        return crystalSystem;
    }

    public void setOccurrences(ArrayList<String> occurrences) {
        this.occurrences = occurrences;
    }

    public ArrayList<String> getOccurrences() {
        return occurrences;
    }

    public void setHardness(String hardness) {
        this.hardness = hardness;
    }

    public String getHardness() {
        return hardness;
    }

    public Double getHardnessAsDouble() {
        String[] hardnessRange;
        if (hardness.contains(" - ")) {
            hardnessRange = hardness.split(" - ");
        } else if (hardness.contains("-")) {
            hardnessRange = hardness.split("-");
        } else {
            hardnessRange = hardness.split(" ");
        }

        double highestHardness = Double.parseDouble(hardnessRange[hardnessRange.length - 1]);
        return highestHardness;
    }

    public void setSpecificGravity(String specificGravity) {
        this.specificGravity = specificGravity;
    }

    public String getSpecificGravity() {
        return specificGravity;
    }

    public Double getSpecificGravityAsDouble() {
        String[] specificGravityRange;
        if (specificGravity.contains(" - ")) {
            specificGravityRange = specificGravity.split(" - ");
        } else if (specificGravity.contains("-")) {
            specificGravityRange = specificGravity.split("-");
        } else {
            specificGravityRange = specificGravity.split(" ");
        }

        double highestSpecificGravity = Double.parseDouble(specificGravityRange[specificGravityRange.length - 1]);
        return highestSpecificGravity;
    }

    public void setCleavage(String cleavage) {
        this.cleavage = cleavage;
    }

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

    public void setCrustalAbundances(String crustalAbundances) {
        this.crustalAbundances = crustalAbundances;
    }

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

    public void setEconomicValue(String economicValue) {
        this.economicValue = economicValue;
    }

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

    @Override
    public String toString() {
        String playCardString = super.toString();
        playCardString += ",\nChemistry = " + chemistry + ", Classification = " + classification + ", Crystal System = "
                + crystalSystem + ",\nOccurences = " + occurrences.toString() + ",\nHardness = " + hardness +
                ", Specific Gravity = " + specificGravity + ", Cleavage = " + cleavage + ",\nCrustal Abundances = " +
                crustalAbundances + " and Economic Value = " + economicValue;
        return playCardString;

    }

    public String toShortString() {
        String playCardString = "Name: "  + getCardName() + ", Hardness = " + hardness + ", Specific Gravity = " +
                specificGravity + ", Cleavage: " + cleavage + ", Crustal Abundances: " + crustalAbundances + " and " +
                "Economic Value: " + economicValue;
        return playCardString;
    }
}
