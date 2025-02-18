package org.mofubot.utilities.cryptography;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class RSA {
    private RSA() {}

    private static final ThreadLocal<Cipher> cipher = ThreadLocal.withInitial(() -> {
        try {
            return Cipher.getInstance("RSA");
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new RuntimeException(e);
        }
    });

    private static final ThreadLocal<KeyPair> keyGen = ThreadLocal.withInitial(() -> {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(2048);
            return keyGen.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    });

    public static String encrypt(String plaintext, PublicKey publicKey) throws BadPaddingException, IllegalBlockSizeException, InvalidKeyException {
        Cipher cipherInstance = cipher.get();
        cipherInstance.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedBytes = cipherInstance.doFinal(plaintext.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(String ciphertext, PrivateKey privateKey) throws BadPaddingException, IllegalBlockSizeException, InvalidKeyException {
        Cipher cipherInstance = cipher.get();
        cipherInstance.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedBytes = cipherInstance.doFinal(Base64.getDecoder().decode(ciphertext));
        return new String(decryptedBytes);
    }

    public static String keyToString(PublicKey publicKey) {
        return Base64.getEncoder().encodeToString(publicKey.getEncoded());
    }

    public static String keyToString(PrivateKey privateKey) {
        return Base64.getEncoder().encodeToString(privateKey.getEncoded());
    }

    public static PublicKey getPublicKeyFromString(String keyString) throws InvalidKeySpecException, NoSuchAlgorithmException {
        byte[] decodedKey = Base64.getDecoder().decode(keyString);
        return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decodedKey));
    }

    public static PrivateKey getPrivateKeyFromString(String keyString) throws InvalidKeySpecException, NoSuchAlgorithmException {
        byte[] decodedKey = Base64.getDecoder().decode(keyString);
        return KeyFactory.getInstance("RSA").generatePrivate(new X509EncodedKeySpec(decodedKey));
    }
}
