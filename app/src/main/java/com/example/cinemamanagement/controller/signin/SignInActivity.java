package com.example.cinemamanagement.controller.signin;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.cinemamanagement.R;
import com.example.cinemamanagement.constant.GlobalFuntion;
import com.example.cinemamanagement.controller.AdminActivity;
import com.example.cinemamanagement.controller.signup.SignUpActivity;
import com.example.cinemamanagement.controller.UserActivity;
import com.example.cinemamanagement.databinding.ActivitySignInBinding;
import com.example.cinemamanagement.utils.StringUtil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity implements SignInView {
    private ActivitySignInBinding mActivitySignInBinding;
    private SignInPresenter signInPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivitySignInBinding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(mActivitySignInBinding.getRoot());

        signInPresenter = new SignInPresenter(this);
        setEventEdt();
        initEvent();
    }

    private void setEventEdt() {
        mActivitySignInBinding.edtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!StringUtil.isValidEmail(editable.toString())) {
                    mActivitySignInBinding.edtEmail.setError(getString(R.string.invalid_email));
                } else {
                    mActivitySignInBinding.edtEmail.setError(null);
                }
            }
        });

        mActivitySignInBinding.edtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!StringUtil.isGoodField(editable.toString())) {
                    mActivitySignInBinding.edtPassword.setError(getString(R.string.invalid_password));
                } else {
                    mActivitySignInBinding.edtPassword.setError(null);
                }
            }
        });
    }

    private void initEvent() {
        mActivitySignInBinding.btnSignIn.setOnClickListener(view -> {
            String email = mActivitySignInBinding.edtEmail.getText().toString().trim();
            String password = mActivitySignInBinding.edtPassword.getText().toString().trim();
            boolean rbClientChecked = mActivitySignInBinding.rbClient.isChecked();
            signInPresenter.switchToMainActivity(SignInActivity.this, email, password, rbClientChecked);
        });

        mActivitySignInBinding.layoutForgotPassword.setOnClickListener(view -> {
            signInPresenter.sendRequestResetPassword(mActivitySignInBinding.edtEmail.getText().toString().trim());
        });
        mActivitySignInBinding.layoutSignUp.setOnClickListener(view -> {
            GlobalFuntion.startActivity(getApplicationContext(), SignUpActivity.class);
        });
    }

    @Override
    public void showNotifyRetypeEmail() {
        GlobalFuntion.showToastMessage(getApplicationContext(), getString(R.string.notify_retype_email));
    }

    @Override
    public void sendRequestResetPasswordSuccess() {
        GlobalFuntion.showToastMessage(getApplicationContext(), getString(R.string.notify_send_request_reset_password_success));
    }

    @Override
    public void sendRequestResetPasswordFail() {
        GlobalFuntion.showToastMessage(getApplicationContext(), getString(R.string.notify_send_request_reset_password_fail));
    }

    @Override
    public void switchToUser() {
        GlobalFuntion.startActivity(getApplicationContext(), UserActivity.class);
        finishAffinity();
    }

    @Override
    public void switchToAdmin() {
        GlobalFuntion.startActivity(getApplicationContext(), AdminActivity.class);
        finishAffinity();
    }

    @Override
    public void showNotifyLoginFail() {
        GlobalFuntion.showToastMessage(getApplicationContext(), getString(R.string.notify_login_fail));
    }
}