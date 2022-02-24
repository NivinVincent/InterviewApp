package com.example.interviewapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.interviewapp.DB.DatabaseHandler;
import com.example.interviewapp.Utility.Utility;
import com.example.interviewapp.adapter.ProductListAdapter;
import com.example.interviewapp.databinding.ActivityMainBinding;
import com.example.interviewapp.retrofit.RetrofitClass;
import com.example.interviewapp.retrofit.retrofit_model.ProductListResponse;
import com.example.interviewapp.retrofit.retrofit_model.ProductListResponseSearchMobile;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ProductListAdapter listAdapter;
    ActivityMainBinding binding;
    List<ProductListResponseSearchMobile> searchProductMobile = new ArrayList<>();
    DatabaseHandler db;
    boolean isNetStatus = false;
    private ProgressDialog simpleWaitDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        db = new DatabaseHandler(this);

        isNetworkAvailable();


    }

    private void isNetworkAvailable() {

        if (Utility.isConnected(getApplicationContext())) {
            //Toast.makeText(this, "Network Available", Toast.LENGTH_SHORT).show();
            isNetStatus = true;
            getProductList();
        } else {
            //  Toast.makeText(this, "No Network", Toast.LENGTH_SHORT).show();
            isNetStatus = false;
            searchProductMobile.clear();
            searchProductMobile = db.getAllProducts();
            if (!searchProductMobile.isEmpty()) {
                adapterInitialization();
            } else {
                Toast.makeText(this, "No Offline Data Found", Toast.LENGTH_SHORT).show();
            }
            //  adapterInitialization();
        }


    }

    private void getProductList() {

        binding.progrs.setVisibility(View.VISIBLE);
        Call<ProductListResponse> listproducts = RetrofitClass.RetrofitMain().listproducts("sNhZrOJ/BHc=", "1", "1", "5");
        listproducts.enqueue(new Callback<ProductListResponse>() {
            @Override
            public void onResponse(Call<ProductListResponse> call, Response<ProductListResponse> response) {
                binding.progrs.setVisibility(View.GONE);
                Log.i("getProductList", "<==" + response.code());
                Log.i("getProductList", "<==" + response.body().getTotalCount());
                if (response.code() == 200) {

                    searchProductMobile.clear();
                    searchProductMobile = response.body().getSearchProductMobile();

                    Utility.settingSnackBar(binding.getRoot(), "Please wait a few minutes for storing data to offline").show();
                    adapterInitialization();

                    for (ProductListResponseSearchMobile sb : searchProductMobile) {

                        new ImageDownloader(sb).execute(sb.getThumbImage());

                    }

                }

            }

            @Override
            public void onFailure(Call<ProductListResponse> call, Throwable t) {
                binding.progrs.setVisibility(View.GONE);
            }
        });


    }


    private class ImageDownloader extends AsyncTask<String, Integer, byte[]> {

        ProductListResponseSearchMobile sb;


        public ImageDownloader(ProductListResponseSearchMobile sb) {
            this.sb = sb;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            simpleWaitDialog = new ProgressDialog(MainActivity.this);
            simpleWaitDialog.setMessage("Downloading file. Please wait...");
            simpleWaitDialog.setIndeterminate(false);
            simpleWaitDialog.setProgress(0);
            simpleWaitDialog.setMax(searchProductMobile.size());
            simpleWaitDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            simpleWaitDialog.setCancelable(true);
            // simpleWaitDialog.show();

        }

        @Override
        protected byte[] doInBackground(String... params) {

            Log.i("doBag", "<==" + params[0]);

            try {
                URL imageUrl = new URL(params[0]);
                URLConnection ucon = imageUrl.openConnection();

                InputStream is = ucon.getInputStream();
                BufferedInputStream bis = new BufferedInputStream(is);

                ByteArrayOutputStream buffer = new ByteArrayOutputStream();

                //  ByteArrayBuffer baf = new ByteArrayBuffer(500);
                int current = 0;
                while ((current = bis.read()) != -1) {
                    buffer.write((byte) current);
                }

                // sb.setImage_blob();
                Log.i("doBag", "<==" + buffer.toByteArray());
                return buffer.toByteArray();

            } catch (Exception e) {

            }

            return null;
        }


        @Override
        protected void onPostExecute(byte[] aByte) {
            super.onPostExecute(aByte);
            Log.i("doBagP", "<==" + aByte);
            sb.setImage_blob(aByte);
            db.addProduct(sb);
            simpleWaitDialog.dismiss();
        }


        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            simpleWaitDialog.setProgress(values[0]);
        }
    }


    private void getBlobImage(ProductListResponseSearchMobile sb) {

        try {
            URL imageUrl = new URL(sb.getThumbImage());
            URLConnection ucon = imageUrl.openConnection();

            InputStream is = ucon.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);

            ByteArrayOutputStream buffer = new ByteArrayOutputStream();

            //  ByteArrayBuffer baf = new ByteArrayBuffer(500);
            int current = 0;
            while ((current = bis.read()) != -1) {
                buffer.write((byte) current);
            }

            sb.setImage_blob(buffer.toByteArray());
            db.addProduct(sb);

            //  return buffer.toByteArray();
        } catch (Exception e) {
            Log.d("ImageManager", "Error: " + e.toString());
        }

        //    db.addProduct(sb);
    }

    private void adapterInitialization() {

        binding.productListRc.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        listAdapter = new ProductListAdapter(isNetStatus, getApplicationContext(), searchProductMobile, new ProductListAdapter.ProductClick() {
            @Override
            public void addToCart(View v, int position) {


            }

            @Override
            public void productDetails(View v, int position) {

                Intent prodIntent = new Intent(getApplicationContext(), ProductDetailActivity.class);
                prodIntent.putExtra("SocietyProdId", String.valueOf(searchProductMobile.get(position).getSocietyproductID()));
                prodIntent.putExtra("ProductImage", String.valueOf(searchProductMobile.get(position).getThumbImage()));
                startActivity(prodIntent);

            }
        });
        binding.productListRc.setAdapter(listAdapter);

    }
}