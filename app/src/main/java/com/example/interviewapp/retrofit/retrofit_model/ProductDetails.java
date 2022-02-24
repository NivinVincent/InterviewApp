package com.example.interviewapp.retrofit.retrofit_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductDetails {

    @SerializedName("productInfoDetails")
    @Expose
    private List<ProductInfoDetail> productInfoDetails = null;
    @SerializedName("productImageDetails")
    @Expose
    private List<ProductImageDetail> productImageDetails = null;

    public List<ProductInfoDetail> getProductInfoDetails() {
        return productInfoDetails;
    }

    public void setProductInfoDetails(List<ProductInfoDetail> productInfoDetails) {
        this.productInfoDetails = productInfoDetails;
    }

    public List<ProductImageDetail> getProductImageDetails() {
        return productImageDetails;
    }

    public void setProductImageDetails(List<ProductImageDetail> productImageDetails) {
        this.productImageDetails = productImageDetails;
    }




}



