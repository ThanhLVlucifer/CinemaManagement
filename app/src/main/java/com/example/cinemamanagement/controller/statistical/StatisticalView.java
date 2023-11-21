package com.example.cinemamanagement.controller.statistical;

import com.example.cinemamanagement.model.Booking;

import java.util.List;

public interface StatisticalView {
    void loadRevenueSuccess(List<Booking> list);
    void loadDataFail();
}
