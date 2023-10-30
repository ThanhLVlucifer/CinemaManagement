package com.example.cinemamanagement.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.cinemamanagement.databinding.FragmentUserHistoryBookingBinding;


public class UserHistoryBookingFragment extends Fragment {
    private FragmentUserHistoryBookingBinding mFragmentUserHistoryBookingBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentUserHistoryBookingBinding = FragmentUserHistoryBookingBinding.inflate(inflater,container,false);
        return mFragmentUserHistoryBookingBinding.getRoot();
    }
}
