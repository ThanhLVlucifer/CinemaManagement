package com.example.cinemamanagement.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.app.AlertDialog;
import android.os.Bundle;

import com.example.cinemamanagement.R;
import com.example.cinemamanagement.adapter.AdminAdapter;
import com.example.cinemamanagement.adapter.UserAdapter;
import com.example.cinemamanagement.databinding.ActivityAdminBinding;

public class AdminActivity extends AppCompatActivity {
    private ActivityAdminBinding activityAdminBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAdminBinding = ActivityAdminBinding.inflate(getLayoutInflater());
        setContentView(activityAdminBinding.getRoot());

        AdminAdapter adminAdapter = new AdminAdapter(this);
        activityAdminBinding.viewpager2.setAdapter(adminAdapter);
        activityAdminBinding.viewpager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position) {
                    case 0:
                        activityAdminBinding.bottomNavigation.getMenu().findItem(R.id.nav_admin_movie).setChecked(true);
                        break;
                    case 1:
                        activityAdminBinding.bottomNavigation.getMenu().findItem(R.id.nav_admin_genre).setChecked(true);
                        break;
                    case 2:
                        activityAdminBinding.bottomNavigation.getMenu().findItem(R.id.nav_admin_product).setChecked(true);
                        break;
                    case 3:
                        activityAdminBinding.bottomNavigation.getMenu().findItem(R.id.nav_admin_history).setChecked(true);
                        break;
                    case 4:
                        activityAdminBinding.bottomNavigation.getMenu().findItem(R.id.nav_admin_manage).setChecked(true);
                        break;
                }
            }
        });

        activityAdminBinding.bottomNavigation.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_admin_movie) {
                activityAdminBinding.viewpager2.setCurrentItem(0);
            } else if (id == R.id.nav_admin_genre) {
                activityAdminBinding.viewpager2.setCurrentItem(1);
            } else if (id == R.id.nav_admin_product) {
                activityAdminBinding.viewpager2.setCurrentItem(2);
            } else if (id == R.id.nav_admin_history) {
                activityAdminBinding.viewpager2.setCurrentItem(3);
            } else if (id == R.id.nav_admin_manage) {
                activityAdminBinding.viewpager2.setCurrentItem(4);
            }
            return true;
        });
    }

}