package com.example.interviewapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Toast;

import com.example.interviewapp.DB.DatabaseHandler;
import com.example.interviewapp.Utility.Utility;
import com.example.interviewapp.databinding.ActivityProductDetailBinding;
import com.example.interviewapp.retrofit.RetrofitClass;
import com.example.interviewapp.retrofit.retrofit_model.ProductDetails;
import com.example.interviewapp.retrofit.retrofit_model.ProductDetailsResponse;
import com.example.interviewapp.retrofit.retrofit_model.ProductInfoDetail;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailActivity extends AppCompatActivity {

    ActivityProductDetailBinding binding;
    String SocietyProdId, ProductImage;
    List<ProductInfoDetail> productInfoDetails = new ArrayList<>();
    DatabaseHandler db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_product_detail);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_product_detail);
        db = new DatabaseHandler(this);


        SocietyProdId = getIntent().getStringExtra("SocietyProdId");
        ProductImage = getIntent().getStringExtra("ProductImage");

        if (!Utility.isConnected(getApplicationContext())) {
            productInfoDetails.clear();
            try {
                ProductInfoDetail getProductDetail = db.getProductDetail(Integer.parseInt(SocietyProdId));
                productInfoDetails.add(0,getProductDetail);
                setDetails();
            }catch (Exception e){
                Toast.makeText(this, "No Offline Data Found", Toast.LENGTH_SHORT).show();
            }


        }else{
            getProductDetails();
        }


        btnClk();



    }

    private void getProductDetails() {

        Call<ProductDetailsResponse> productdetails = RetrofitClass.RetrofitMain().productdetails("sNhZrOJ/BHc=", SocietyProdId, "1");
        productdetails.enqueue(new Callback<ProductDetailsResponse>() {
            @Override
            public void onResponse(Call<ProductDetailsResponse> call, Response<ProductDetailsResponse> response) {

                if (response.code() == 200) {

                    ProductDetails productDetails = response.body().getProductDetails();
                    productInfoDetails = productDetails.getProductInfoDetails();

                    setDetails();

                }
            }

            @Override
            public void onFailure(Call<ProductDetailsResponse> call, Throwable t) {

            }
        });


    }

    private void setDetails() {

        binding.productName.setText(productInfoDetails.get(0).getTitle());
        binding.textView.setText(productInfoDetails.get(0).getTitle());
        binding.price.setText("Rs " + productInfoDetails.get(0).getMrp());
        binding.discription.setText(Html.fromHtml(productInfoDetails.get(0).getDescription()));

        db.addProductDetails(productInfoDetails.get(0));

        Picasso.get()
                .load(ProductImage)
                .placeholder(R.drawable.progress_animation)
                .into(binding.imageView);


    }

    private void btnClk() {


        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();

            }
        });

        binding.minusClk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int cnt = Integer.parseInt(binding.countTxt.getText().toString()) - 1;

                if (cnt >= 0) {
                    binding.countTxt.setText(String.valueOf(cnt));
                } else {
                    Toast.makeText(getApplicationContext(), "reached min limit 0", Toast.LENGTH_SHORT).show();
                }

            }
        });

        binding.plusClk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int cnt = Integer.parseInt(binding.countTxt.getText().toString()) + 1;

                binding.countTxt.setText(String.valueOf(cnt));


            }
        });


    }
}