package com.example.cinemamanagement.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinemamanagement.databinding.ItemBookingAdminBinding;
import com.example.cinemamanagement.databinding.ItemBookingBinding;
import com.example.cinemamanagement.model.Booking;

import java.util.List;

public class AdminBookingAdapter extends RecyclerView.Adapter<AdminBookingAdapter.BookingViewHolder> {
    private List<Booking> bookingList;
    private IClickBookingItemListener iClickBookingItemListener;

    public interface IClickBookingItemListener {
        void onClickBookingItem(Booking booking);
    }

    public AdminBookingAdapter(List<Booking> bookingList, IClickBookingItemListener iClickBookingItemListener) {
        this.bookingList = bookingList;
        this.iClickBookingItemListener = iClickBookingItemListener;
    }

    @NonNull
    @Override
    public BookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemBookingAdminBinding itemBookingBinding = ItemBookingAdminBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new BookingViewHolder(itemBookingBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingViewHolder holder, int position) {
        Booking booking = bookingList.get(position);
        if (booking == null) {
            return;
        }
        holder.itemBookingBinding.tvId.setText(String.valueOf(booking.getId()));
        holder.itemBookingBinding.tvMovieName.setText(booking.getNameMovie());
        holder.itemBookingBinding.tvReleaseDate.setText(booking.getReleaseDate());
        holder.itemBookingBinding.tvRoom.setText(booking.getRoom());
        holder.itemBookingBinding.tvShowtime.setText(booking.getShowtime());
        holder.itemBookingBinding.tvTicketQuantity.setText(String.valueOf(booking.getTicketQuantity()));
        holder.itemBookingBinding.tvSelectedSeat.setText(booking.getSelectedSeat());
        holder.itemBookingBinding.tvProduct.setText(booking.getSelectedProduct());
        holder.itemBookingBinding.tvPaymentMethod.setText("Tiền mặt");

        String total = booking.getTotalPayment() + ".000đ";
        holder.itemBookingBinding.tvTotalPayment.setText(total);
    }

    @Override
    public int getItemCount() {
        return null == bookingList ? 0 : bookingList.size();
    }

    public static class BookingViewHolder extends RecyclerView.ViewHolder {

        private ItemBookingAdminBinding itemBookingBinding;

        public BookingViewHolder(@NonNull ItemBookingAdminBinding itemBookingBinding) {
            super(itemBookingBinding.getRoot());
            this.itemBookingBinding = itemBookingBinding;
        }
    }
}
