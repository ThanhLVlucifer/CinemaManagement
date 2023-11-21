package com.example.cinemamanagement.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinemamanagement.constant.Constant;
import com.example.cinemamanagement.databinding.ItemProductBinding;
import com.example.cinemamanagement.model.Product;
import com.example.cinemamanagement.utils.GlideUtils;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private final List<Product> productList;
    private final IClickProductListener iClickProductListener;

    public interface IClickProductListener {
        void clickItemProduct(Product product, int position);
    }

    public ProductAdapter(List<Product> productList, IClickProductListener iClickProductListener) {
        this.productList = productList;
        this.iClickProductListener = iClickProductListener;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemProductBinding itemProductBinding = ItemProductBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ProductViewHolder(itemProductBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        if (product == null) {
            return;
        }
        GlideUtils.loadUrl(product.getImage(), holder.itemProductBinding.imgFoodCart);
        holder.itemProductBinding.tvProductName.setText(product.getName());
        String strProductPrice = String.valueOf(product.getPrice()) + Constant.CURRENCY_USD;
        holder.itemProductBinding.tvProductPrice.setText(strProductPrice);
        holder.itemProductBinding.tvCount.setText(String.valueOf(product.getCount()));
        holder.itemProductBinding.tvSubtract.setOnClickListener(view -> {
            String strCount = holder.itemProductBinding.tvCount.getText().toString();
            int count = Integer.parseInt(strCount);
            if (count <= 0) {
                return;
            }
            int newCount = count - 1;
            holder.itemProductBinding.tvCount.setText(String.valueOf(newCount));

            int totalPrice = product.getPrice() * newCount;
            product.setCount(newCount);
            product.setTotalPrice(totalPrice);

            iClickProductListener.clickItemProduct(product, holder.getAdapterPosition());
        });

        holder.itemProductBinding.tvAdd.setOnClickListener(view -> {
            String strCount = holder.itemProductBinding.tvCount.getText().toString();
            int newCount = Integer.parseInt(strCount) + 1;

            int totalPrice = product.getPrice() * newCount;
            product.setCount(newCount);
            product.setTotalPrice(totalPrice);

            iClickProductListener.clickItemProduct(product, holder.getAdapterPosition());
        });

    }

    @Override
    public int getItemCount() {
        return null == productList ? 0 : productList.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        private final ItemProductBinding itemProductBinding;

        public ProductViewHolder(@NonNull ItemProductBinding itemProductBinding) {
            super(itemProductBinding.getRoot());
            this.itemProductBinding = itemProductBinding;
        }
    }
}
