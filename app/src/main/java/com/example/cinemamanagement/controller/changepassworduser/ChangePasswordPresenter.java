package com.example.cinemamanagement.controller.changepassworduser;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.cinemamanagement.constant.GlobalFuntion;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChangePasswordPresenter {
    private final ChangePasswordView changePasswordView;

    public ChangePasswordPresenter(ChangePasswordView changePasswordView) {
        this.changePasswordView = changePasswordView;
    }

    public void changePasswordUer(@NonNull Context context, String currentPassword, String newPassword, String confirmNewPassword) {
        if (!newPassword.equals(confirmNewPassword)) {
            GlobalFuntion.showToastMessage(context, "New passwords do not match");
            return;
        }
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        AuthCredential credential = EmailAuthProvider.getCredential(user.getEmail(), currentPassword);

        user.reauthenticate(credential)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            user.updatePassword(newPassword)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                changePasswordView.loadChangePasswordSuccess();
                                                GlobalFuntion.showToastMessage(context, "change password success");
                                            } else {
                                                GlobalFuntion.showToastMessage(context, "change password fail");
                                            }
                                        }
                                    });
                        } else {
                            GlobalFuntion.showToastMessage(context, "Validation of old password failed");
                        }
                    }
                });
    }
}
