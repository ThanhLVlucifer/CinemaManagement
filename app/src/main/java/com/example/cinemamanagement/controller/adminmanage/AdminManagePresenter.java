package com.example.cinemamanagement.controller.adminmanage;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.cinemamanagement.utils.Utils;
import com.google.firebase.auth.FirebaseAuth;

public class AdminManagePresenter {
    private final AdminManageView adminManageView;

    public AdminManagePresenter(AdminManageView adminManageView) {
        this.adminManageView = adminManageView;
    }

    public void getUserEmail(@NonNull Context context) {
        String strEmail = Utils.getUserEmail(context);
        adminManageView.loadEmailUser(strEmail);
    }

    public void logoutUser(@NonNull Context context){
        FirebaseAuth.getInstance().signOut();
        adminManageView.loadLogoutUser(context);
    }

}
