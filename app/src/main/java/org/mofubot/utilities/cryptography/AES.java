package org.mofubot.utilities.cryptography;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AES {
    private AES() {}

    private static final ThreadLocal<Cipher> cipher = ThreadLocal.withInitial(() -> {
        try {
            return Cipher.getInstance("AES");
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new RuntimeException(e);
        }
    });

    private static final ThreadLocal<KeyGenerator> keyGen = ThreadLocal.withInitial(() -> {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(256);
            return keyGen;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    });

    private static SecretKey generateKey() {
        return keyGen.get().generateKey();
    }

    public static String encrypt(String plaintext, SecretKey secretKey) throws BadPaddingException, IllegalBlockSizeException, InvalidKeyException {
        Cipher cipherInstance = cipher.get();
        cipherInstance.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipherInstance.doFinal(plaintext.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(String ciphertext, SecretKey secretKey) throws BadPaddingException, IllegalBlockSizeException, InvalidKeyException {
        Cipher cipherInstance = cipher.get();
        cipherInstance.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipherInstance.doFinal(Base64.getDecoder().decode(ciphertext));
        return new String(decryptedBytes);
    }

    public static SecretKey getKeyFromString(String keyString) {
        byte[] decodedKey = Base64.getDecoder().decode(keyString);
        return new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
    }

    public static String keyToString(SecretKey secretKey) {
        return Base64.getEncoder().encodeToString(secretKey.getEncoded());
    }
}
