package com.example.cinemamanagement.controller.trailer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.media3.common.MediaItem;
import androidx.media3.exoplayer.ExoPlayer;

import android.os.Bundle;

import com.example.cinemamanagement.R;
import com.example.cinemamanagement.constant.Constant;
import com.example.cinemamanagement.databinding.ActivityTrailerBinding;
import com.example.cinemamanagement.model.Movie;

public class TrailerActivity extends AppCompatActivity implements TrailerView {
    private ActivityTrailerBinding activityTrailerBinding;
    private TrailerPresenter trailerPresenter;
    private ExoPlayer exoPlayer;

    private Movie movie;

    private String uriTrailer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityTrailerBinding = ActivityTrailerBinding.inflate(getLayoutInflater());
        setContentView(activityTrailerBinding.getRoot());

        trailerPresenter = new TrailerPresenter(this);
        getData();
        exoPlayer = new ExoPlayer.Builder(this).build();
        activityTrailerBinding.playerView.setPlayer(exoPlayer);

        // Build the media item.
        MediaItem mediaItem = MediaItem.fromUri(uriTrailer);
// Set the media item to be played.
        exoPlayer.setMediaItem(mediaItem);
// Prepare the player.
        exoPlayer.prepare();
// Start the playback.
        exoPlayer.play();
    }

    private void getData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            uriTrailer = (String) bundle.get(Constant.KEY_INTENT_URI);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        exoPlayer.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        exoPlayer.play();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        exoPlayer.pause();
    }
}