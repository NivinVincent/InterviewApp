package com.example.interviewapp.retrofit.retrofit_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductListResponseSearchMobile {

    @SerializedName("SocietyproductID")
    @Expose
    private Integer societyproductID;
    @SerializedName("Title")
    @Expose
    private String title;
    @SerializedName("OriginalImage")
    @Expose
    private String originalImage;
    @SerializedName("ThumbImage")
    @Expose
    private String thumbImage;
    @SerializedName("Finalprice")
    @Expose
    private Float finalprice;
    @SerializedName("Mrp")
    @Expose
    private Float mrp;
    @SerializedName("CartQuantity")
    @Expose
    private Integer cartQuantity;
    @SerializedName("TotalCount")
    @Expose
    private Integer totalCount;
    @SerializedName("CartId")
    @Expose
    private Integer cartId;
    @SerializedName("CartCount")
    @Expose
    private Integer cartCount;
    @SerializedName("Unit")
    @Expose
    private String unit;

    private byte[] image_blob;

    public byte[] getImage_blob() {
        return image_blob;
    }

    public void setImage_blob(byte[] image_blob) {
        this.image_blob = image_blob;
    }

    public Integer getSocietyproductID() {
        return societyproductID;
    }

    public void setSocietyproductID(Integer societyproductID) {
        this.societyproductID = societyproductID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Float getFinalprice() {
        return finalprice;
    }

    public void setFinalprice(Float finalprice) {
        this.finalprice = finalprice;
    }

    public Float getMrp() {
        return mrp;
    }

    public void setMrp(Float mrp) {
        this.mrp = mrp;
    }

    public Integer getCartQuantity() {
        return cartQuantity;
    }

    public void setCartQuantity(Integer cartQuantity) {
        this.cartQuantity = cartQuantity;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public Integer getCartCount() {
        return cartCount;
    }

    public void setCartCount(Integer cartCount) {
        this.cartCount = cartCount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }


}
