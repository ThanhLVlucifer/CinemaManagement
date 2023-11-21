package com.example.cinemamanagement.controller.createproduct;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.cinemamanagement.ControllerApplication;
import com.example.cinemamanagement.constant.Constant;
import com.example.cinemamanagement.constant.GlobalFuntion;
import com.example.cinemamanagement.model.Genre;
import com.example.cinemamanagement.model.Product;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class AddProductPresenter {
    private final AddProductView addProductView;

    public AddProductPresenter(AddProductView addProductView) {
        this.addProductView = addProductView;
    }


    public void getLastProductSuccess(@NonNull Context context) {
        if (context == null) {
            return;
        }
        DatabaseReference reference = ControllerApplication.get(context).getProductDatabaseReference();
        Query query = reference.orderByChild(Constant.CHILD_ID).limitToLast(1);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    Product lastProduct = new Product();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        lastProduct = dataSnapshot.getValue(Product.class);
                    }
                    addProductView.getLastProductSuccess(lastProduct);
                } else {
                    addProductView.showMessageListEmpty();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                GlobalFuntion.showToastMessage(context, error.getMessage());
            }
        });

    }

    public void sendProduct(@NonNull Context context, Product product) {
        if (context == null) {
            return;
        }
        ControllerApplication.get(context).getProductDatabaseReference()
                .child(String.valueOf(product.getId()))
                .setValue(product, (error, ref) -> addProductView.sendProductSuccess());
    }

}
