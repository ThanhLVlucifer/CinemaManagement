package com.example.cinemamanagement.controller.admingenre;

import com.example.cinemamanagement.model.Genre;

import java.util.List;

public interface AdminGenreView {
    void loadListGenreSuccess(List<Genre> list);
    void loadDataError();

    void removeSuccess();
}
