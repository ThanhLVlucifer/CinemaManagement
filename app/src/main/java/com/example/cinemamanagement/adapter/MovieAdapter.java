package com.example.cinemamanagement.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinemamanagement.databinding.ItemMovieAdminBinding;
import com.example.cinemamanagement.model.Movie;
import com.example.cinemamanagement.utils.GlideUtils;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private List<Movie> movieList;
    private IClickMovieAdminListener iClickMovieAdminListener;

    public MovieAdapter(List<Movie> movieList, IClickMovieAdminListener iClickMovieAdminListener) {
        this.movieList = movieList;
        this.iClickMovieAdminListener = iClickMovieAdminListener;
    }

    public interface IClickMovieAdminListener {
        void updateMovie(Movie movie);

        void deleteMovie(Movie movie);
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMovieAdminBinding itemMovieAdminBinding = ItemMovieAdminBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MovieViewHolder(itemMovieAdminBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movieList.get(position);
        if (movie == null) {
            return;
        }
        GlideUtils.loadUrl(movie.getImage(), holder.itemMovieAdminBinding.img);
        holder.itemMovieAdminBinding.tvName.setText(movie.getName());
        holder.itemMovieAdminBinding.imgUpdate.setOnClickListener(view -> iClickMovieAdminListener.updateMovie(movie));
        holder.itemMovieAdminBinding.imgDelete.setOnClickListener(view -> iClickMovieAdminListener.deleteMovie(movie));
    }

    @Override
    public int getItemCount() {
        return null == movieList ? 0 : movieList.size();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        private ItemMovieAdminBinding itemMovieAdminBinding;

        public MovieViewHolder(@NonNull ItemMovieAdminBinding itemMovieAdminBinding) {
            super(itemMovieAdminBinding.getRoot());
            this.itemMovieAdminBinding = itemMovieAdminBinding;
        }
    }
}
