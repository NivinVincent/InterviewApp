package com.example.interviewapp.retrofit.retrofit_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductListResponse {

    @SerializedName("SearchProductMobile")
    @Expose
    private List<ProductListResponseSearchMobile> searchProductMobile = null;
    @SerializedName("TotalCount")
    @Expose
    private Integer totalCount;

    public List<ProductListResponseSearchMobile> getSearchProductMobile() {
        return searchProductMobile;
    }

    public void setSearchProductMobile(List<ProductListResponseSearchMobile> searchProductMobile) {
        this.searchProductMobile = searchProductMobile;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }


}
