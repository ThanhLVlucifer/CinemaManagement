package com.example.cinemamanagement.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.cinemamanagement.databinding.FragmentUserAccountBinding;

public class UserAccountFragment extends Fragment {
    private FragmentUserAccountBinding mFragmentUserAccountBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentUserAccountBinding = FragmentUserAccountBinding.inflate(inflater,container,false);
        return mFragmentUserAccountBinding.getRoot();
    }
}
