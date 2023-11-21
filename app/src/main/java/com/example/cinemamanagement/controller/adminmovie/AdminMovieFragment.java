package com.example.cinemamanagement.controller.adminmovie;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.cinemamanagement.R;
import com.example.cinemamanagement.adapter.MovieAdapter;
import com.example.cinemamanagement.adapter.ProductAdminAdapter;
import com.example.cinemamanagement.constant.Constant;
import com.example.cinemamanagement.constant.GlobalFuntion;
import com.example.cinemamanagement.controller.AdminActivity;
import com.example.cinemamanagement.controller.createmovie.AddMovieActivity;
import com.example.cinemamanagement.controller.updatemovie.UpdateMovieActivity;
import com.example.cinemamanagement.controller.updateproduct.UpdateProductActivity;
import com.example.cinemamanagement.databinding.FragmentAdminMovieBinding;
import com.example.cinemamanagement.model.Movie;

import java.util.List;

public class AdminMovieFragment extends Fragment implements AdminMovieView, MovieAdapter.IClickMovieAdminListener {
    private FragmentAdminMovieBinding fragmentAdminMovieBinding;
    private AdminMoviePresenter adminMoviePresenter;
    private List<Movie> movieList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentAdminMovieBinding = FragmentAdminMovieBinding.inflate(inflater, container, false);
        adminMoviePresenter = new AdminMoviePresenter(this);
        adminMoviePresenter.getListMovie(getContext(), "");
        initEvent();


        return fragmentAdminMovieBinding.getRoot();
    }

    private void initEvent() {
        fragmentAdminMovieBinding.search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adminMoviePresenter.getListMovie(getContext(), newText);
                return true;
            }
        });
        fragmentAdminMovieBinding.fabAdd.setOnClickListener(view -> {
            GlobalFuntion.startActivity(getContext(), AddMovieActivity.class);
        });
    }

    private void displayListMovie() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        fragmentAdminMovieBinding.rcvMovie.setLayoutManager(layoutManager);

        MovieAdapter adapter = new MovieAdapter(movieList, this);
        fragmentAdminMovieBinding.rcvMovie.setAdapter(adapter);
    }

    @Override
    public void loadListMovieSuccess(List<Movie> list) {
        movieList = list;
        displayListMovie();
    }

    @Override
    public void loadDataError(String error) {
        GlobalFuntion.showToastMessage(getContext(), error);
    }

    @Override
    public void removeSuccess() {
        GlobalFuntion.showToastMessage(getContext(), getString(R.string.msg_remove_data_success));
        GlobalFuntion.startActivity(getContext(), AdminActivity.class);
    }

    @Override
    public void updateMovie(Movie movie) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constant.KEY_INTENT_MOVIE_OBJECT, movie);
        GlobalFuntion.startActivity(getContext(), UpdateMovieActivity.class, bundle);
    }

    @Override
    public void deleteMovie(Movie movie) {
        new AlertDialog.Builder(getActivity())
                .setTitle(getString(R.string.confirm_delete_movie))
                .setMessage(getString(R.string.message_delete_movie))
                .setPositiveButton(getString(R.string.delete), (dialogInterface, i) -> adminMoviePresenter.deleteMovie(getContext(), movie))
                .setNegativeButton(getString(R.string.cancel_delete), (dialogInterface, i) -> dialogInterface.dismiss())
                .show();
    }
}
