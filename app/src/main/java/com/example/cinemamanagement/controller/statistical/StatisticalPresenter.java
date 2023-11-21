package com.example.cinemamanagement.controller.statistical;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.cinemamanagement.ControllerApplication;
import com.example.cinemamanagement.model.Booking;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class StatisticalPresenter {
    private final StatisticalView statisticalView;

    public StatisticalPresenter(StatisticalView statisticalView) {
        this.statisticalView = statisticalView;
    }

    public void getRevenue(@NonNull Context context) {
        if (context == null) {
            return;
        }
        ControllerApplication.get(context).getBookingDatabaseReference()
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        List<Booking> list = new ArrayList<>();
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            Booking booking = dataSnapshot.getValue(Booking.class);
                            if (booking == null) {
                                return;
                            }
                            if (list != null) {
                                boolean flag = true;
                                for (int i = 0; i < list.size(); i++) {
                                    if (list.get(i).getNameMovie().equals(booking.getNameMovie())) {
                                        flag = false;
                                        int quantityNew = list.get(i).getTicketQuantity() + booking.getTicketQuantity();
                                        int totalTicketSalesNew = list.get(i).getTotalTicketSales() + booking.getTotalTicketSales();
                                        list.get(i).setTicketQuantity(quantityNew);
                                        list.get(i).setTotalTicketSales(totalTicketSalesNew);
                                        break;
                                    }
                                }
                                if (flag) {
                                    list.add(booking);
                                }
                            } else {
                                list.add(booking);
                            }
                        }
                        statisticalView.loadRevenueSuccess(list);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        statisticalView.loadDataFail();
                    }
                });
    }
}
