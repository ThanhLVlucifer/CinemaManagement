package com.example.cinemamanagement.controller.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;

import com.example.cinemamanagement.constant.GlobalFuntion;
import com.example.cinemamanagement.controller.AdminActivity;
import com.example.cinemamanagement.controller.signin.SignInActivity;
import com.example.cinemamanagement.controller.UserActivity;
import com.example.cinemamanagement.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity implements SplashView {
    private ActivitySplashBinding activitySplashBinding;
    private SplashPresenter splashPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySplashBinding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(activitySplashBinding.getRoot());
        splashPresenter = new SplashPresenter(this);
        waitForTransition();
    }

    private void waitForTransition() {
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            splashPresenter.getUid();
        }, 2000);
    }

    @Override
    public void switchToSignInActivity() {
        GlobalFuntion.startActivity(getApplicationContext(), SignInActivity.class);
        finish();
    }

    @Override
    public void switchToUserActivity() {
        GlobalFuntion.startActivity(getApplicationContext(), UserActivity.class);
        finish();
    }

    @Override
    public void switchToAdminActivity() {
        GlobalFuntion.startActivity(getApplicationContext(), AdminActivity.class);
        finish();
    }
}