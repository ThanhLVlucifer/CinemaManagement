package com.example.cinemamanagement.controller.createproduct;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.cinemamanagement.R;
import com.example.cinemamanagement.constant.GlobalFuntion;
import com.example.cinemamanagement.controller.AdminActivity;
import com.example.cinemamanagement.databinding.ActivityAddProductBinding;
import com.example.cinemamanagement.model.Product;

public class AddProductActivity extends AppCompatActivity implements AddProductView {

    private ActivityAddProductBinding activityAddProductBinding;
    private AddProductPresenter addProductPresenter;
    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAddProductBinding = ActivityAddProductBinding.inflate(getLayoutInflater());
        setContentView(activityAddProductBinding.getRoot());

        addProductPresenter = new AddProductPresenter(this);
        product = new Product();
        addProductPresenter.getLastProductSuccess(getApplicationContext());
        activityAddProductBinding.btnAddProduct.setOnClickListener(view -> {
            String name = activityAddProductBinding.edtEnterName.getText().toString().trim();
            String description = activityAddProductBinding.edtEnterDescription.getText().toString().trim();
            String strPrice = activityAddProductBinding.edtEnterPrice.getText().toString().trim();
            int price = Integer.parseInt(strPrice);
            String url = activityAddProductBinding.edtEnterUrlImage.getText().toString().trim();
            product.setName(name);
            product.setDescription(description);
            product.setPrice(price);
            product.setImage(url);
            addProductPresenter.sendProduct(getApplicationContext(), product);
        });
    }

    @Override
    public void getLastProductSuccess(Product lastProduct) {
        product.setId(lastProduct.getId() + 1);
    }

    @Override
    public void showMessageListEmpty() {
        product.setId(0);
        GlobalFuntion.showToastMessage(getApplicationContext(), getString(R.string.msg_list_empty_and_add_first_genre));
    }

    @Override
    public void sendProductSuccess() {
        GlobalFuntion.showToastMessage(getApplicationContext(), getString(R.string.msg_add_data_success));
        GlobalFuntion.startActivity(getApplicationContext(), AdminActivity.class);
    }
}