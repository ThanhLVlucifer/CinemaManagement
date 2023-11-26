package com.example.cinemamanagement.controller.confirm;

import com.example.cinemamanagement.model.Product;
import com.example.cinemamanagement.model.Seat;

import java.util.List;

public interface ConfirmView {
    void loadListRoomName(List<String> list);
    void loadListScreeningRoomName(List<String> list);
    void loadListError();
    void loadListSeat(List<Seat> list);
    void colorClassificationSuccess(List<Seat> list);
    void loadListProductSuccess(List<Product> list);
    void sendBookingSuccess();

    void showing();
    void notShowing();
}
