package com.example.cinemamanagement.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.cinemamanagement.R;
import com.example.cinemamanagement.adapter.UserAdapter;
import com.example.cinemamanagement.databinding.ActivityUserBinding;
import com.example.cinemamanagement.model.Movie;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserActivity extends AppCompatActivity {
    private ActivityUserBinding mActivityUserBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityUserBinding = ActivityUserBinding.inflate(getLayoutInflater());
        setContentView(mActivityUserBinding.getRoot());

        UserAdapter userAdapter = new UserAdapter(this);
        mActivityUserBinding.viewpager2.setAdapter(userAdapter);
        mActivityUserBinding.viewpager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position) {
                    case 0:
                        mActivityUserBinding.bottomNavigation.getMenu().findItem(R.id.nav_home_user).setChecked(true);
                        break;
                    case 1:
                        mActivityUserBinding.bottomNavigation.getMenu().findItem(R.id.nav_history_booking_user).setChecked(true);
                        break;
                    case 2:
                        mActivityUserBinding.bottomNavigation.getMenu().findItem(R.id.nav_account_user).setChecked(true);
                        break;
                }
            }
        });

        mActivityUserBinding.bottomNavigation.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home_user) {
                mActivityUserBinding.viewpager2.setCurrentItem(0);
            } else if (id == R.id.nav_history_booking_user) {
                mActivityUserBinding.viewpager2.setCurrentItem(1);
            } else if (id == R.id.nav_account_user) {
                mActivityUserBinding.viewpager2.setCurrentItem(2);
            }
            return true;
        });
    }

}