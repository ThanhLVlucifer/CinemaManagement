package com.example.cinemamanagement.controller.confirm;

import android.content.Context;
import android.graphics.Color;

import androidx.annotation.NonNull;

import com.example.cinemamanagement.ControllerApplication;
import com.example.cinemamanagement.constant.Constant;
import com.example.cinemamanagement.model.Booking;
import com.example.cinemamanagement.model.Product;
import com.example.cinemamanagement.model.Seat;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ConfirmPresenter {
    private final ConfirmView confirmView;

    public ConfirmPresenter(ConfirmView confirmView) {
        this.confirmView = confirmView;
    }

    public void checkSchedule(@NonNull Context context, String movieName, int idRoom, int idScreening) {
        if (context == null) {
            return;
        }
        ControllerApplication.get(context).getScheduleDatabaseReference()
                .child(String.valueOf(idRoom))
                .child(String.valueOf(idScreening))
                .child(Constant.MOVIE_COLUMN)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String str = snapshot.getValue(String.class);
                        if (str.equals(movieName)) {
                            confirmView.showing();
                        } else {
                            confirmView.notShowing();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        confirmView.loadListError();
                    }
                });

    }

    public void updateTicket(@NonNull Context context, List<Seat> selectSeat, Seat seat) {
        if (context == null) {
            return;
        }
        if (seat.getColor() == Color.WHITE) {
            seat.setColor(Color.GREEN);
            selectSeat.add(seat);
        } else if (seat.getColor() == Color.GREEN) {
            seat.setColor(Color.WHITE);
            selectSeat.remove(seat);
        }
    }

    public void updateCart(@NonNull Context context, List<Product> chooseProduct, Product product) {
        if (context == null) {
            return;
        }
        int count = product.getCount();
        if (count == 0) {
            for (int i = 0; i < chooseProduct.size(); i++) {
                if (chooseProduct.get(i).getId() == product.getId()) {
                    chooseProduct.remove(i);
                }
            }
        } else {
            boolean flag = true;
            for (int i = 0; i < chooseProduct.size(); i++) {
                if (chooseProduct.get(i).getId() == product.getId()) {
                    chooseProduct.set(i, product);
                    flag = false;
                }
            }
            if (flag) {
                chooseProduct.add(product);
            }
        }
    }

    public void getListProduct(@NonNull Context context) {
        if (context == null) {
            return;
        }
        ControllerApplication.get(context).getProductDatabaseReference()
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        List<Product> list = new ArrayList<>();
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            list.add(dataSnapshot.getValue(Product.class));
                        }
                        confirmView.loadListProductSuccess(list);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        confirmView.loadListError();
                    }
                });
    }

    public void colorClassificationOfSeatingList(@NonNull Context context, int roomId, int screeningId, List<Seat> list) {
        if (context == null) {
            return;
        }
        ControllerApplication.get(context).getLookupDatabaseReference()
                .child(String.valueOf(roomId))
                .child(String.valueOf(screeningId))
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        int position = 0;
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            String status = dataSnapshot.getValue(String.class);
                            if (status.equals(Constant.UNAVAILABLE_STATUS)) {
                                list.get(position).setColor(Color.GRAY);
                            } else {
                                list.get(position).setColor(Color.WHITE);
                            }
                            position++;
                        }

                        confirmView.colorClassificationSuccess(list);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        confirmView.loadListError();
                    }
                });
    }

    public void getListSeat(@NonNull Context context) {
        if (context == null) {
            return;
        }
        ControllerApplication.get(context).getSeatDatabaseReference()
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        List<Seat> list = new ArrayList<>();
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            list.add(dataSnapshot.getValue(Seat.class));
                        }
                        confirmView.loadListSeat(list);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        confirmView.loadListError();
                    }
                });
    }

    public void getListRoomName(@NonNull Context context) {
        if (context == null) {
            return;
        }
        ControllerApplication.get(context).getRoomDatabaseReference()
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        List<String> list = new ArrayList<>();
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            list.add(dataSnapshot.child(Constant.NAME_COLUMN).getValue(String.class));
                        }
                        confirmView.loadListRoomName(list);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        confirmView.loadListError();
                    }
                });
    }

    public void getListScreeningName(@NonNull Context context) {
        if (context == null) {
            return;
        }
        ControllerApplication.get(context).getScreeningDatabaseReference()
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        List<String> list = new ArrayList<>();
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            list.add(dataSnapshot.child(Constant.START_TIME_COLUMN).getValue(String.class));
                        }
                        confirmView.loadListScreeningRoomName(list);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        confirmView.loadListError();
                    }
                });
    }

    public void sendBookingToFirebase(Context context, long id, @NonNull Booking booking) {
        ControllerApplication.get(context).getBookingDatabaseReference()
                .child(String.valueOf(id))
                .setValue(booking, (error, ref) -> confirmView.sendBookingSuccess());
    }

    public void updateLookup(Context context, List<Seat> list, int x, int y) {
        for (Seat seat : list) {
            ControllerApplication.get(context).getLookupDatabaseReference()
                    .child(String.valueOf(x))
                    .child(String.valueOf(y))
                    .child(String.valueOf(seat.getId()))
                    .setValue(Constant.UNAVAILABLE_STATUS);
        }
    }
}
