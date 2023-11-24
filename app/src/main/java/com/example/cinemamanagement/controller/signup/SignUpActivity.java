package com.example.cinemamanagement.controller.signup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import static android.content.ContentValues.TAG;

import android.widget.Toast;

import com.example.cinemamanagement.R;
import com.example.cinemamanagement.constant.GlobalFuntion;
import com.example.cinemamanagement.controller.UserActivity;
import com.example.cinemamanagement.controller.signin.SignInActivity;
import com.example.cinemamanagement.databinding.ActivitySignUpBinding;
import com.example.cinemamanagement.utils.StringUtil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity implements SignUpView {
    private ActivitySignUpBinding mActivitySignUpBinding;
    private SignUpPresenter signUpPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivitySignUpBinding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(mActivitySignUpBinding.getRoot());

        signUpPresenter = new SignUpPresenter(this);
        setEventEdt();
        initEvent();
    }

    private void initEvent() {
        mActivitySignUpBinding.btnSignUp.setOnClickListener(view -> {
            String email = mActivitySignUpBinding.edtEmail.getText().toString().trim();
            String password = mActivitySignUpBinding.edtPassword.getText().toString().trim();
            signUpPresenter.registerSuccess(SignUpActivity.this, email, password);
        });
        mActivitySignUpBinding.layoutSignIn.setOnClickListener(view -> {
            GlobalFuntion.startActivity(getApplicationContext(), SignInActivity.class);
        });
    }

    private void setEventEdt() {
        mActivitySignUpBinding.edtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!StringUtil.isValidEmail(editable.toString())) {
                    mActivitySignUpBinding.edtEmail.setError(getString(R.string.invalid_email));
                } else {
                    mActivitySignUpBinding.edtEmail.setError(null);
                }
            }
        });

        mActivitySignUpBinding.edtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!StringUtil.isGoodField(editable.toString())) {
                    mActivitySignUpBinding.edtPassword.setError(getString(R.string.invalid_password));
                } else {
                    mActivitySignUpBinding.edtPassword.setError(null);
                }
            }
        });
    }

    @Override
    public void switchToUser() {
        GlobalFuntion.startActivity(getApplicationContext(), UserActivity.class);
        finish();
    }

    @Override
    public void registerFail() {
        GlobalFuntion.showToastMessage(getApplicationContext(), getString(R.string.notify_register_fail));
    }
}
