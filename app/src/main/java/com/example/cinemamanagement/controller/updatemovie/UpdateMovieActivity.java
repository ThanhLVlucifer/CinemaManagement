package com.example.cinemamanagement.controller.updatemovie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.cinemamanagement.R;
import com.example.cinemamanagement.constant.Constant;
import com.example.cinemamanagement.constant.GlobalFuntion;
import com.example.cinemamanagement.controller.AdminActivity;
import com.example.cinemamanagement.databinding.ActivityUpdateMovieBinding;
import com.example.cinemamanagement.model.Movie;
import com.example.cinemamanagement.model.Product;

public class UpdateMovieActivity extends AppCompatActivity implements UpdateMovieView {
    private ActivityUpdateMovieBinding activityUpdateMovieBinding;
    private UpdateMoviePresenter updateMoviePresenter;
    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityUpdateMovieBinding = ActivityUpdateMovieBinding.inflate(getLayoutInflater());
        setContentView(activityUpdateMovieBinding.getRoot());
        updateMoviePresenter = new UpdateMoviePresenter(this);
        getDataIntent();
        setData();
        initEvent();
    }

    private void initEvent() {
        activityUpdateMovieBinding.btnUpdateMovie.setOnClickListener(view -> {
            String name = activityUpdateMovieBinding.edtEnterName.getText().toString().trim();
            String image = activityUpdateMovieBinding.edtEnterUrlImage.getText().toString().trim();
            String genre = activityUpdateMovieBinding.edtEnterGenre.getText().toString().trim();
            String director = activityUpdateMovieBinding.edtEnterDirector.getText().toString().trim();
            String actor = activityUpdateMovieBinding.edtEnterActor.getText().toString().trim();
            String releaseDate = activityUpdateMovieBinding.edtEnterReleaseDate.getText().toString().trim();
            String duration = activityUpdateMovieBinding.edtEnterDuration.getText().toString().trim();
            String strEvaluate = activityUpdateMovieBinding.edtEnterUrlEvaluate.getText().toString().trim();
            Double evaluate = Double.parseDouble(strEvaluate);
            String rating = activityUpdateMovieBinding.edtEnterRating.getText().toString().trim();
            String strTicketPrice = activityUpdateMovieBinding.edtEnterTicketPrice.getText().toString().trim();
            int ticketPrice = Integer.parseInt(strTicketPrice);
            String strSuggest = activityUpdateMovieBinding.edtEnterSuggest.getText().toString().trim();
            boolean suggest = strSuggest.equals("true");
            String description = activityUpdateMovieBinding.edtEnterDescription.getText().toString().trim();
            String uriTrailer = activityUpdateMovieBinding.edtEnterTrailer.getText().toString().trim();

            Movie newMovie = new Movie(movie.getId(), name, image, genre, director, actor
                    , releaseDate, duration, evaluate, rating, ticketPrice, suggest, description, uriTrailer);
            updateMoviePresenter.updateMovieSuccess(getApplicationContext(), newMovie);
        });
    }

    private void setData() {
        activityUpdateMovieBinding.edtEnterName.setText(movie.getName());
        activityUpdateMovieBinding.edtEnterUrlImage.setText(movie.getImage());
        activityUpdateMovieBinding.edtEnterGenre.setText(movie.getGenre());
        activityUpdateMovieBinding.edtEnterDirector.setText(movie.getDirector());
        activityUpdateMovieBinding.edtEnterActor.setText(movie.getActor());
        activityUpdateMovieBinding.edtEnterReleaseDate.setText(movie.getReleaseDate());
        activityUpdateMovieBinding.edtEnterDuration.setText(movie.getDuration());
        String strEvaluate = String.valueOf(movie.getEvaluate());
        activityUpdateMovieBinding.edtEnterUrlEvaluate.setText(strEvaluate);
        activityUpdateMovieBinding.edtEnterRating.setText(movie.getRating());
        String strTicketPrice = String.valueOf(movie.getTicketPrice());
        activityUpdateMovieBinding.edtEnterTicketPrice.setText(strTicketPrice);
        String strSuggest = movie.isSuggest() ? "true" : "false";
        activityUpdateMovieBinding.edtEnterSuggest.setText(strSuggest);
        activityUpdateMovieBinding.edtEnterDescription.setText(movie.getDescription());
        activityUpdateMovieBinding.edtEnterTrailer.setText(movie.getTrailer());
    }

    private void getDataIntent() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            movie = (Movie) bundle.get(Constant.KEY_INTENT_MOVIE_OBJECT);
        }
    }

    @Override
    public void updateDataSuccess() {
        GlobalFuntion.showToastMessage(getApplicationContext(), getString(R.string.msg_update_data_success));
        GlobalFuntion.startActivity(getApplicationContext(), AdminActivity.class);
    }
}