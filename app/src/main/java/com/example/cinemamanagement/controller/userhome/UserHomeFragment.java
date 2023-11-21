package com.example.cinemamanagement.controller.userhome;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.example.cinemamanagement.R;
import com.example.cinemamanagement.adapter.MovieGridAdapter;
import com.example.cinemamanagement.adapter.MovieSuggestAdapter;
import com.example.cinemamanagement.constant.Constant;
import com.example.cinemamanagement.constant.GlobalFuntion;
import com.example.cinemamanagement.controller.moviedetail.MovieDetailActivity;
import com.example.cinemamanagement.databinding.FragmentUserHomeBinding;
import com.example.cinemamanagement.model.Movie;

import java.util.List;


public class UserHomeFragment extends Fragment implements UserHomeView {
    private FragmentUserHomeBinding mFragmentUserHomeBinding;
    private UserHomePresenter userHomePresenter;
    private List<Movie> movieList;
    private List<Movie> suggestList;

    private final Handler handler = new Handler();

    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (suggestList == null || suggestList.isEmpty()) {
                return;
            }
            if (mFragmentUserHomeBinding.viewpager2.getCurrentItem() == suggestList.size() - 1) {
                mFragmentUserHomeBinding.viewpager2.setCurrentItem(0);
                return;
            }
            mFragmentUserHomeBinding.viewpager2.setCurrentItem(mFragmentUserHomeBinding.viewpager2.getCurrentItem() + 1);
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentUserHomeBinding = FragmentUserHomeBinding.inflate(inflater, container, false);

        userHomePresenter = new UserHomePresenter(this);
        userHomePresenter.getListMovies(getActivity());
        return mFragmentUserHomeBinding.getRoot();
    }

    private void displayListMovieSuggest() {
        MovieSuggestAdapter suggestAdapter = new MovieSuggestAdapter(suggestList, this::goToMovieDetail);

        mFragmentUserHomeBinding.viewpager2.setAdapter(suggestAdapter);
        mFragmentUserHomeBinding.indicator3.setViewPager(mFragmentUserHomeBinding.viewpager2);

        mFragmentUserHomeBinding.viewpager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable, 3000);
            }
        });
    }

    private void displayListMovie() {
        suggestList = userHomePresenter.getListMovieSuggest(movieList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        mFragmentUserHomeBinding.rcvMoive.setLayoutManager(gridLayoutManager);
        MovieGridAdapter movieGridAdapter = new MovieGridAdapter(movieList, this::goToMovieDetail);
        mFragmentUserHomeBinding.rcvMoive.setAdapter(movieGridAdapter);
    }

    private void goToMovieDetail(Movie movie) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constant.KEY_INTENT_MOVIE_OBJECT, movie);
        GlobalFuntion.startActivity(getActivity(), MovieDetailActivity.class, bundle);
    }

    @Override
    public void loadListMovies(List<Movie> list) {
        movieList = list;
        displayListMovie();
        displayListMovieSuggest();
    }

    @Override
    public void loadListMovieError() {
        GlobalFuntion.showToastMessage(getActivity(), getString(R.string.msg_get_data_error));
    }

    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    @Override
    public void onResume() {
        super.onResume();
        handler.postDelayed(runnable, 3000);
    }
}
