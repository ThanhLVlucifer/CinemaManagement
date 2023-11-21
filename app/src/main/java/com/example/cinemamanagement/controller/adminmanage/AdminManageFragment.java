package com.example.cinemamanagement.controller.adminmanage;

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
import com.example.cinemamanagement.controller.statistical.StatisticalActivity;
import com.example.cinemamanagement.databinding.FragmentAdminManageBinding;

public class AdminManageFragment extends Fragment implements AdminManageView {
    private FragmentAdminManageBinding fragmentAdminManageBinding;
    private AdminManagePresenter adminManagePresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentAdminManageBinding = FragmentAdminManageBinding.inflate(inflater, container, false);
        adminManagePresenter = new AdminManagePresenter(this);
        initView();
        setEvent();

        return fragmentAdminManageBinding.getRoot();
    }

    private void initView() {
        if (getActivity() == null) {
            return;
        }
        adminManagePresenter.getUserEmail(getActivity());
    }

    private void setEvent() {
        fragmentAdminManageBinding.layoutChangePassword.setOnClickListener(view -> {
            GlobalFuntion.startActivity(getActivity(), ChangePasswordUserActivity.class);
        });

        fragmentAdminManageBinding.layoutLogout.setOnClickListener(view -> {
            adminManagePresenter.logoutUser(getActivity());
        });

        fragmentAdminManageBinding.layoutRevenue.setOnClickListener(view -> {
            GlobalFuntion.startActivity(getActivity(), StatisticalActivity.class);
        });
    }

    @Override
    public void loadEmailUser(String str) {
        fragmentAdminManageBinding.tvEmail.setText(str);
    }

    @Override
    public void loadLogoutUser(Context context) {
        GlobalFuntion.startActivity(getActivity(), SignInActivity.class);
    }
}
