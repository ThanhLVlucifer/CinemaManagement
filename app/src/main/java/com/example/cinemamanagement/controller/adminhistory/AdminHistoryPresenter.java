package com.example.cinemamanagement.controller.adminhistory;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.cinemamanagement.ControllerApplication;
import com.example.cinemamanagement.constant.GlobalFuntion;
import com.example.cinemamanagement.controller.userhistorybooking.UserHistoryBookingView;
import com.example.cinemamanagement.model.Booking;
import com.example.cinemamanagement.utils.StringUtil;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdminHistoryPresenter {
    private final AdminHistoryView adminHistoryView;

    public AdminHistoryPresenter(AdminHistoryView adminHistoryView) {
        this.adminHistoryView = adminHistoryView;
    }

    public void getAllListBooking(@NonNull Context context, String key) {
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
                            if (StringUtil.isEmpty(key)) {
                                list.add(0, booking);
                            } else {
                                if (GlobalFuntion.getTextSearch(String.valueOf(booking.getId()).toLowerCase().trim())
                                        .contains(GlobalFuntion.getTextSearch(key.toLowerCase().trim()))) {
                                    list.add(0, booking);
                                }
                            }
                        }
                        adminHistoryView.loadAllListBooking(list);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        adminHistoryView.loadListError();
                    }
                });
    }

}
