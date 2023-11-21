package com.example.cinemamanagement.controller.adminproduct;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.cinemamanagement.ControllerApplication;
import com.example.cinemamanagement.constant.GlobalFuntion;
import com.example.cinemamanagement.model.Genre;
import com.example.cinemamanagement.model.Product;
import com.example.cinemamanagement.utils.StringUtil;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdminProductPresenter {
    private final AdminProductView adminProductView;

    public AdminProductPresenter(AdminProductView adminProductView) {
        this.adminProductView = adminProductView;
    }

    public void deleteProduct(@NonNull Context context, Product product) {
        if (context == null) {
            return;
        }
        ControllerApplication.get(context).getProductDatabaseReference()
                .child(String.valueOf(product.getId()))
                .removeValue((error, ref) -> adminProductView.removeSuccess());
    }

    public void getListProduct(@NonNull Context context, String key) {
        if (context == null) {
            return;
        }
        ControllerApplication.get(context).getProductDatabaseReference()
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        List<Product> list = new ArrayList<>();
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            Product product = dataSnapshot.getValue(Product.class);
                            if (product == null) {
                                return;
                            }
                            if (StringUtil.isEmpty(key)) {
                                list.add(0, product);
                            } else {
                                if (GlobalFuntion.getTextSearch(product.getName()).toLowerCase().trim()
                                        .contains(GlobalFuntion.getTextSearch(key).toLowerCase().trim())) {
                                    list.add(0, product);
                                }
                            }
                        }
                        adminProductView.loadListProductSuccess(list);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        adminProductView.loadDataError();
                    }
                });
    }
}
