package com.example.cinemamanagement.controller.userhistorybooking;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.cinemamanagement.R;
import com.example.cinemamanagement.adapter.BookingAdapter;
import com.example.cinemamanagement.constant.GlobalFuntion;
import com.example.cinemamanagement.databinding.FragmentUserHistoryBookingBinding;
import com.example.cinemamanagement.model.Booking;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.util.List;


public class UserHistoryBookingFragment extends Fragment implements UserHistoryBookingView {
    private FragmentUserHistoryBookingBinding mFragmentUserHistoryBookingBinding;
    private UserHistoryBookingPresenter userHistoryBookingPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mFragmentUserHistoryBookingBinding = FragmentUserHistoryBookingBinding.inflate(inflater, container, false);

        userHistoryBookingPresenter = new UserHistoryBookingPresenter(this);
        initView();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        userHistoryBookingPresenter.getListBooking(getActivity(), user.getUid().toString());
        mFragmentUserHistoryBookingBinding.imgBack.setOnClickListener(view -> {
            mFragmentUserHistoryBookingBinding.imgCreateQrCode.setVisibility(View.GONE);
            mFragmentUserHistoryBookingBinding.imgBack.setVisibility(View.GONE);
        });

        return mFragmentUserHistoryBookingBinding.getRoot();
    }

    private void initView() {
        if (getActivity() == null) {
            return;
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mFragmentUserHistoryBookingBinding.rcvBooking.setLayoutManager(layoutManager);
    }

    @Override
    public void loadListBooking(List<Booking> list) {
        BookingAdapter bookingAdapter = new BookingAdapter(list, id -> {
            try {
                Bitmap bitmap = encodeAsBitmap(id);
                mFragmentUserHistoryBookingBinding.imgCreateQrCode.setImageBitmap(bitmap);
                mFragmentUserHistoryBookingBinding.imgCreateQrCode.setVisibility(View.VISIBLE);
                mFragmentUserHistoryBookingBinding.imgBack.setVisibility(View.VISIBLE);
            } catch (WriterException e) {
                e.printStackTrace();
            }
        });
        mFragmentUserHistoryBookingBinding.rcvBooking.setAdapter(bookingAdapter);
    }

    private Bitmap encodeAsBitmap(String data) throws WriterException {
        QRCodeWriter writer = new QRCodeWriter();
        BitMatrix bitMatrix = writer.encode(data, BarcodeFormat.QR_CODE, 512, 512);
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                bmp.setPixel(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }

        return bmp;
    }

    @Override
    public void loadListError() {
        GlobalFuntion.showToastMessage(getActivity(), getString(R.string.msg_get_data_error));
    }
}
