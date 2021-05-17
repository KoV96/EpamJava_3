package com.epam.rd.java.basic.practice3;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

public class Part4 {

    public static void main(String[] args) throws NoSuchAlgorithmException{
        System.out.println(hash("asdf", "MD5"));
        System.out.println(hash("asdf", "SHA-256"));
    }

    public static String hash(String input, String algorithm) throws NoSuchAlgorithmException{
        MessageDigest digest = MessageDigest.getInstance(algorithm);
        digest.update(input.getBytes(StandardCharsets.UTF_8));
        byte[] hash = digest.digest();
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String s = Integer.toHexString(0xff & b);
            s = (s.length() == 1) ? "0" + s : s;
            hexString.append(s);
        }
        return hexString.toString().toUpperCase(Locale.ROOT);
    }
}
