package employees.test.com.utiils;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Math.abs;

public class RandomUtils {

    private static Random rnd = ThreadLocalRandom.current();

    private RandomUtils() {
    }

    public static Integer randomPositiveInteger() {
        int random = rnd.nextInt();
        return abs(random);
    }
}
