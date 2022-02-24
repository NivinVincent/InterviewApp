package com.example.interviewapp.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.interviewapp.R;
import com.example.interviewapp.retrofit.retrofit_model.ProductListResponseSearchMobile;
import com.squareup.picasso.Picasso;

import java.util.List;


public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {

    Context context;
    List<ProductListResponseSearchMobile> searchProductMobile;
    boolean isNetStatus = false;


    public interface ProductClick {
        void addToCart(View v, int position);
        void productDetails(View v, int position);
    }

    private ProductClick productClick;

    public ProductListAdapter(boolean isNetStatus, Context context, List<ProductListResponseSearchMobile> searchProductMobile, ProductClick productClick) {
        this.context = context;
        this.searchProductMobile = searchProductMobile;
        this.productClick = productClick;
        this.isNetStatus = isNetStatus;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_list_item, parent, false);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ProductListAdapter.ViewHolder holder, int position) {

        if (isNetStatus) {
            Picasso.get()
                    .load(searchProductMobile.get(position).getThumbImage())
                    .placeholder(R.drawable.progress_animation)
                    .into(holder.imageView);
        } else {
            try {
                holder.imageView.setImageBitmap(BitmapFactory.decodeByteArray(searchProductMobile.get(position).getImage_blob(),
                        0, searchProductMobile.get(position).getImage_blob().length));
            } catch (Exception e) {

            }

        }


        holder.product_name.setText(searchProductMobile.get(position).getTitle());
        holder.textView2.setText("RS " + searchProductMobile.get(position).getFinalprice());

        holder.complete_clk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                productClick.productDetails(view,position);

            }
        });

        holder.addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productClick.addToCart(view, position);
            }
        });

        holder.minus_clk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int cnt = Integer.parseInt(holder.count_txt.getText().toString()) - 1;

                if (cnt >= 0) {
                    holder.count_txt.setText(String.valueOf(cnt));
                } else {
                    Toast.makeText(context, "reached min limit 0", Toast.LENGTH_SHORT).show();
                }

            }
        });


        holder.plus_clk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int cnt = Integer.parseInt(holder.count_txt.getText().toString()) + 1;

                holder.count_txt.setText(String.valueOf(cnt));


            }
        });


    }

    @Override
    public int getItemCount() {
        if (!searchProductMobile.isEmpty()) {
            return searchProductMobile.size();
        } else {
            return 0;
        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout addToCart, minus_clk, plus_clk;
        TextView count_txt, product_name, textView2;
        ImageView imageView;
        ConstraintLayout complete_clk;

        ViewHolder(View itemView) {
            super(itemView);

            addToCart = itemView.findViewById(R.id.add_to_cart);
            minus_clk = itemView.findViewById(R.id.minus_clk);
            plus_clk = itemView.findViewById(R.id.plus_clk);
            count_txt = itemView.findViewById(R.id.count_txt);
            imageView = itemView.findViewById(R.id.imageView);
            product_name = itemView.findViewById(R.id.product_name);
            textView2 = itemView.findViewById(R.id.textView2);
            complete_clk = itemView.findViewById(R.id.complete_clk);


        }


    }

}
