package com.example.cinemamanagement.controller.confirm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.cinemamanagement.R;
import com.example.cinemamanagement.adapter.ProductAdapter;
import com.example.cinemamanagement.adapter.SeatAdapter;
import com.example.cinemamanagement.constant.Constant;
import com.example.cinemamanagement.constant.GlobalFuntion;
import com.example.cinemamanagement.databinding.ActivityConfirmBinding;
import com.example.cinemamanagement.listener.IOnClickSeatItemListener;
import com.example.cinemamanagement.model.Booking;
import com.example.cinemamanagement.model.Movie;
import com.example.cinemamanagement.model.Product;
import com.example.cinemamanagement.model.Seat;
import com.example.cinemamanagement.utils.Utils;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.paypal.checkout.approve.Approval;
import com.paypal.checkout.approve.OnApprove;
import com.paypal.checkout.createorder.CreateOrder;
import com.paypal.checkout.createorder.CreateOrderActions;
import com.paypal.checkout.createorder.CurrencyCode;
import com.paypal.checkout.createorder.OrderIntent;
import com.paypal.checkout.createorder.UserAction;
import com.paypal.checkout.order.Amount;
import com.paypal.checkout.order.AppContext;
import com.paypal.checkout.order.CaptureOrderResult;
import com.paypal.checkout.order.OnCaptureComplete;
import com.paypal.checkout.order.OrderRequest;
import com.paypal.checkout.order.PurchaseUnit;
import com.paypal.checkout.paymentbutton.PaymentButtonContainer;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ConfirmActivity extends AppCompatActivity implements ConfirmView, IOnClickSeatItemListener, ProductAdapter.IClickProductListener {
    private ActivityConfirmBinding activityConfirmBinding;
    private ConfirmPresenter confirmPresenter;

    private Movie movie;
    private List<String> roomList;
    private List<String> screeningList;
    private int idRoom = Constant.FLAG;
    private int idScreening = Constant.FLAG;
    private List<Seat> selectSeat;
    private List<Product> chooseProduct;
    private SeatAdapter seatAdapter;
    private ProductAdapter productAdapter;
    private BottomSheetDialog bottomSheetDialog;
    private View viewDialog;
    private int PAYMENT_METHOD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityConfirmBinding = ActivityConfirmBinding.inflate(getLayoutInflater());
        setContentView(activityConfirmBinding.getRoot());

        confirmPresenter = new ConfirmPresenter(this);
        getMovieIntent();
        displayRoomandScreening();
        setEventListRoomAndScreening();
        displayListProduct();
        setupSpinner();
        activityConfirmBinding.btnConfirm.setOnClickListener(view -> {
            if (selectSeat != null) {
                showDialog();
            }
        });
    }

    private void setupSpinner() {
        String[] paymentMethods = {Constant.PAYMENT_METHOD_PAYPAL};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, paymentMethods);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        activityConfirmBinding.spnPaymentMethod.setAdapter(adapter);

        activityConfirmBinding.spnPaymentMethod.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                PAYMENT_METHOD = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                PAYMENT_METHOD = 0;
            }
        });
    }

    private void showDialog() {
        View viewDialog = getLayoutInflater().inflate(R.layout.layout_bottom_sheet_booking, null);

        bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(viewDialog);
        bottomSheetDialog.getBehavior().setState(BottomSheetBehavior.STATE_EXPANDED);

        TextView tvMovieName = viewDialog.findViewById(R.id.tv_movie_name);
        TextView tvReleaseDate = viewDialog.findViewById(R.id.tv_release_date);
        TextView tvRoom = viewDialog.findViewById(R.id.tv_room);
        TextView tvShowtime = viewDialog.findViewById(R.id.tv_showtime);
        TextView tvQuantityTicket = viewDialog.findViewById(R.id.tv_quantity_ticket);
        TextView tvSeatsSelected = viewDialog.findViewById(R.id.tv_seats_selected);
        TextView tvProduct = viewDialog.findViewById(R.id.tv_product);
        TextView tvPaymentMethod = viewDialog.findViewById(R.id.tv_payment_method);
        TextView tvTotal = viewDialog.findViewById(R.id.tv_total);
        Button btnCancel = viewDialog.findViewById(R.id.btn_cancel);
//        Button btnOk = viewDialog.findViewById(R.id.btn_ok);

        int totalPayment = 0;
        String movieName = movie.getName();
        tvMovieName.setText(movieName);
        String releaseDate = movie.getReleaseDate();
        tvReleaseDate.setText(releaseDate);
        String room = roomList.get(idRoom);
        tvRoom.setText(room);
        String showtime = screeningList.get(idScreening);
        tvShowtime.setText(showtime);
        int ticketQuantity = selectSeat.size();
        tvQuantityTicket.setText(String.valueOf(ticketQuantity));
        StringBuilder selectedSeat = new StringBuilder("");
        for (Seat seat : selectSeat) {
            selectedSeat.append(seat.getId()).append(" ");
        }
        int totalTicketSales = ticketQuantity * movie.getTicketPrice();
        totalPayment += totalTicketSales;
        tvSeatsSelected.setText(selectedSeat.toString().trim());
        StringBuilder selectedProduct = new StringBuilder("");
        for (Product product : chooseProduct) {
            String str = product.getName()
                    + " - "
                    + product.getPrice()
                    + Constant.CURRENCY_USD
                    + " - "
                    + getString(R.string.quantity)
                    + " "
                    + product.getCount()
                    + "\n";
            selectedProduct.append(str);
            totalPayment += product.getTotalPrice();
        }
        if (selectedProduct.length() > 0 && selectedProduct.charAt(selectedProduct.length() - 1) == '\n') {
            selectedProduct.deleteCharAt(selectedProduct.length() - 1);
        }
        tvProduct.setText(selectedProduct.toString());
        String paymentMethod = "";
        if (PAYMENT_METHOD == 0) {
            paymentMethod = Constant.PAYMENT_METHOD_PAYPAL;
        }
        tvPaymentMethod.setText(paymentMethod);
        String str = String.valueOf(totalPayment) + Constant.CURRENCY_USD;
        tvTotal.setText(str);

        btnCancel.setOnClickListener(view -> bottomSheetDialog.dismiss());

        int finalTotalPayment = totalPayment;


        PaymentButtonContainer paymentButtonContainer = viewDialog.findViewById(R.id.payment_button_container);
        paymentButtonContainer.setup(
                createOrderActions -> {
                    String strTotal = finalTotalPayment + ".00";
                    ArrayList<PurchaseUnit> purchaseUnits = new ArrayList<>();
                    purchaseUnits.add(
                            new PurchaseUnit.Builder()
                                    .amount(
                                            new Amount.Builder()
                                                    .currencyCode(CurrencyCode.USD)
                                                    .value(strTotal)
                                                    .build()
                                    )
                                    .build()
                    );
                    OrderRequest order = new OrderRequest(
                            OrderIntent.CAPTURE,
                            new AppContext.Builder()
                                    .userAction(UserAction.PAY_NOW)
                                    .build(),
                            purchaseUnits
                    );
                    createOrderActions.create(order, (CreateOrderActions.OnOrderCreated) null);
                },
                approval -> approval.getOrderActions().capture(result -> {
                    long id = System.currentTimeMillis();
                    String userId = Utils.getUserId(getApplicationContext());
                    Booking booking = new Booking(id, userId, movieName, releaseDate, room, showtime, ticketQuantity,
                            selectedSeat.toString(), selectedProduct.toString(), 0, finalTotalPayment, totalTicketSales);
                    confirmPresenter.sendBookingToFirebase(getApplicationContext(), id, booking);
                })
        );

        bottomSheetDialog.show();
    }

    private void displayListProduct() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        activityConfirmBinding.rcvProduct.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        activityConfirmBinding.rcvProduct.addItemDecoration(dividerItemDecoration);

        confirmPresenter.getListProduct(getApplicationContext());
    }

    private void setEventListRoomAndScreening() {
        activityConfirmBinding.listRoom.setOnItemClickListener((adapterView, view, i, l) -> {
            idRoom = i;
            if (idScreening != -1) {
                confirmPresenter.getListSeat(getApplicationContext());
                activityConfirmBinding.layoutSeat.setVisibility(View.VISIBLE);
            }
        });
        activityConfirmBinding.listScreening.setOnItemClickListener((adapterView, view, i, l) -> {
            idScreening = i;
            if (idScreening != -1) {
                confirmPresenter.getListSeat(getApplicationContext());
                activityConfirmBinding.layoutSeat.setVisibility(View.VISIBLE);
            }
        });
    }

    private void displayRoomandScreening() {
        confirmPresenter.getListRoomName(getApplicationContext());
        confirmPresenter.getListScreeningName(getApplicationContext());
    }

    private void getMovieIntent() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            movie = (Movie) bundle.get(Constant.KEY_INTENT_MOVIE_OBJECT);
        }
    }

    @Override
    public void loadListRoomName(List<String> list) {
        roomList = list;
        ArrayAdapter<String> adapterRoom = new ArrayAdapter<>(getApplication(),
                android.R.layout.simple_list_item_multiple_choice, list);
        activityConfirmBinding.listRoom.setAdapter(adapterRoom);
    }

    @Override
    public void loadListScreeningRoomName(List<String> list) {
        screeningList = list;
        ArrayAdapter<String> adapterScreening = new ArrayAdapter<>(getApplication(),
                android.R.layout.simple_list_item_multiple_choice, list);
        activityConfirmBinding.listScreening.setAdapter(adapterScreening);
    }

    @Override
    public void loadListError() {
        GlobalFuntion.showToastMessage(getApplicationContext(), getString(R.string.msg_get_data_error));
    }

    @Override
    public void loadListSeat(List<Seat> list) {
        confirmPresenter.colorClassificationOfSeatingList(getApplicationContext(), idRoom, idScreening, list);
    }

    @Override
    public void colorClassificationSuccess(List<Seat> list) {
        selectSeat = new ArrayList<>();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 4);
        activityConfirmBinding.rvcSeat.setLayoutManager(gridLayoutManager);
        seatAdapter = new SeatAdapter(list, this);
        activityConfirmBinding.rvcSeat.setAdapter(seatAdapter);
    }

    @Override
    public void loadListProductSuccess(List<Product> list) {
        chooseProduct = new ArrayList<>();
        if (list == null || list.isEmpty()) {
            return;
        }

        productAdapter = new ProductAdapter(list, this);
        activityConfirmBinding.rcvProduct.setAdapter(productAdapter);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void sendBookingSuccess() {
        GlobalFuntion.showToastMessage(getApplicationContext(), getString(R.string.msg_booking_ticket_success));
        bottomSheetDialog.dismiss();
        confirmPresenter.updateLookup(getApplicationContext(), selectSeat, idRoom, idScreening);
        seatAdapter.notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onClickItemSeat(Seat seat) {
        confirmPresenter.updateTicket(getApplicationContext(), selectSeat, seat);
        seatAdapter.notifyDataSetChanged();
    }

    @Override
    public void clickItemProduct(Product product, int position) {
        confirmPresenter.updateCart(getApplicationContext(), chooseProduct, product);
        productAdapter.notifyItemChanged(position);
    }
}