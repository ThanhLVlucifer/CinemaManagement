package com.example.cinemamanagement.controller.userhistorybooking;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.cinemamanagement.ControllerApplication;
import com.example.cinemamanagement.model.Booking;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserHistoryBookingPresenter {
    private final UserHistoryBookingView userHistoryBookingView;

    public UserHistoryBookingPresenter(UserHistoryBookingView userHistoryBookingView) {
        this.userHistoryBookingView = userHistoryBookingView;
    }

    public void getListBooking(@NonNull Context context, String Uid) {
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
                            if (booking.getIdUser().equals(Uid)) {
                                list.add(booking);
                            }
                        }
                        userHistoryBookingView.loadListBooking(list);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        userHistoryBookingView.loadListError();
                    }
                });
    }
}
