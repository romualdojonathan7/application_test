package br.com.jonathan.challenge.ui.adapter;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import br.com.jonathan.challenge.R;
import br.com.jonathan.challenge.model.Product;
import br.com.jonathan.challenge.databinding.ProductItemBinding;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>{

    private List<Product> products;

    @NonNull
    @Override
    public ProductAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ProductItemBinding productItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.product_item, parent, false);

        return new ProductViewHolder(productItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ProductViewHolder holder, int position) {
        if(products != null) {
            Product product = products.get(position);

            holder.productItemBinding.setProduct(product);
            holder.productItemBinding.executePendingBindings();
        } else {
            // Exibe lista vazia
        }
    }

    @BindingAdapter({"imageUrl"})
    public static void setImage(ImageView imageView, String imageUrl){
        Glide.with(imageView.getContext())
                .load(imageUrl)
                .placeholder(R.drawable.ic_menu_gallery)
                .dontAnimate()
                .centerCrop()
                .error(android.R.drawable.ic_menu_gallery)
                .into(imageView);
    }

    @BindingAdapter({"dateText"})
    public static void setText(TextView textView, Timestamp timestamp){
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.LONG);
        textView.setText(dateFormat.format(timestamp));
    }

    public void setProducts(List<Product> products) {
        this.products = products;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(products != null){
            return products.size();
        }
        return 0;
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {

        final ProductItemBinding productItemBinding;

        public ProductViewHolder(@NonNull ProductItemBinding productItemBinding) {
            super(productItemBinding.getRoot());

            this.productItemBinding = productItemBinding;
        }
    }
}
