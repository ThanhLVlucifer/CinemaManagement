package com.example.cinemamanagement.controller.createmovie;

import com.example.cinemamanagement.model.Movie;
import com.example.cinemamanagement.model.Product;

public interface AddMovieView {
    void getLastMovieSuccess(Movie lastMovie);
    void showMessageListEmpty();
    void sendMovieSuccess();
}
