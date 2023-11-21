package com.example.cinemamanagement.adapter;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinemamanagement.databinding.ItemSeatGridBinding;
import com.example.cinemamanagement.listener.IOnClickSeatItemListener;
import com.example.cinemamanagement.model.Seat;

import java.util.List;

public class SeatAdapter extends RecyclerView.Adapter<SeatAdapter.SeatViewHolder> {
    private final List<Seat> seatList;
    private final IOnClickSeatItemListener iOnClickSeatItemListener;

    public SeatAdapter(List<Seat> seatList, IOnClickSeatItemListener iOnClickSeatItemListener) {
        this.seatList = seatList;
        this.iOnClickSeatItemListener = iOnClickSeatItemListener;
    }

    @NonNull
    @Override
    public SeatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemSeatGridBinding itemSeatGridBinding = ItemSeatGridBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new SeatViewHolder(itemSeatGridBinding);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onBindViewHolder(@NonNull SeatViewHolder holder, int position) {
        Seat seat = seatList.get(position);
        if (seat == null) {
            return;
        }
        holder.itemSeatGridBinding.btnSeat.setOnClickListener(view -> {
            iOnClickSeatItemListener.onClickItemSeat(seat);
        });
        holder.itemSeatGridBinding.btnSeat.setBackgroundColor(seat.getColor());
        holder.itemSeatGridBinding.btnSeat.setText(String.valueOf(seat.getId()));
    }

    @Override
    public int getItemCount() {
        return null == seatList ? 0 : seatList.size();
    }

    public static class SeatViewHolder extends RecyclerView.ViewHolder {
        private final ItemSeatGridBinding itemSeatGridBinding;

        public SeatViewHolder(ItemSeatGridBinding itemSeatGridBinding) {
            super(itemSeatGridBinding.getRoot());
            this.itemSeatGridBinding = itemSeatGridBinding;
        }
    }
}
