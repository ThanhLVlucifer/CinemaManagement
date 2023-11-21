package com.example.cinemamanagement.controller.creategenre;

import com.example.cinemamanagement.model.Genre;

public interface CreateGenreView {
    void getLastGenreSuccess(Genre lastGenre);
    void showMessageListEmpty();
    void sendGenreSuccess();
}
