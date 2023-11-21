package com.example.cinemamanagement.controller.signin;

public interface SignInView {
    void showNotifyRetypeEmail();
    void sendRequestResetPasswordSuccess();
    void sendRequestResetPasswordFail();

    void switchToUser();
    void switchToAdmin();

    void showNotifyLoginFail();
}
