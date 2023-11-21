package com.example.cinemamanagement.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinemamanagement.databinding.ItemGenreBinding;
import com.example.cinemamanagement.model.Genre;
import com.example.cinemamanagement.utils.GlideUtils;

import java.util.List;

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.GenreViewHolder> {
    private List<Genre> list;
    private IClickGenreListener iClickGenreListener;

    public GenreAdapter(List<Genre> list, IClickGenreListener iClickGenreListener) {
        this.list = list;
        this.iClickGenreListener = iClickGenreListener;
    }

    public interface IClickGenreListener {
        void updateGenre(Genre genre);

        void deleteGenre(Genre genre);
    }

    @NonNull
    @Override
    public GenreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemGenreBinding itemGenreBinding = ItemGenreBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new GenreViewHolder(itemGenreBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull GenreViewHolder holder, int position) {
        Genre genre = list.get(position);
        if (genre == null) {
            return;
        }
        GlideUtils.loadUrl(genre.getImage(), holder.itemGenreBinding.img);
        holder.itemGenreBinding.tvName.setText(genre.getName());
        holder.itemGenreBinding.imgUpdate.setOnClickListener(view -> iClickGenreListener.updateGenre(genre));
        holder.itemGenreBinding.imgDelete.setOnClickListener(view -> iClickGenreListener.deleteGenre(genre));
    }

    @Override
    public int getItemCount() {
        return null == list ? 0 : list.size();
    }

    public static class GenreViewHolder extends RecyclerView.ViewHolder {
        private ItemGenreBinding itemGenreBinding;

        public GenreViewHolder(@NonNull ItemGenreBinding itemGenreBinding) {
            super(itemGenreBinding.getRoot());
            this.itemGenreBinding = itemGenreBinding;
        }
    }
}
