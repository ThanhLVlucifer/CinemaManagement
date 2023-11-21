package com.example.cinemamanagement.controller.useraccount;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.cinemamanagement.ControllerApplication;
import com.example.cinemamanagement.utils.Utils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserAccountPresenter {
    private final UserAccountView userAccountView;

    public UserAccountPresenter(UserAccountView userAccountView) {
        this.userAccountView = userAccountView;
    }

    public void getUserEmail(@NonNull Context context) {
        String strEmail = Utils.getUserEmail(context);
        userAccountView.loadEmailUser(strEmail);
    }

    public void logoutUser(@NonNull Context context){
        FirebaseAuth.getInstance().signOut();
        userAccountView.loadLogoutUser(context);
    }

}
