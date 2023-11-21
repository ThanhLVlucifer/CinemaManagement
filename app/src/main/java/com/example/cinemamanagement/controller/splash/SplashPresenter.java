package com.example.cinemamanagement.controller.splash;

import com.example.cinemamanagement.constant.Constant;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashPresenter {
    private final SplashView splashView;

    public SplashPresenter(SplashView splashView) {
        this.splashView = splashView;
    }

    public void getUid() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            splashView.switchToSignInActivity();
        } else {
            if (user.getUid().equals(Constant.UID_ADMIN)) {
                splashView.switchToAdminActivity();
            } else {
                splashView.switchToUserActivity();
            }
        }
    }
}
