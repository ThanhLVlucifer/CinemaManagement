package com.example.cinemamanagement.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinemamanagement.databinding.ItemMovieGridBinding;
import com.example.cinemamanagement.databinding.ItemMovieSuggestBinding;
import com.example.cinemamanagement.listener.IOnClickMovieItemListener;
import com.example.cinemamanagement.model.Movie;
import com.example.cinemamanagement.utils.GlideUtils;

import java.util.List;

public class MovieSuggestAdapter extends RecyclerView.Adapter<MovieSuggestAdapter.MovieSuggestViewHolder> {

    private final List<Movie> mListMovies;
    public final IOnClickMovieItemListener iOnClickMovieItemListener;

    public MovieSuggestAdapter(List<Movie> mListMovies, IOnClickMovieItemListener iOnClickMovieItemListener) {
        this.mListMovies = mListMovies;
        this.iOnClickMovieItemListener = iOnClickMovieItemListener;
    }

    @NonNull
    @Override
    public MovieSuggestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMovieSuggestBinding itemMovieSuggestBinding = ItemMovieSuggestBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MovieSuggestViewHolder(itemMovieSuggestBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieSuggestViewHolder holder, int position) {
        Movie movie = mListMovies.get(position);
        if (movie == null) {
            return;
        }
        GlideUtils.loadUrl(movie.getImage(), holder.itemMovieSuggestBinding.imgMoive);
        holder.itemMovieSuggestBinding.tvMovieEvaluate.setText(String.valueOf(movie.getEvaluate()));
        holder.itemMovieSuggestBinding.tvMovieRating.setText(movie.getRating());
        holder.itemMovieSuggestBinding.layoutItem.setOnClickListener(view -> iOnClickMovieItemListener.onClickItemMovie(movie));
    }

    @Override
    public int getItemCount() {
        return null == mListMovies ? 0 : mListMovies.size();
    }

    public static class MovieSuggestViewHolder extends RecyclerView.ViewHolder {

        private final ItemMovieSuggestBinding itemMovieSuggestBinding;

        public MovieSuggestViewHolder(ItemMovieSuggestBinding itemMovieSuggestBinding) {
            super(itemMovieSuggestBinding.getRoot());
            this.itemMovieSuggestBinding = itemMovieSuggestBinding;
        }
    }
}
