package com.example.cinemamanagement.controller.updateproduct;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.cinemamanagement.ControllerApplication;
import com.example.cinemamanagement.model.Genre;
import com.example.cinemamanagement.model.Product;

public class UpdateProductPresenter {
    private final UpdateProductView updateProductView;

    public UpdateProductPresenter(UpdateProductView updateProductView) {
        this.updateProductView = updateProductView;
    }

    public void updateProductSuccess(@NonNull Context context, Product product) {
        if (context == null) {
            return;
        }
        ControllerApplication.get(context).getProductDatabaseReference()
                .child(String.valueOf(product.getId()))
                .setValue(product, (error, ref) -> updateProductView.updateDataSuccess());
    }
}
