package org.mofubot.utilities;

import java.util.concurrent.ThreadLocalRandom;

public class RandomNumberGenerator {
    private RandomNumberGenerator() {}

    public static int generateRandomNumber(int n) {
        return ThreadLocalRandom.current().nextInt(n);
    }
}
