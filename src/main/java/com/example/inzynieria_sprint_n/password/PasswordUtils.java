package com.example.inzynieria_sprint_n.password;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;


public class PasswordUtils {

    // Define the algorithm and the key length
    private static final String ALGORITHM = "PBKDF2WithHmacSHA512";
    private static final int KEY_LENGTH = 512;
    private static final int ITERATIONS = 10000;

    private static final String SALT_STRING = "ABRAKADABRA";
    private static final byte[] SALT = SALT_STRING.getBytes();

    // Hash a password using the PBKDF2WithHmacSHA512 algorithm
    public static String hashPassword(String password) {
        char[] passwordChars = password.toCharArray();
        byte[] hashedPassword = null;
        try {
            PBEKeySpec spec = new PBEKeySpec(passwordChars, SALT, ITERATIONS, KEY_LENGTH);
            SecretKeyFactory key = SecretKeyFactory.getInstance(ALGORITHM);
            hashedPassword = key.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return Base64.getEncoder().encodeToString(hashedPassword);
    }

    // Unhash a password using the PBKDF2WithHmacSHA512 algorithm
    public static boolean verifyPassword(String password, String hashedPassword) {
        char[] passwordChars = password.toCharArray();
        byte[] hashedPasswordBytes = Base64.getDecoder().decode(hashedPassword);
        try {
            PBEKeySpec spec = new PBEKeySpec(passwordChars, SALT, ITERATIONS, KEY_LENGTH);
            SecretKeyFactory key = SecretKeyFactory.getInstance(ALGORITHM);
            byte[] testHash = key.generateSecret(spec).getEncoded();
            if (testHash.length != hashedPasswordBytes.length) {
                return false;
            }
            for (int i = 0; i < testHash.length; i++) {
                if (testHash[i] != hashedPasswordBytes[i]) {
                    return false;
                }
            }
            return true;
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
            return false;
        }
    }
}
