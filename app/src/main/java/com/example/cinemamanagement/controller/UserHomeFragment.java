package com.example.cinemamanagement.controller;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.cinemamanagement.adapter.MovieGridAdapter;
import com.example.cinemamanagement.constant.Constant;
import com.example.cinemamanagement.constant.GlobalFuntion;
import com.example.cinemamanagement.databinding.FragmentUserHomeBinding;
import com.example.cinemamanagement.model.Movie;
import com.example.cinemamanagement.utils.StringUtil;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class UserHomeFragment extends Fragment {
    private FragmentUserHomeBinding mFragmentUserHomeBinding;
    private List<Movie> mListMovie;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentUserHomeBinding = FragmentUserHomeBinding.inflate(inflater,container,false);
//        getListAllListMovieFromFirebase(getActivity(), "");
        mListMovie = getList();
        if(mListMovie==null) System.out.println("rong");
        for(Movie movie: mListMovie){
            System.out.println(movie.toString());
        }
        System.out.println("yes");
        return mFragmentUserHomeBinding.getRoot();
    }

    public List<Movie> getList() {
        List<Movie> list = new ArrayList<>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("movie");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Movie movie = dataSnapshot.getValue(Movie.class);
                    list.add(movie);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return list;
    }

    public void getListAllListMovieFromFirebase(Context context, String key) {
        if (context == null) {
            return;
        }
        ControllerApplication.get(context).getMoiveDatabaseReference()
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        List<Movie> list = new ArrayList<>();
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            Movie movie = dataSnapshot.getValue(Movie.class);
                            if (movie == null) {
                                return;
                            }

                            if (StringUtil.isEmpty(key)) {
                                list.add(0, movie);
                            } else {
                                if (GlobalFuntion.getTextSearch(movie.getName()).toLowerCase().trim()
                                        .contains(GlobalFuntion.getTextSearch(key).toLowerCase().trim())) {
                                    list.add(0, movie);
                                }
                            }
                        }
                        System.out.println("yes");
                        mListMovie = list;
                        displayAllListMovie();
//                        mHomeMVPView.loadListFoodSuccess(list);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
//                        mHomeMVPView.loadListFoodError();
                        System.out.println("no");
                    }
                });
    }

    private void displayAllListMovie() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        mFragmentUserHomeBinding.rcvMoive.setLayoutManager(gridLayoutManager);

        MovieGridAdapter movieGridAdapter = new MovieGridAdapter(mListMovie, this::goToMoiveDetail);
        mFragmentUserHomeBinding.rcvMoive.setAdapter(movieGridAdapter);


    }

    private void goToMoiveDetail(@NonNull Movie movie) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constant.KEY_INTENT_FOOD_OBJECT, movie);
        GlobalFuntion.startActivity(getActivity(), MovieDetailActivity.class, bundle);
    }
}
