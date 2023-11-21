package com.example.cinemamanagement.controller.adminproduct;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.cinemamanagement.R;
import com.example.cinemamanagement.adapter.GenreAdapter;
import com.example.cinemamanagement.adapter.ProductAdminAdapter;
import com.example.cinemamanagement.constant.Constant;
import com.example.cinemamanagement.constant.GlobalFuntion;
import com.example.cinemamanagement.controller.AdminActivity;
import com.example.cinemamanagement.controller.creategenre.CreateGenreActivity;
import com.example.cinemamanagement.controller.createproduct.AddProductActivity;
import com.example.cinemamanagement.controller.updategenre.UpdateGenreActivity;
import com.example.cinemamanagement.controller.updateproduct.UpdateProductActivity;
import com.example.cinemamanagement.databinding.FragmentAdminProductBinding;
import com.example.cinemamanagement.model.Genre;
import com.example.cinemamanagement.model.Product;

import java.util.List;

public class AdminProductFragment extends Fragment implements AdminProductView, ProductAdminAdapter.IClickProductAdmin {
    private FragmentAdminProductBinding fragmentAdminProductBinding;
    private AdminProductPresenter adminProductPresenter;
    private List<Product> listProduct;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentAdminProductBinding = FragmentAdminProductBinding.inflate(inflater, container, false);
        adminProductPresenter = new AdminProductPresenter(this);
        adminProductPresenter.getListProduct(getContext(), "");
        setEvent();

        return fragmentAdminProductBinding.getRoot();
    }

    private void setEvent() {
        setEventSearch();
        setEventAdd();
    }

    private void setEventAdd() {
        fragmentAdminProductBinding.fabAdd.setOnClickListener(view -> {
            GlobalFuntion.startActivity(getContext(), AddProductActivity.class);
        });
    }

    private void setEventSearch() {
        fragmentAdminProductBinding.search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adminProductPresenter.getListProduct(getContext(), newText);
                return true;
            }
        });
    }

    private void displayListProduct() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        fragmentAdminProductBinding.rcvProduct.setLayoutManager(layoutManager);

        ProductAdminAdapter adapter = new ProductAdminAdapter(listProduct, this);
        fragmentAdminProductBinding.rcvProduct.setAdapter(adapter);
    }

    @Override
    public void loadListProductSuccess(List<Product> list) {
        listProduct = list;
        displayListProduct();
    }

    @Override
    public void loadDataError() {
        GlobalFuntion.showToastMessage(getContext(), getString(R.string.msg_get_data_error));
    }

    @Override
    public void removeSuccess() {
        GlobalFuntion.showToastMessage(getContext(), getString(R.string.msg_remove_data_success));
        GlobalFuntion.startActivity(getContext(), AdminActivity.class);
    }

    @Override
    public void updateProduct(Product product) {
        gotoUpdateProduct(product);
    }

    private void gotoUpdateProduct(Product product) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constant.KEY_INTENT_PRODUCT_OBJECT, product);
        GlobalFuntion.startActivity(getContext(), UpdateProductActivity.class, bundle);
    }

    @Override
    public void deleteProduct(Product product) {
        new AlertDialog.Builder(getActivity())
                .setTitle(getString(R.string.confirm_delete_product))
                .setMessage(getString(R.string.message_delete_product))
                .setPositiveButton(getString(R.string.delete), (dialogInterface, i) -> adminProductPresenter.deleteProduct(getContext(), product))
                .setNegativeButton(getString(R.string.cancel_delete), (dialogInterface, i) -> dialogInterface.dismiss())
                .show();
    }
}
