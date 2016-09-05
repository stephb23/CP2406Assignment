import java.util.ArrayList;

/**
 * Created by Stephanie on 2/09/2016.
 */
public class TestPlayCard {
    public static void main(String[] args) {
        ArrayList<String> occurences = new ArrayList();
        occurences.add("igneous");
        occurences.add("metamorphic");
        occurences.add("sedimentary");

        PlayCard testCard1 = new PlayCard("Slide01.jpg", "Slide01", "Quartz", "SiO_2", "tectosilicate", "hexagonal",
                occurences, "7", "2.65", "poor/none", "high", "moderate");

        System.out.println("Testing PlayCard using Quartz card from assignment specification...");
        System.out.println(testCard1.toString());

        System.out.println("\nTesting setter methods by changing the card to Topaz card from assignment");
        occurences.remove(0);
        testCard1.setImageFile("Slide25.jpg");
        testCard1.setImageName("Slide25");
        testCard1.setCardName("Topaz");
        testCard1.setChemistry("Al_2 Si O_4 (F, OH)_2");
        testCard1.setClassification("nesosilicate");
        testCard1.setCrystalSystem("orthorhombic");
        testCard1.setOccurrences(occurences);
        testCard1.setHardness("8");
        testCard1.setSpecificGravity("3.5 - 3.6");
        testCard1.setCleavage("1 poor");
        testCard1.setCrystalAbundances("trace");
        testCard1.setEconomicValue("moderate");
        System.out.println("Card information now is: ");
        System.out.println(testCard1.toString());

        System.out.println("\nTest getters using this card");
        System.out.println("Expected Image File = Slide25.jpg, Actual Image File = " + testCard1.getImageFile());
        System.out.println("Expected Image Name = Slide25, Actual Image Name = " + testCard1.getImageName());
        System.out.println("Expected Card Name = Topaz, Actual Image Name = " + testCard1.getCardName());
        System.out.println("Expected Chemistry = Al_2 Si O_4 (F, OH)_2, Actual Chemistry = " + testCard1.getChemistry());
        System.out.println("Expected Classification = nesosilicate, Actual Classification = " + testCard1.getClass());
        System.out.println("Expected Crystal System = orthorhombic, Actual Crystal System = " + testCard1.getCrystalSystem());
        System.out.println("Expected Occurrences = [metamorphic, sedimentary], Actual Occurences = " + testCard1.getOccurrences());
        System.out.println("Expected Hardness = 8, Actual Hardness = " + testCard1.getHardness());
        System.out.println("Expected Specific Gravity = 3.5 - 3.6, Actual Specific Gravity = " + testCard1.getSpecificGravity());
        System.out.println("Expected Cleavage = 1 poor, Actual Cleavage = " + testCard1.getCleavage());
        System.out.println("Expected Crystal Abundances = trace, Actual Crystal Abundances = " + testCard1.getCrystalAbundances());
        System.out.println("Expected Economic Value = moderate, Actual Economic Value = " + testCard1.getEconomicValue());
    }
}
