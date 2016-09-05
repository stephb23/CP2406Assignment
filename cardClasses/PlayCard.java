import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Stephanie on 2/09/2016.
 */
public class PlayCard extends Card {
    private String chemistry, classification, crystalSystem;
    private ArrayList<String> occurrences = new ArrayList<>();
    private String hardness, specificGravity, cleavage, crystalAbundances, economicValue;

    public PlayCard() {
        // let all set to null;
    }

    public PlayCard(String cardName) {
        super(cardName);
    }

    public PlayCard(String imageFile, String imageName, String cardName) {
        super(imageFile, imageName, cardName);
    }

    public PlayCard(String cardName, String chemistry, String classification, String crystalSystem,
                    ArrayList<String> occurrences, String hardness, String specificGravity, String cleavage,
                    String crystalAbundances, String economicValue) {
        super(cardName);
        this.chemistry = chemistry;
        this.classification = classification;
        this.crystalSystem = crystalSystem;
        this.occurrences = occurrences;
        this.hardness = hardness;
        this.specificGravity = specificGravity;
        this.cleavage = cleavage;
        this.crystalAbundances = crystalAbundances;
        this.economicValue = economicValue;
    }

    public PlayCard(String imageFile, String imageName, String cardName, String chemistry, String classification,
                    String crystalSystem, ArrayList<String> occurrences, String hardness, String specificGravity,
                    String cleavage, String crystalAbundances, String economicValue) {
        super(imageFile, imageName, cardName);
        this.chemistry = chemistry;
        this.classification = classification;
        this.crystalSystem = crystalSystem;
        this.occurrences = occurrences;
        this.hardness = hardness;
        this.specificGravity = specificGravity;
        this.cleavage = cleavage;
        this.crystalAbundances = crystalAbundances;
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

    public void setSpecificGravity(String specificGravity) {
        this.specificGravity = specificGravity;
    }

    public String getSpecificGravity() {
        return specificGravity;
    }

    public void setCleavage(String cleavage) {
        this.cleavage = cleavage;
    }

    public String getCleavage() {
        return cleavage;
    }

    public void setCrystalAbundances(String crystalAbundances) {
        this.crystalAbundances = crystalAbundances;
    }

    public String getCrystalAbundances(){
        return crystalAbundances;
    }

    public void setEconomicValue(String economicValue) {
        this.economicValue = economicValue;
    }

    public String getEconomicValue() {
        return economicValue;
    }

    @Override
    public String toString() {
        String playCardString = super.toString();
        playCardString += ",\nChemistry = " + chemistry + ", Classification = " + classification + ", Crystal System = "
                + crystalSystem + ",\nOccurences = " + occurrences.toString() + ",\nHardness = " + hardness +
                ", Specific Gravity = " + specificGravity + ", Cleavage = " + cleavage + ",\nCrystal Abundances = " +
                crystalAbundances + " and Economic Value = " + economicValue;
        return playCardString;

    }

}
