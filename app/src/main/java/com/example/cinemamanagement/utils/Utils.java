package com.example.cinemamanagement.utils;

import android.annotation.SuppressLint;
import android.content.Context;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Utils {

    @SuppressLint("HardwareIds")
    public static String getUserId(Context context) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        return user.getUid().toString();
    }

    public static String getUserEmail(Context context) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        return user.getEmail().toString();
    }

}
