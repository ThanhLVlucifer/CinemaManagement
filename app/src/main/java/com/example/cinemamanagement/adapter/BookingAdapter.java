package com.example.cinemamanagement.adapter;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinemamanagement.databinding.ItemBookingBinding;
import com.example.cinemamanagement.model.Booking;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.util.List;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.BookingViewHolder> {
    private List<Booking> bookingList;
    private IClickBookingItemListener iClickBookingItemListener;

    public interface IClickBookingItemListener {
//        void onClickBookingItem(Booking booking);

        void onClickImageQrCode(String id);
    }

    public BookingAdapter(List<Booking> bookingList, IClickBookingItemListener iClickBookingItemListener) {
        this.bookingList = bookingList;
        this.iClickBookingItemListener = iClickBookingItemListener;
    }

    @NonNull
    @Override
    public BookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemBookingBinding itemBookingBinding = ItemBookingBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
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
        holder.itemBookingBinding.imgQrCode.setOnClickListener(view -> {
//            iClickBookingItemListener.onClickBookingItem(booking);
        });

        holder.itemBookingBinding.imgQrCode.setOnClickListener(view -> {
            iClickBookingItemListener.onClickImageQrCode(String.valueOf(booking.getId()));
        });
    }

    @Override
    public int getItemCount() {
        return null == bookingList ? 0 : bookingList.size();
    }

    public static class BookingViewHolder extends RecyclerView.ViewHolder {

        private ItemBookingBinding itemBookingBinding;

        public BookingViewHolder(@NonNull ItemBookingBinding itemBookingBinding) {
            super(itemBookingBinding.getRoot());
            this.itemBookingBinding = itemBookingBinding;
        }
    }
}
