package com.example.cinemamanagement.controller.createproduct;

import com.example.cinemamanagement.model.Product;

public interface AddProductView {
    void getLastProductSuccess(Product lastProduct);
    void showMessageListEmpty();
    void sendProductSuccess();
}
