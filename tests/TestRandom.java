/**
 * Created by Stephanie on 9/09/2016.
 */
public class TestRandom {
    public static void main(String[] args) {
        int random;

        for (int i = 0; i < 20; ++i) {
            random = (int) (Math.random() * 5);
            System.out.println(random);
        }
    }
}
