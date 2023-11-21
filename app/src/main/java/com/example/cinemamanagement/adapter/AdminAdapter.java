package com.example.cinemamanagement.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.cinemamanagement.controller.admingenre.AdminGenreFragment;
import com.example.cinemamanagement.controller.adminhistory.AdminHistoryFragment;
import com.example.cinemamanagement.controller.adminmanage.AdminManageFragment;
import com.example.cinemamanagement.controller.adminmovie.AdminMovieFragment;
import com.example.cinemamanagement.controller.adminproduct.AdminProductFragment;

public class AdminAdapter extends FragmentStateAdapter {

    public AdminAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new AdminMovieFragment();
            case 1:
                return new AdminGenreFragment();
            case 2:
                return new AdminProductFragment();
            case 3:
                return new AdminHistoryFragment();
            case 4:
                return new AdminManageFragment();
            default:
                return new AdminMovieFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
