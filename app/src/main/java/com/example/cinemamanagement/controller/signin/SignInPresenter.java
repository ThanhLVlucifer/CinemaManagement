package com.example.cinemamanagement.controller.signin;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.cinemamanagement.R;
import com.example.cinemamanagement.constant.Constant;
import com.example.cinemamanagement.constant.GlobalFuntion;
import com.example.cinemamanagement.controller.AdminActivity;
import com.example.cinemamanagement.controller.UserActivity;
import com.example.cinemamanagement.utils.StringUtil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignInPresenter {
    private final SignInView signInView;

    public SignInPresenter(SignInView signInView) {
        this.signInView = signInView;
    }


    public void switchToMainActivity(@NonNull Activity activity, String email, String password, boolean rbClientchecked) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        if (rbClientchecked && !user.getUid().equals(Constant.UID_ADMIN)) {
                            signInView.switchToUser();
                        } else if (!rbClientchecked && user.getUid().equals(Constant.UID_ADMIN)) {
                            signInView.switchToAdmin();
                        }
                    } else {
                        signInView.showNotifyLoginFail();
                    }
                })
                .addOnFailureListener(e -> signInView.showNotifyLoginFail());

    }

    public void sendRequestResetPassword(String strEmail) {
        if (StringUtil.isEmpty(strEmail)) {
            signInView.showNotifyRetypeEmail();
            return;
        }
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.sendPasswordResetEmail(strEmail)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        signInView.sendRequestResetPasswordSuccess();
                    } else {
                        signInView.sendRequestResetPasswordFail();
                    }
                });
        mAuth.sendPasswordResetEmail(strEmail).addOnFailureListener(e -> signInView.sendRequestResetPasswordFail());
    }
}
