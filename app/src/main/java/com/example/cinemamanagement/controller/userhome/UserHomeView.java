package com.example.cinemamanagement.controller.userhome;

import com.example.cinemamanagement.model.Movie;

import java.util.List;

public interface UserHomeView {
    void loadListMovies(List<Movie> list);
    void loadListMovieError();
}
