package com.example.cinemamanagement.controller.createmovie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.cinemamanagement.R;
import com.example.cinemamanagement.constant.GlobalFuntion;
import com.example.cinemamanagement.controller.AdminActivity;
import com.example.cinemamanagement.databinding.ActivityAddMovieBinding;
import com.example.cinemamanagement.model.Movie;

public class AddMovieActivity extends AppCompatActivity implements AddMovieView {
    private ActivityAddMovieBinding activityAddMovieBinding;
    private AddMoviePresenter addMoviePresenter;
    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAddMovieBinding = ActivityAddMovieBinding.inflate(getLayoutInflater());
        setContentView(activityAddMovieBinding.getRoot());

        addMoviePresenter = new AddMoviePresenter(this);
        movie = new Movie();
        addMoviePresenter.getLastMovieSuccess(getApplicationContext());
        activityAddMovieBinding.btnAddMovie.setOnClickListener(view -> {
            String name = activityAddMovieBinding.edtEnterName.getText().toString().trim();
            String image = activityAddMovieBinding.edtEnterUrlImage.getText().toString().trim();
            String genre = activityAddMovieBinding.edtEnterGenre.getText().toString().trim();
            String director = activityAddMovieBinding.edtEnterDirector.getText().toString().trim();
            String actor = activityAddMovieBinding.edtEnterActor.getText().toString().trim();
            String releaseDate = activityAddMovieBinding.edtEnterReleaseDate.getText().toString().trim();
            String duration = activityAddMovieBinding.edtEnterDuration.getText().toString().trim();
            String strEvaluate = activityAddMovieBinding.edtEnterUrlEvaluate.getText().toString().trim();
            Double evaluate = Double.parseDouble(strEvaluate);
            String rating = activityAddMovieBinding.edtEnterRating.getText().toString().trim();
            String strTicketPrice = activityAddMovieBinding.edtEnterTicketPrice.getText().toString().trim();
            int ticketPrice = Integer.parseInt(strTicketPrice);
            String strSuggest = activityAddMovieBinding.edtEnterSuggest.getText().toString().trim();
            boolean suggest = strSuggest.equals("true");
            String description = activityAddMovieBinding.edtEnterDescription.getText().toString().trim();
            String uriTrailer = activityAddMovieBinding.edtEnterTrailer.getText().toString().trim();

            movie.setName(name);
            movie.setImage(image);
            movie.setGenre(genre);
            movie.setDirector(director);
            movie.setActor(actor);
            movie.setReleaseDate(releaseDate);
            movie.setDuration(duration);
            movie.setEvaluate(evaluate);
            movie.setRating(rating);
            movie.setTicketPrice(ticketPrice);
            movie.setSuggest(suggest);
            movie.setDescription(description);
            movie.setTrailer(uriTrailer);

            addMoviePresenter.sendMovie(getApplicationContext(), movie);
        });

    }

    @Override
    public void getLastMovieSuccess(Movie lastMovie) {
        movie.setId(lastMovie.getId() + 1);
    }

    @Override
    public void showMessageListEmpty() {
        movie.setId(0);
        GlobalFuntion.showToastMessage(getApplicationContext(), getString(R.string.msg_list_empty_and_add_first_genre));
    }

    @Override
    public void sendMovieSuccess() {
        GlobalFuntion.showToastMessage(getApplicationContext(), getString(R.string.msg_add_data_success));
        GlobalFuntion.startActivity(getApplicationContext(), AdminActivity.class);
    }
}