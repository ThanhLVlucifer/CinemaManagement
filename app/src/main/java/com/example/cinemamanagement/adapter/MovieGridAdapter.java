package com.example.cinemamanagement.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinemamanagement.databinding.ItemMovieGridBinding;
import com.example.cinemamanagement.listener.IOnClickMovieItemListener;
import com.example.cinemamanagement.model.Movie;
import com.example.cinemamanagement.utils.GlideUtils;
//import com.example.foodorder.constant.Constant;
//import com.example.foodorder.databinding.ItemFoodGridBinding;
//import com.example.foodorder.listener.IOnClickFoodItemListener;
//import com.example.foodorder.model.Food;
//import com.example.foodorder.utils.GlideUtils;

import java.util.List;

public class MovieGridAdapter extends RecyclerView.Adapter<MovieGridAdapter.MovieGridViewHolder> {

    private final List<Movie> mListMovies;
    public final IOnClickMovieItemListener iOnClickMovieItemListener;

    public MovieGridAdapter(List<Movie> mListMovies, IOnClickMovieItemListener iOnClickMovieItemListener) {
        this.mListMovies = mListMovies;
        this.iOnClickMovieItemListener = iOnClickMovieItemListener;
    }

    @NonNull
    @Override
    public MovieGridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMovieGridBinding itemMovieGridBinding = ItemMovieGridBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MovieGridViewHolder(itemMovieGridBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieGridViewHolder holder, int position) {
        Movie movie = mListMovies.get(position);
        if (movie == null) {
            return;
        }
        GlideUtils.loadUrl(movie.getImage(), holder.mItemMovieGridBinding.imgMoive);
        holder.mItemMovieGridBinding.tvMovieEvaluate.setText(String.valueOf(movie.getEvaluate()));
        holder.mItemMovieGridBinding.tvMovieRating.setText(movie.getRating());
        holder.mItemMovieGridBinding.layoutItem.setOnClickListener(view -> iOnClickMovieItemListener.onClickItemMovie(movie));
    }

    @Override
    public int getItemCount() {
        return null == mListMovies ? 0 : mListMovies.size();
    }

    public static class MovieGridViewHolder extends RecyclerView.ViewHolder {

        private final ItemMovieGridBinding mItemMovieGridBinding;

        public MovieGridViewHolder(ItemMovieGridBinding itemMovieGridBinding) {
            super(itemMovieGridBinding.getRoot());
            this.mItemMovieGridBinding = itemMovieGridBinding;
        }
    }
}
