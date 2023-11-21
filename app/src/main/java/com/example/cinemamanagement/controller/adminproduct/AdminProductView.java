package com.example.cinemamanagement.controller.adminproduct;

import com.example.cinemamanagement.model.Genre;
import com.example.cinemamanagement.model.Product;

import java.util.List;

public interface AdminProductView {
    void loadListProductSuccess(List<Product> list);
    void loadDataError();

    void removeSuccess();
}
