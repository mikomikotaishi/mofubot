package org.mofubot.utilities.cryptography.hash;

public class Sha256 implements Hash {
    private Sha256() {}

    private static String getAlgorithm() {
        return "SHA-256";
    }
}
