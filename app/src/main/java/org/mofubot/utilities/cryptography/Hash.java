package org.mofubot.utilities.cryptography;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Hash {
    private Hash() {}

    public static String hash(String message, String algorithm) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(algorithm);
        byte[] hashBytes = digest.digest(message.getBytes());
        return Base64.getEncoder().encodeToString(hashBytes);
    }

    public static boolean verifyHash(String message, String hash, String algorithm) throws NoSuchAlgorithmException {
        String newHash = hash(message, algorithm);
        return newHash.equals(hash);
    }
}
