package com.example.cinemamanagement.controller.signup;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.cinemamanagement.R;
import com.example.cinemamanagement.controller.UserActivity;
import com.example.cinemamanagement.utils.StringUtil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpPresenter {
    private final SignUpView signUpView;

    public SignUpPresenter(SignUpView signUpView) {
        this.signUpView = signUpView;
    }

    public void registerSuccess(@NonNull Activity activity, String email, String password) {
        if (activity == null || StringUtil.isEmpty(email) || StringUtil.isEmpty(password)) {
            return;
        }
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, task -> {
                    if (task.isSuccessful()) {
                        signUpView.switchToUser();
                    } else {
                        signUpView.registerFail();
                    }
                })
                .addOnFailureListener(e -> signUpView.registerFail());
    }
}
