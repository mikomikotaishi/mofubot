package org.mofubot.utilities.cryptography;

import java.security.SecureRandom;

public class RandomNumberGenerator {
    private RandomNumberGenerator() {}

    private static final ThreadLocal<SecureRandom> rng = ThreadLocal.withInitial(SecureRandom::new);

    public static int generateRandomNumber(int n) {
        return rng.get().nextInt(n);
    }

    public static byte[] generateRandomBytes(int numBytes) {
        byte[] bytes = new byte[numBytes];
        rng.get().nextBytes(bytes);
        return bytes;
    }
}
