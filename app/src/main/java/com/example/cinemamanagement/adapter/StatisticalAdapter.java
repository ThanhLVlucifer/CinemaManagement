package com.example.cinemamanagement.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinemamanagement.constant.Constant;
import com.example.cinemamanagement.databinding.ItemRevenueBinding;
import com.example.cinemamanagement.model.Booking;
import com.example.cinemamanagement.model.Movie;

import java.util.List;

public class StatisticalAdapter extends RecyclerView.Adapter<StatisticalAdapter.StatisticalViewHolder> {
    private final List<Booking> list;

    public StatisticalAdapter(List<Booking> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public StatisticalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRevenueBinding itemRevenueBinding = ItemRevenueBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new StatisticalViewHolder(itemRevenueBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull StatisticalViewHolder holder, int position) {
        Booking booking = list.get(position);
        if (booking == null) {
            return;
        }
        holder.itemRevenueBinding.tvStt.setText(String.valueOf(position));
        holder.itemRevenueBinding.tvMovieName.setText(booking.getNameMovie());
        holder.itemRevenueBinding.tvMovieTicketSalesCount.setText(String.valueOf(booking.getTicketQuantity()));
        String revenue = String.valueOf(booking.getTotalTicketSales()) + Constant.CURRENCY_USD;
        holder.itemRevenueBinding.tvMovieRevenue.setText(revenue);
    }

    @Override
    public int getItemCount() {
        return null == list ? 0 : list.size();
    }

    public static class StatisticalViewHolder extends RecyclerView.ViewHolder {

        private final ItemRevenueBinding itemRevenueBinding;

        public StatisticalViewHolder(@NonNull ItemRevenueBinding itemRevenueBinding) {
            super(itemRevenueBinding.getRoot());
            this.itemRevenueBinding = itemRevenueBinding;
        }
    }
}
