package com.example.cinemamanagement.controller.changepassworduser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.cinemamanagement.R;
import com.example.cinemamanagement.databinding.ActivityChangePasswordUserBinding;

public class ChangePasswordUserActivity extends AppCompatActivity implements ChangePasswordView {
    private ActivityChangePasswordUserBinding activityChangePasswordUserBinding;
    private ChangePasswordPresenter changePasswordPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityChangePasswordUserBinding = ActivityChangePasswordUserBinding.inflate(getLayoutInflater());
        setContentView(activityChangePasswordUserBinding.getRoot());
        changePasswordPresenter = new ChangePasswordPresenter(this);
        activityChangePasswordUserBinding.btnChangePassword.setOnClickListener(view -> {
            String currentPassword = activityChangePasswordUserBinding.edtCurrentPassword.getText().toString().trim();
            String newPassword = activityChangePasswordUserBinding.edtNewPassword.getText().toString().trim();
            String confirmNewPassword = activityChangePasswordUserBinding.edtConfirmNewPassword.getText().toString().trim();
            changePasswordPresenter.changePasswordUer(getApplicationContext(), currentPassword, newPassword, confirmNewPassword);
        });

    }

    @Override
    public void loadChangePasswordSuccess() {
        activityChangePasswordUserBinding.edtCurrentPassword.setText("");
        activityChangePasswordUserBinding.edtNewPassword.setText("");
        activityChangePasswordUserBinding.edtConfirmNewPassword.setText("");
    }
}