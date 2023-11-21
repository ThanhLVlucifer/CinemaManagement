package com.example.cinemamanagement.controller.admingenre;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.cinemamanagement.ControllerApplication;
import com.example.cinemamanagement.constant.Constant;
import com.example.cinemamanagement.constant.GlobalFuntion;
import com.example.cinemamanagement.model.Genre;
import com.example.cinemamanagement.model.Movie;
import com.example.cinemamanagement.utils.StringUtil;
import com.example.cinemamanagement.utils.Utils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdminGenrePresenter {
    private final AdminGenreView adminGenreView;

    public AdminGenrePresenter(AdminGenreView adminGenreView) {
        this.adminGenreView = adminGenreView;
    }

    // don't handle
    public void updateMovieDatabase(@NonNull Context context, Genre genre){
        if (context == null) {
            return;
        }
        ControllerApplication.get(context).getMovieDatabaseReference()
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        List<Movie> list = new ArrayList<>();
                        for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                            list.add(dataSnapshot.getValue(Movie.class));
                        }
                        for(Movie movie: list){
                            ControllerApplication.get(context).getMovieDatabaseReference()
                                    .child(String.valueOf(movie.getId()))
                                    .child(Constant.GENREID_COLUMN)
                                    .addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            List<Integer> listId = new ArrayList<>();
                                            for(DataSnapshot idSnapshot: snapshot.getChildren()){
                                                listId.add(idSnapshot.getValue(Integer.class));
                                            }
                                            Integer idGenre = genre.getId();
                                            if(listId.contains(idGenre)){

                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        adminGenreView.loadDataError();
                    }
                });
    }
    public void deleteGenre(@NonNull Context context, Genre genre) {
        if (context == null) {
            return;
        }
        ControllerApplication.get(context).getGenreDatabaseReference()
                .child(String.valueOf(genre.getId()))
                .removeValue((error, ref) -> adminGenreView.removeSuccess());
    }

    public void getListGenre(@NonNull Context context, String key) {
        if (context == null) {
            return;
        }
        ControllerApplication.get(context).getGenreDatabaseReference()
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        List<Genre> list = new ArrayList<>();
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            Genre genre = dataSnapshot.getValue(Genre.class);
                            if (genre == null) {
                                return;
                            }
                            if (StringUtil.isEmpty(key)) {
                                list.add(0, genre);
                            } else {
                                if (GlobalFuntion.getTextSearch(genre.getName()).toLowerCase().trim()
                                        .contains(GlobalFuntion.getTextSearch(key).toLowerCase().trim())) {
                                    list.add(0, genre);
                                }
                            }
                        }
                        adminGenreView.loadListGenreSuccess(list);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        adminGenreView.loadDataError();
                    }
                });
    }
}
