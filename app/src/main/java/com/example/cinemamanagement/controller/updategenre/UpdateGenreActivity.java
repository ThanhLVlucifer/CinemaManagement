package com.example.cinemamanagement.controller.updategenre;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.cinemamanagement.R;
import com.example.cinemamanagement.constant.Constant;
import com.example.cinemamanagement.constant.GlobalFuntion;
import com.example.cinemamanagement.controller.AdminActivity;
import com.example.cinemamanagement.databinding.ActivityUpdateGenreBinding;
import com.example.cinemamanagement.model.Genre;
import com.example.cinemamanagement.model.Movie;

public class UpdateGenreActivity extends AppCompatActivity implements UpdateGenreView {
    private ActivityUpdateGenreBinding activityUpdateGenreBinding;
    private UpdateGenrePresenter updateGenrePresenter;
    private Genre genre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityUpdateGenreBinding = ActivityUpdateGenreBinding.inflate(getLayoutInflater());
        setContentView(activityUpdateGenreBinding.getRoot());

        updateGenrePresenter = new UpdateGenrePresenter(this);
        getDataIntent();
        setData();
        initEvent();
    }

    private void initEvent() {
        activityUpdateGenreBinding.btnUpdateGenre.setOnClickListener(view -> {
            String name = activityUpdateGenreBinding.edtEnterName.getText().toString().trim();
            String url = activityUpdateGenreBinding.edtEnterUrlImage.getText().toString().trim();
            String description = activityUpdateGenreBinding.edtEnterDescription.getText().toString().trim();
            Genre newGenre = new Genre(genre.getId(), name, url, description);
            updateGenrePresenter.updateGenreSuccess(getApplicationContext(), newGenre);
        });
    }

    private void setData() {
        activityUpdateGenreBinding.edtEnterName.setText(genre.getName());
        activityUpdateGenreBinding.edtEnterUrlImage.setText(genre.getImage());
        activityUpdateGenreBinding.edtEnterDescription.setText(genre.getDescription());
    }

    private void getDataIntent() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            genre = (Genre) bundle.get(Constant.KEY_INTENT_GENRE_OBJECT);
        }
    }

    @Override
    public void updateDataSuccess() {
        GlobalFuntion.showToastMessage(getApplicationContext(), getString(R.string.msg_update_data_success));
        GlobalFuntion.startActivity(getApplicationContext(), AdminActivity.class);
    }
}