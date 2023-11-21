package com.example.cinemamanagement.controller.updatemovie;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.cinemamanagement.ControllerApplication;
import com.example.cinemamanagement.model.Movie;
import com.example.cinemamanagement.model.Product;

public class UpdateMoviePresenter {
    private final UpdateMovieView updateMovieView;

    public UpdateMoviePresenter(UpdateMovieView updateMovieView) {
        this.updateMovieView = updateMovieView;
    }

    public void updateMovieSuccess(@NonNull Context context, Movie movie) {
        if (context == null) {
            return;
        }
        ControllerApplication.get(context).getMovieDatabaseReference()
                .child(String.valueOf(movie.getId()))
                .setValue(movie, (error, ref) -> updateMovieView.updateDataSuccess());
    }
}
