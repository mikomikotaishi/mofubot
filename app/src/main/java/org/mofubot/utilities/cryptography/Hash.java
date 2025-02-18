package org.mofubot.utilities.cryptography;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Hash {
    private Hash() {}

    public static String hash(String message, String algorithmName) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(algorithmName);
        byte[] hashBytes = digest.digest(message.getBytes());
        return Base64.getEncoder().encodeToString(hashBytes);
    }

    public static boolean verifyHash(String message, String hash, String algorithmName) throws NoSuchAlgorithmException {
        String newHash = hash(message, algorithmName);
        return newHash.equals(hash);
    }
}
