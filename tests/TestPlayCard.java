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
    }
}
