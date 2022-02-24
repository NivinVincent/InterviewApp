package com.example.interviewapp.retrofit.retrofit_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductInfoDetail {

    @SerializedName("SocietyproductID")
    @Expose
    private Integer societyproductID;
    @SerializedName("ProductID")
    @Expose
    private Integer productID;
    @SerializedName("Title")
    @Expose
    private String title;
    @SerializedName("CategoryID")
    @Expose
    private Integer categoryID;
    @SerializedName("SubcategoryID")
    @Expose
    private Integer subcategoryID;
    @SerializedName("CategoryTitle")
    @Expose
    private String categoryTitle;
    @SerializedName("SubCategoryTitle")
    @Expose
    private String subCategoryTitle;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("Mrp")
    @Expose
    private Float mrp;
    @SerializedName("Finalprice")
    @Expose
    private Float finalprice;
    @SerializedName("Unit")
    @Expose
    private String unit;
    @SerializedName("AddedtoCart")
    @Expose
    private String addedtoCart;
    @SerializedName("CartId")
    @Expose
    private Integer cartId;
    @SerializedName("CartQuantity")
    @Expose
    private Integer cartQuantity;
    @SerializedName("CartCount")
    @Expose
    private Integer cartCount;

    public Integer getSocietyproductID() {
        return societyproductID;
    }

    public void setSocietyproductID(Integer societyproductID) {
        this.societyproductID = societyproductID;
    }

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Integer categoryID) {
        this.categoryID = categoryID;
    }

    public Integer getSubcategoryID() {
        return subcategoryID;
    }

    public void setSubcategoryID(Integer subcategoryID) {
        this.subcategoryID = subcategoryID;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public String getSubCategoryTitle() {
        return subCategoryTitle;
    }

    public void setSubCategoryTitle(String subCategoryTitle) {
        this.subCategoryTitle = subCategoryTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getMrp() {
        return mrp;
    }

    public void setMrp(Float mrp) {
        this.mrp = mrp;
    }

    public Float getFinalprice() {
        return finalprice;
    }

    public void setFinalprice(Float finalprice) {
        this.finalprice = finalprice;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getAddedtoCart() {
        return addedtoCart;
    }

    public void setAddedtoCart(String addedtoCart) {
        this.addedtoCart = addedtoCart;
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public Integer getCartQuantity() {
        return cartQuantity;
    }

    public void setCartQuantity(Integer cartQuantity) {
        this.cartQuantity = cartQuantity;
    }

    public Integer getCartCount() {
        return cartCount;
    }

    public void setCartCount(Integer cartCount) {
        this.cartCount = cartCount;
    }

}
