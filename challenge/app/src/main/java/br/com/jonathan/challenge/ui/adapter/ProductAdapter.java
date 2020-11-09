package br.com.jonathan.challenge.ui.adapter;

import android.content.Context;
import android.os.Build;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import br.com.jonathan.challenge.R;
import br.com.jonathan.challenge.model.Product;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>{

    private LayoutInflater layoutInflater;
    private Context context;
    private List<Product> products;

    public ProductAdapter(Context context){
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ProductAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.product_item_2, parent, false); // AQUI. Trocar o activity_main por job_item

        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ProductViewHolder holder, int position) {
        if(products != null) {
            Product product = products.get(position);

            Glide.with(this.context)
                    .load(product.getImage())
                    .dontAnimate()
                    .centerCrop()
                    .error(android.R.drawable.ic_menu_gallery)
                    .into(holder.companyImage);

            holder.productTitle.setText(product.getName());
            holder.productDescription.setText(product.getDescription());
            holder.productCreatedAt.setText(product.getCreatedAt().toString());
            holder.productType.setText(product.getProductType());
//            holder.jobDescription.setText(job.getDescrption());
        } else {
            // Exibe lista vazia
        }
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

    public class ProductViewHolder extends RecyclerView.ViewHolder {

        private final ImageView companyImage;
        private final TextView productTitle, productDescription, productCreatedAt, productType;


        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            companyImage = itemView.findViewById(R.id.imageView_product_image);
            productTitle = itemView.findViewById(R.id.text_view_product_title);
            productDescription = itemView.findViewById(R.id.text_view_product_description);
            productCreatedAt = itemView.findViewById(R.id.text_view_created_at);
            productType = itemView.findViewById(R.id.text_view_product_type);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                productDescription.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
            }
        }
    }
}
