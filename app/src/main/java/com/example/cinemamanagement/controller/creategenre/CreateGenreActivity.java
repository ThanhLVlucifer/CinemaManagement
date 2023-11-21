package com.example.cinemamanagement.controller.creategenre;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.cinemamanagement.R;
import com.example.cinemamanagement.constant.GlobalFuntion;
import com.example.cinemamanagement.controller.AdminActivity;
import com.example.cinemamanagement.databinding.ActivityCreateGenreBinding;
import com.example.cinemamanagement.model.Genre;

public class CreateGenreActivity extends AppCompatActivity implements CreateGenreView {
    private ActivityCreateGenreBinding activityCreateGenreBinding;
    private CreateGenrePresenter createGenrePresenter;
    private Genre genre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityCreateGenreBinding = ActivityCreateGenreBinding.inflate(getLayoutInflater());
        setContentView(activityCreateGenreBinding.getRoot());
        createGenrePresenter = new CreateGenrePresenter(this);
        genre = new Genre();
        createGenrePresenter.getLastGenreSuccess(getApplicationContext());
        activityCreateGenreBinding.btnAddGenre.setOnClickListener(view -> {
            String name = activityCreateGenreBinding.edtEnterName.getText().toString().trim();
            String url = activityCreateGenreBinding.edtEnterUrlImage.getText().toString().trim();
            String description = activityCreateGenreBinding.edtEnterDescription.getText().toString().trim();
            genre.setName(name);
            genre.setImage(url);
            genre.setDescription(description);
            createGenrePresenter.sendGenre(getApplicationContext(), genre);
        });
    }

    @Override
    public void getLastGenreSuccess(Genre lastGenre) {
        genre.setId(lastGenre.getId() + 1);
    }

    @Override
    public void showMessageListEmpty() {
        genre.setId(0);
        GlobalFuntion.showToastMessage(getApplicationContext(), getString(R.string.msg_list_empty_and_add_first_genre));
    }

    @Override
    public void sendGenreSuccess() {
        GlobalFuntion.showToastMessage(getApplicationContext(), getString(R.string.msg_add_data_success));
        GlobalFuntion.startActivity(getApplicationContext(), AdminActivity.class);
    }
}