package com.example.evidencepojisteni.models.services;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Passwords {
    // Class for encrypting user's passwords
    protected Passwords() {
    }

    // Encrypt user's password
    public static String encrypt(String password) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        String key = "Bép12345Taruy'(";
        Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, aesKey);
        byte[] encrypted = cipher.doFinal(password.getBytes());
        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(encrypted);
    }

    // Decrypt user's password
    public static String decrypt(String encrypted) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        String key = "Bép12345Taruy'(";
        byte[] decodedKey = Base64.getDecoder().decode(encrypted);
        Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, aesKey);
        byte[] decrypted = cipher.doFinal(decodedKey);
        return new String(decrypted);
    }
}
