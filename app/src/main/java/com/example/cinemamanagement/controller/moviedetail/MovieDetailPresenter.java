package com.example.cinemamanagement.controller.moviedetail;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.cinemamanagement.ControllerApplication;
import com.example.cinemamanagement.constant.Constant;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MovieDetailPresenter {
    private final MovieDetailView movieDetailView;

    public MovieDetailPresenter(MovieDetailView movieDetailView) {
        this.movieDetailView = movieDetailView;
    }

}
