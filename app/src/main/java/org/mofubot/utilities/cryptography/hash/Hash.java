package org.mofubot.utilities.cryptography.hash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public interface Hash {
    static String getAlgorithm() {
        throw new UnsupportedOperationException("getAlgorithm() must be overriden!");
    }

    static String hash(String message) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(getAlgorithm());
        byte[] hashBytes = digest.digest(message.getBytes());
        return Base64.getEncoder().encodeToString(hashBytes);
    }

    static boolean verifyHash(String message, String hash) throws NoSuchAlgorithmException {
        String newHash = hash(message);
        return newHash.equals(hash);
    }
}
