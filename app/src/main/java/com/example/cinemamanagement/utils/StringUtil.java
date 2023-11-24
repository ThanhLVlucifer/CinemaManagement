package com.example.cinemamanagement.utils;

import android.util.Patterns;

public class StringUtil {

    public static boolean isValidEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean isGoodField(String input) {
        return input != null && !input.isEmpty() && input.length() >= 6;
    }

    public static boolean isEmpty(String input) {
        return input == null || input.isEmpty() || ("").equals(input.trim());
    }
}
