package com.example.cinemamanagement.controller.creategenre;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.cinemamanagement.ControllerApplication;
import com.example.cinemamanagement.constant.Constant;
import com.example.cinemamanagement.constant.GlobalFuntion;
import com.example.cinemamanagement.model.Genre;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class CreateGenrePresenter {
    private final CreateGenreView createGenreView;

    public CreateGenrePresenter(CreateGenreView createGenreView) {
        this.createGenreView = createGenreView;
    }


    public void getLastGenreSuccess(@NonNull Context context) {
        if (context == null) {
            return;
        }
        DatabaseReference reference = ControllerApplication.get(context).getGenreDatabaseReference();
        Query query = reference.orderByChild(Constant.CHILD_ID).limitToLast(1);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    Genre lastGenre = new Genre();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        lastGenre = dataSnapshot.getValue(Genre.class);
                    }
                    createGenreView.getLastGenreSuccess(lastGenre);
                } else {
                    createGenreView.showMessageListEmpty();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                GlobalFuntion.showToastMessage(context, error.getMessage());
            }
        });

    }

    public void sendGenre(@NonNull Context context, Genre genre) {
        if (context == null) {
            return;
        }
        ControllerApplication.get(context).getGenreDatabaseReference()
                .child(String.valueOf(genre.getId()))
                .setValue(genre, (error, ref) -> createGenreView.sendGenreSuccess());
    }

}
