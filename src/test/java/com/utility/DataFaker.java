package com.utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class DataFaker {

    public static String generateRandomEmail() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 2) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr + System.currentTimeMillis() + "@gmail.com";
    }

    public static String generatetimeStampString(String pattern) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime now = LocalDateTime.now();
        return df.format(now);
    }

    public static String generateCharRandom(int len) {
        String password = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder passwordRnd = new StringBuilder();
        Random rnd = new Random();
        while (passwordRnd.length() < len) {
            int index = (int) (rnd.nextFloat() * password.length());
            passwordRnd.append(password.charAt(index));
        }
        String passwordStr = passwordRnd.toString();
        return passwordStr;
    }

}
