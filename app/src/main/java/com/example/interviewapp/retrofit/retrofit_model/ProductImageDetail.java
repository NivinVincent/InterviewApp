package com.example.interviewapp.retrofit.retrofit_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductImageDetail {

    @SerializedName("ProductImageID")
    @Expose
    private Integer productImageID;
    @SerializedName("OriginalImage")
    @Expose
    private String originalImage;
    @SerializedName("ThumbImage")
    @Expose
    private String thumbImage;
    @SerializedName("IsDefault")
    @Expose
    private Integer isDefault;

    public Integer getProductImageID() {
        return productImageID;
    }

    public void setProductImageID(Integer productImageID) {
        this.productImageID = productImageID;
    }

    public String getOriginalImage() {
        return originalImage;
    }

    public void setOriginalImage(String originalImage) {
        this.originalImage = originalImage;
    }

    public String getThumbImage() {
        return thumbImage;
    }

    public void setThumbImage(String thumbImage) {
        this.thumbImage = thumbImage;
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }

}
