package com.example.cinemamanagement.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinemamanagement.databinding.ItemGenreBinding;
import com.example.cinemamanagement.databinding.ItemProductAdminBinding;
import com.example.cinemamanagement.model.Genre;
import com.example.cinemamanagement.model.Product;
import com.example.cinemamanagement.utils.GlideUtils;

import java.util.List;

public class ProductAdminAdapter extends RecyclerView.Adapter<ProductAdminAdapter.ProductAdminViewHolder> {
    private List<Product> list;
    private IClickProductAdmin iClickProductAdmin;

    public interface IClickProductAdmin {
        void updateProduct(Product product);

        void deleteProduct(Product product);

    }

    public ProductAdminAdapter(List<Product> list, IClickProductAdmin iClickProductAdmin) {
        this.list = list;
        this.iClickProductAdmin = iClickProductAdmin;
    }

    @NonNull
    @Override
    public ProductAdminViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemProductAdminBinding itemProductAdminBinding = ItemProductAdminBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ProductAdminViewHolder(itemProductAdminBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdminViewHolder holder, int position) {
        Product product = list.get(position);
        if (product == null) {
            return;
        }
        GlideUtils.loadUrl(product.getImage(), holder.itemProductAdminBinding.img);
        holder.itemProductAdminBinding.tvName.setText(product.getName());
        holder.itemProductAdminBinding.imgUpdate.setOnClickListener(view -> iClickProductAdmin.updateProduct(product));
        holder.itemProductAdminBinding.imgDelete.setOnClickListener(view -> iClickProductAdmin.deleteProduct(product));
    }

    @Override
    public int getItemCount() {
        return null == list ? 0 : list.size();
    }

    public static class ProductAdminViewHolder extends RecyclerView.ViewHolder {
        private ItemProductAdminBinding itemProductAdminBinding;

        public ProductAdminViewHolder(@NonNull ItemProductAdminBinding itemProductAdminBinding) {
            super(itemProductAdminBinding.getRoot());
            this.itemProductAdminBinding = itemProductAdminBinding;
        }
    }
}
