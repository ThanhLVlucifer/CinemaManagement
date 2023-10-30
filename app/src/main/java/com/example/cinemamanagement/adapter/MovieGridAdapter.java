package com.example.cinemamanagement.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinemamanagement.databinding.ItemMovieGridBinding;
import com.example.cinemamanagement.listener.IOnClickMovieItemListener;
import com.example.cinemamanagement.model.Movie;
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
        ItemMovieGridBinding itemMovieGridBinding = ItemMovieGridBinding.inflate(LayoutInflater.from(parent.getContext()),parent, false);
        return new MovieGridViewHolder(itemMovieGridBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieGridViewHolder holder, int position) {
        Movie movie = mListMovies.get(position);
        if(movie == null){
            return;
        }
        holder.mItemMovieGridBinding.layoutItem.setOnClickListener(view -> iOnClickMovieItemListener.onClickItemFood(movie));
    }

//    @Override
//    public void onBindViewHolder(@NonNull FoodGridViewHolder holder, int position) {
//        Food food = mListFoods.get(position);
//        if (food == null) {
//            return;
//        }
//        GlideUtils.loadUrl(food.getImage(), holder.mItemFoodGridBinding.imgFood);
//        if (food.getSale() <= 0) {
//            holder.mItemFoodGridBinding.tvSaleOff.setVisibility(View.GONE);
//            holder.mItemFoodGridBinding.tvPrice.setVisibility(View.GONE);
//
//            String strPrice = food.getPrice() + Constant.CURRENCY;
//            holder.mItemFoodGridBinding.tvPriceSale.setText(strPrice);
//        } else {
//            holder.mItemFoodGridBinding.tvSaleOff.setVisibility(View.VISIBLE);
//            holder.mItemFoodGridBinding.tvPrice.setVisibility(View.VISIBLE);
//
//            String strSale = "Giảm " + food.getSale() + "%";
//            holder.mItemFoodGridBinding.tvSaleOff.setText(strSale);
//
//            String strOldPrice = food.getPrice() + Constant.CURRENCY;
//            holder.mItemFoodGridBinding.tvPrice.setText(strOldPrice);
//            holder.mItemFoodGridBinding.tvPrice.setPaintFlags(holder.mItemFoodGridBinding.tvPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
//
//            String strRealPrice = food.getRealPrice() + Constant.CURRENCY;
//            holder.mItemFoodGridBinding.tvPriceSale.setText(strRealPrice);
//        }
//        holder.mItemFoodGridBinding.tvFoodName.setText(food.getName());
//
//        holder.mItemFoodGridBinding.layoutItem.setOnClickListener(v -> iOnClickFoodItemListener.onClickItemFood(food));
//    }

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
