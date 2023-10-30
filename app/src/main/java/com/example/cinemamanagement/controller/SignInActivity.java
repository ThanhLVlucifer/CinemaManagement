package com.example.cinemamanagement.controller;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.cinemamanagement.R;
import com.example.cinemamanagement.databinding.ActivitySignInBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity {
    private ActivitySignInBinding mActivitySignInBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivitySignInBinding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(mActivitySignInBinding.getRoot());

        mActivitySignInBinding.btnSignIn.setOnClickListener(view -> onClickLogin());
        mActivitySignInBinding.layoutForgotPassword.setOnClickListener(view -> {
            String email = mActivitySignInBinding.edtEmail.getText().toString().trim();
            if(email.isEmpty() || email == null ){
                Toast.makeText(SignInActivity.this, getResources().getString(R.string.blank_email), Toast.LENGTH_SHORT);
            }
            onClickForgotPassword(email);
        });
        mActivitySignInBinding.layoutSignUp.setOnClickListener(view -> switchToRegisterLayout());
    }

    private void onClickForgotPassword(String email) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(SignInActivity.this, getResources().getString(R.string.send_email_success), Toast.LENGTH_SHORT);
                        }
                    }
                });
        mAuth.sendPasswordResetEmail(email).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(SignInActivity.this, getResources().getString(R.string.send_email_failure), Toast.LENGTH_SHORT);
            }
        });
    }

    private void switchToRegisterLayout() {
        Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
        startActivity(intent);
        finishAffinity();
    }

    private void onClickLogin() {
        String email = mActivitySignInBinding.edtEmail.getText().toString().trim();
        String password = mActivitySignInBinding.edtPassword.getText().toString().trim();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(SignInActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent intent = new Intent(SignInActivity.this, UserActivity.class);
                            startActivity(intent);
                            finishAffinity();
                        }else{
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(SignInActivity.this, getResources().getString(R.string.login_failed), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}