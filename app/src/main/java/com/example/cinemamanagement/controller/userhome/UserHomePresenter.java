package com.example.cinemamanagement.controller.userhome;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.cinemamanagement.ControllerApplication;
import com.example.cinemamanagement.model.Movie;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserHomePresenter {

    private final UserHomeView userHomeView;

    public UserHomePresenter(UserHomeView userHomeView) {
        this.userHomeView = userHomeView;
    }

    public void getListMovies(@NonNull Context context) {
        if (context == null) {
            return;
        }
        ControllerApplication.get(context).getMovieDatabaseReference()
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        List<Movie> movieList = new ArrayList<>();
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            movieList.add(dataSnapshot.getValue(Movie.class));
                        }
                        userHomeView.loadListMovies(movieList);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        userHomeView.loadListMovieError();
                    }
                });
    }

    public List<Movie> getListMovieSuggest(List<Movie> listMovie) {
        List<Movie> list = new ArrayList<>();
        if (listMovie == null || listMovie.isEmpty()) {
            return list;
        }
        for (Movie movie : listMovie) {
            if (movie.isSuggest()) {
                list.add(movie);
            }
        }
        return list;
    }
}
