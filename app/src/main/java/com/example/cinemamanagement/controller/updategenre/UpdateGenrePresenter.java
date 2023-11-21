package com.example.cinemamanagement.controller.updategenre;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.cinemamanagement.ControllerApplication;
import com.example.cinemamanagement.model.Genre;

public class UpdateGenrePresenter {
    private final UpdateGenreView updateGenreView;

    public UpdateGenrePresenter(UpdateGenreView updateGenreView) {
        this.updateGenreView = updateGenreView;
    }

    public void updateGenreSuccess(@NonNull Context context, Genre genre) {
        if (context == null) {
            return;
        }
        ControllerApplication.get(context).getGenreDatabaseReference()
                .child(String.valueOf(genre.getId()))
                .setValue(genre, (error, ref) -> updateGenreView.updateDataSuccess());
    }
}
