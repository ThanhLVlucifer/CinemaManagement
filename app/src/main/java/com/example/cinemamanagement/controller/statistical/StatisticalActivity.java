package com.example.cinemamanagement.controller.statistical;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.cinemamanagement.R;
import com.example.cinemamanagement.adapter.StatisticalAdapter;
import com.example.cinemamanagement.constant.GlobalFuntion;
import com.example.cinemamanagement.databinding.ActivityStatisticalBinding;
import com.example.cinemamanagement.model.Booking;

import java.util.List;

public class StatisticalActivity extends AppCompatActivity implements StatisticalView{
    private ActivityStatisticalBinding activityStatisticalBinding;
    private StatisticalPresenter statisticalPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityStatisticalBinding = ActivityStatisticalBinding.inflate(getLayoutInflater());
        setContentView(activityStatisticalBinding.getRoot());

        statisticalPresenter = new StatisticalPresenter(this);
        statisticalPresenter.getRevenue(getApplicationContext());
    }

    @Override
    public void loadRevenueSuccess(List<Booking> list) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        activityStatisticalBinding.rcvRevenue.setLayoutManager(layoutManager);
        StatisticalAdapter adapter = new StatisticalAdapter(list);
        activityStatisticalBinding.rcvRevenue.setAdapter(adapter);
    }

    @Override
    public void loadDataFail() {
        GlobalFuntion.showToastMessage(getApplicationContext(), getString(R.string.msg_get_data_error));
    }
}