package com.example.cinemamanagement.controller.adminhistory;

import com.example.cinemamanagement.model.Booking;

import java.util.List;

public interface AdminHistoryView {

    void loadAllListBooking(List<Booking> list);
    void loadListError();
}
