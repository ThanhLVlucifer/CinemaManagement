package com.example.cinemamanagement.controller.updateproduct;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.cinemamanagement.R;
import com.example.cinemamanagement.constant.Constant;
import com.example.cinemamanagement.constant.GlobalFuntion;
import com.example.cinemamanagement.controller.AdminActivity;
import com.example.cinemamanagement.databinding.ActivityUpdateProductBinding;
import com.example.cinemamanagement.model.Genre;
import com.example.cinemamanagement.model.Product;

public class UpdateProductActivity extends AppCompatActivity implements UpdateProductView {
    private ActivityUpdateProductBinding activityUpdateProductBinding;
    private UpdateProductPresenter updateProductPresenter;
    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityUpdateProductBinding = ActivityUpdateProductBinding.inflate(getLayoutInflater());
        setContentView(activityUpdateProductBinding.getRoot());
        updateProductPresenter = new UpdateProductPresenter(this);
        getDataIntent();
        setData();
        initEvent();

    }

    private void initEvent() {
        activityUpdateProductBinding.btnUpdateProduct.setOnClickListener(view -> {
            String name = activityUpdateProductBinding.edtEnterName.getText().toString().trim();
            String description = activityUpdateProductBinding.edtEnterDescription.getText().toString().trim();
            String strPrice = activityUpdateProductBinding.edtEnterPrice.getText().toString().trim();
            int price = Integer.parseInt(strPrice);
            String url = activityUpdateProductBinding.edtEnterUrlImage.getText().toString().trim();
            Product newProduct = new Product(product.getId(), name, description, price, url);
            updateProductPresenter.updateProductSuccess(getApplicationContext(), newProduct);
        });
    }

    private void setData() {
        activityUpdateProductBinding.edtEnterName.setText(product.getName());
        activityUpdateProductBinding.edtEnterDescription.setText(product.getDescription());
        activityUpdateProductBinding.edtEnterPrice.setText(String.valueOf(product.getPrice()));
        activityUpdateProductBinding.edtEnterUrlImage.setText(product.getImage());
    }

    private void getDataIntent() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            product = (Product) bundle.get(Constant.KEY_INTENT_PRODUCT_OBJECT);
        }
    }

    @Override
    public void updateDataSuccess() {
        GlobalFuntion.showToastMessage(getApplicationContext(), getString(R.string.msg_update_data_success));
        GlobalFuntion.startActivity(getApplicationContext(), AdminActivity.class);
    }
}