package com.example.cinemamanagement.controller.adminhistory;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.cinemamanagement.R;
import com.example.cinemamanagement.adapter.AdminAdapter;
import com.example.cinemamanagement.adapter.AdminBookingAdapter;
import com.example.cinemamanagement.constant.GlobalFuntion;
import com.example.cinemamanagement.controller.CaptureAct;
import com.example.cinemamanagement.databinding.FragmentAdminHistoryBinding;
import com.example.cinemamanagement.model.Booking;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.util.List;

public class AdminHistoryFragment extends Fragment implements AdminHistoryView {
    private FragmentAdminHistoryBinding fragmentAdminHistoryBinding;
    private AdminHistoryPresenter adminHistoryPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentAdminHistoryBinding = FragmentAdminHistoryBinding.inflate(inflater, container, false);
        adminHistoryPresenter = new AdminHistoryPresenter(this);
        initView();
        adminHistoryPresenter.getAllListBooking(getContext(), "");
        fragmentAdminHistoryBinding.scanQrCode.setOnClickListener(view -> {
            ScanOptions options = new ScanOptions();
            options.setPrompt(getString(R.string.msg_volume_up_to_flash_on));
            options.setBeepEnabled(true);
            options.setOrientationLocked(true);
            options.setCaptureActivity(CaptureAct.class);
            barLaucher.launch(options);
        });

        return fragmentAdminHistoryBinding.getRoot();
    }

    ActivityResultLauncher<ScanOptions> barLaucher = registerForActivityResult(new ScanContract(), result -> {
        if (result.getContents() != null) {
            adminHistoryPresenter.getAllListBooking(getContext(), result.getContents().trim());
        }
    });

    private void initView() {
        if (getActivity() == null) {
            return;
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        fragmentAdminHistoryBinding.rcvBooking.setLayoutManager(layoutManager);
    }

    @Override
    public void loadAllListBooking(List<Booking> list) {
        AdminBookingAdapter adapter = new AdminBookingAdapter(list, booking -> {
        });
        fragmentAdminHistoryBinding.rcvBooking.setAdapter(adapter);
    }

    @Override
    public void loadListError() {
        GlobalFuntion.showToastMessage(getContext(), getString(R.string.msg_get_data_error));
    }
}
