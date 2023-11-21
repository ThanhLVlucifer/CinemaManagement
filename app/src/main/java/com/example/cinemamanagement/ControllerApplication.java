package com.example.cinemamanagement;

import android.app.Application;
import android.content.Context;

import com.example.cinemamanagement.constant.Constant;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.paypal.checkout.PayPalCheckout;
import com.paypal.checkout.config.CheckoutConfig;
import com.paypal.checkout.config.Environment;
import com.paypal.checkout.createorder.CurrencyCode;
import com.paypal.checkout.createorder.UserAction;

public class ControllerApplication extends Application {

    private FirebaseDatabase mFirebaseDatabase;

    public static ControllerApplication get(Context context) {
        return (ControllerApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);
        mFirebaseDatabase = FirebaseDatabase.getInstance();

        PayPalCheckout.setConfig(new CheckoutConfig(
                this,
                "AQBqy_PwdPgAClpEJu8ZAaN2_yRT2mxF-2ryLFU1TTCDqqeDS9wlT3acwZcnOi1eytVRnO1tdkFu_bwb",
                Environment.SANDBOX,
                CurrencyCode.USD,
                UserAction.PAY_NOW,
                "com.example.cinemamanagement://paypalpay"
        ));
    }

    public DatabaseReference getMovieDatabaseReference() {
        return mFirebaseDatabase.getReference(Constant.FIREBASE_MOVIE_TABLE);
    }

    public DatabaseReference getGenreDatabaseReference() {
        return mFirebaseDatabase.getReference(Constant.FIREBASE_GENRE_TABLE);
    }

    public DatabaseReference getRoomDatabaseReference() {
        return mFirebaseDatabase.getReference(Constant.FIREBASE_ROOM_TABLE);
    }

    public DatabaseReference getScreeningDatabaseReference() {
        return mFirebaseDatabase.getReference(Constant.FIREBASE_SCREENING_TABLE);
    }

    public DatabaseReference getSeatDatabaseReference() {
        return mFirebaseDatabase.getReference(Constant.FIREBASE_SEAT_TABLE);
    }

    public DatabaseReference getLookupDatabaseReference() {
        return mFirebaseDatabase.getReference(Constant.FIREBASE_LOOKUP_TABLE);
    }

    public DatabaseReference getProductDatabaseReference() {
        return mFirebaseDatabase.getReference(Constant.FIREBASE_PRODUCT_TABLE);
    }

    public DatabaseReference getBookingDatabaseReference() {
        return mFirebaseDatabase.getReference(Constant.FIREBASE_BOOKING_TABLE);
    }
}