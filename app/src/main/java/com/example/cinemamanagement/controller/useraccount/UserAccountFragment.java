package com.example.cinemamanagement.controller.useraccount;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.cinemamanagement.constant.GlobalFuntion;
import com.example.cinemamanagement.controller.signin.SignInActivity;
import com.example.cinemamanagement.controller.changepassworduser.ChangePasswordUserActivity;
import com.example.cinemamanagement.databinding.FragmentUserAccountBinding;

public class UserAccountFragment extends Fragment implements UserAccountView {
    private FragmentUserAccountBinding mFragmentUserAccountBinding;
    private UserAccountPresenter userAccountPresenter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentUserAccountBinding = FragmentUserAccountBinding.inflate(inflater, container, false);
        userAccountPresenter = new UserAccountPresenter(this);
        initView();
        setEvent();
        return mFragmentUserAccountBinding.getRoot();
    }

    private void initView() {
        if (getActivity() == null) {
            return;
        }
        userAccountPresenter.getUserEmail(getActivity());
    }

    private void setEvent() {
        mFragmentUserAccountBinding.layoutChangePassword.setOnClickListener(view -> {
            GlobalFuntion.startActivity(getActivity(), ChangePasswordUserActivity.class);
        });

        mFragmentUserAccountBinding.layoutLogout.setOnClickListener(view -> {
            userAccountPresenter.logoutUser(getActivity());
        });

    }

    @Override
    public void loadEmailUser(String str) {
        mFragmentUserAccountBinding.tvEmail.setText(str);
    }

    @Override
    public void loadLogoutUser(Context context) {
        GlobalFuntion.startActivity(context, SignInActivity.class);
    }
}
