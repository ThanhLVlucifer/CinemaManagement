package com.example.cinemamanagement.controller.moviedetail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.media3.common.MediaItem;
import androidx.media3.exoplayer.ExoPlayer;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.example.cinemamanagement.R;
import com.example.cinemamanagement.constant.Constant;
import com.example.cinemamanagement.constant.GlobalFuntion;
import com.example.cinemamanagement.controller.confirm.ConfirmActivity;
import com.example.cinemamanagement.controller.trailer.TrailerActivity;
import com.example.cinemamanagement.databinding.ActivityMovieDetailBinding;
import com.example.cinemamanagement.model.Movie;
import com.example.cinemamanagement.utils.GlideUtils;


import java.util.List;

public class MovieDetailActivity extends AppCompatActivity implements MovieDetailView {
    private ActivityMovieDetailBinding activityMovieDetailBinding;
    private MovieDetailPresenter movieDetailPresenter;
    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMovieDetailBinding = ActivityMovieDetailBinding.inflate(getLayoutInflater());
        setContentView(activityMovieDetailBinding.getRoot());

        movieDetailPresenter = new MovieDetailPresenter(this);
        getData();
        setData();
        activityMovieDetailBinding.layoutTrailer.setOnClickListener(view -> {
            sendUriIntent();
        });
        activityMovieDetailBinding.tvBooking.setOnClickListener(view -> {
            sendDataIntent();
        });
    }

    private void setData() {
        activityMovieDetailBinding.tvName.setText(movie.getName());
        GlideUtils.loadUrl(movie.getImage(), activityMovieDetailBinding.img);
        activityMovieDetailBinding.tvMovieGenre.setText(movie.getGenre());
        activityMovieDetailBinding.tvMovieDirector.setText(movie.getDirector());
        activityMovieDetailBinding.tvMovieActor.setText(movie.getActor());
        activityMovieDetailBinding.tvMovieReleaseDate.setText(movie.getReleaseDate());
        activityMovieDetailBinding.tvMovieDuration.setText(movie.getDuration());
        String strEvaluate = String.valueOf(movie.getEvaluate());
        activityMovieDetailBinding.tvMovieEvaluate.setText(strEvaluate);
        activityMovieDetailBinding.tvMovieRating.setText(movie.getRating());
        String strTicketPrice = String.valueOf(movie.getTicketPrice()) + Constant.CURRENCY_USD + Constant.PER_ONE_TICKET;
        activityMovieDetailBinding.tvMovieTicketPrice.setText(strTicketPrice);
        activityMovieDetailBinding.tvMovieDesciption.setText(movie.getDescription());

        GlideUtils.loadUrl(movie.getImage(), activityMovieDetailBinding.imgTrailer);
    }


    private void getData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            movie = (Movie) bundle.get(Constant.KEY_INTENT_MOVIE_OBJECT);
        }
    }

    private void sendDataIntent() {
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constant.KEY_INTENT_MOVIE_OBJECT, movie);
        GlobalFuntion.startActivity(getApplicationContext(), ConfirmActivity.class, bundle);
    }

    private void sendUriIntent() {
        Bundle bundle = new Bundle();
        bundle.putString(Constant.KEY_INTENT_URI, movie.getTrailer());
        GlobalFuntion.startActivity(getApplicationContext(), TrailerActivity.class, bundle);
    }

}