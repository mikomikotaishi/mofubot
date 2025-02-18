package org.mofubot.utilities.cryptography.hash;

public class Sha512 implements Hash {
    private Sha512() {}

    private static String getAlgorithm() {
        return "SHA-512";
    }
}

