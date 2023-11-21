package com.example.cinemamanagement.controller.adminmovie;

import com.example.cinemamanagement.model.Movie;
import com.example.cinemamanagement.model.Product;

import java.util.List;

public interface AdminMovieView {
    void loadListMovieSuccess(List<Movie> list);

    void loadDataError(String error);

    void removeSuccess();
}
