package org.mofubot.utilities.calculations;

import java.util.concurrent.ThreadLocalRandom;

public class RandomNumberGenerator {
    private RandomNumberGenerator() {}

    public static int generateRandomNumber(int n) {
        return ThreadLocalRandom.current().nextInt(n);
    }
}
