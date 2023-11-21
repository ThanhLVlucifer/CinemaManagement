package com.example.cinemamanagement.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.cinemamanagement.controller.useraccount.UserAccountFragment;
import com.example.cinemamanagement.controller.userhistorybooking.UserHistoryBookingFragment;
import com.example.cinemamanagement.controller.userhome.UserHomeFragment;

public class UserAdapter extends FragmentStateAdapter {
    public UserAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new UserHomeFragment();
            case 1:
                return new UserHistoryBookingFragment();
            case 2:
                return new UserAccountFragment();
            default:
                return new UserHomeFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
