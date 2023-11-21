package com.example.cinemamanagement.controller.adminmovie;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.cinemamanagement.ControllerApplication;
import com.example.cinemamanagement.constant.GlobalFuntion;
import com.example.cinemamanagement.model.Movie;
import com.example.cinemamanagement.utils.GlideUtils;
import com.example.cinemamanagement.utils.StringUtil;
import com.example.cinemamanagement.utils.Utils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdminMoviePresenter {
    private final AdminMovieView adminMovieView;

    public AdminMoviePresenter(AdminMovieView adminMovieView) {
        this.adminMovieView = adminMovieView;
    }

    public void deleteMovie(@NonNull Context context, Movie movie) {
        if (context == null) {
            return;
        }
        ControllerApplication.get(context).getMovieDatabaseReference()
                .child(String.valueOf(movie.getId()))
                .removeValue((error, ref) -> adminMovieView.removeSuccess());

    }

    public void getListMovie(@NonNull Context context, String key) {
        if (context == null) {
            return;
        }
        ControllerApplication.get(context).getMovieDatabaseReference()
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        List<Movie> movieList = new ArrayList<>();
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            Movie movie = dataSnapshot.getValue(Movie.class);
                            if (movie == null) {
                                return;
                            }
                            if (StringUtil.isEmpty(key)) {
                                movieList.add(0, movie);
                            } else {
                                if (GlobalFuntion.getTextSearch(movie.getName()).toLowerCase().trim()
                                        .contains(GlobalFuntion.getTextSearch(key).toLowerCase().trim())) {
                                    movieList.add(0, movie);
                                }
                            }
                        }
                        adminMovieView.loadListMovieSuccess(movieList);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        adminMovieView.loadDataError(error.getMessage());
                    }
                });
    }
}
