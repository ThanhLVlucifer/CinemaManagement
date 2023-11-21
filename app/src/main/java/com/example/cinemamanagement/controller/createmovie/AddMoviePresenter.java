package com.example.cinemamanagement.controller.createmovie;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.cinemamanagement.ControllerApplication;
import com.example.cinemamanagement.constant.Constant;
import com.example.cinemamanagement.constant.GlobalFuntion;
import com.example.cinemamanagement.model.Movie;
import com.example.cinemamanagement.model.Product;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class AddMoviePresenter {
    private final AddMovieView addMovieView;

    public AddMoviePresenter(AddMovieView addMovieView) {
        this.addMovieView = addMovieView;
    }

    public void getLastMovieSuccess(@NonNull Context context) {
        if (context == null) {
            return;
        }
        DatabaseReference reference = ControllerApplication.get(context).getMovieDatabaseReference();
        Query query = reference.orderByChild(Constant.CHILD_ID).limitToLast(1);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    Movie lastMovie = new Movie();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        lastMovie = dataSnapshot.getValue(Movie.class);
                    }
                    addMovieView.getLastMovieSuccess(lastMovie);
                } else {
                    addMovieView.showMessageListEmpty();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                GlobalFuntion.showToastMessage(context, error.getMessage());
            }
        });

    }

    public void sendMovie(@NonNull Context context, Movie movie) {
        if (context == null) {
            return;
        }
        ControllerApplication.get(context).getMovieDatabaseReference()
                .child(String.valueOf(movie.getId()))
                .setValue(movie, (error, ref) -> addMovieView.sendMovieSuccess());
    }
}
