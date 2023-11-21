package com.example.cinemamanagement.controller.userhistorybooking;

import com.example.cinemamanagement.model.Booking;

import java.util.List;

public interface UserHistoryBookingView {

    void loadListBooking(List<Booking> list);
    void loadListError();
}
