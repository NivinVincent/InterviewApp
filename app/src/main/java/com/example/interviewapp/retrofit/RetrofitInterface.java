package com.example.interviewapp.retrofit;

import com.example.interviewapp.retrofit.retrofit_model.ProductDetailsResponse;
import com.example.interviewapp.retrofit.retrofit_model.ProductListResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RetrofitInterface {

    public static String BASE_URL = " http://mockup.aabasoft.info/SampleprojectAPI/";

    @FormUrlEncoded
    @POST("listproducts")
    Call<ProductListResponse> listproducts(@Field("CustomerId") String CustomerId,
                                           @Field("SocietyId") String SocietyId,
                                           @Field("PageNumber") String PageNumber,
                                           @Field("PageSize") String PageSize
    );


    @FormUrlEncoded
    @POST("productdetails")
    Call<ProductDetailsResponse> productdetails(@Field("CustomerId") String CustomerId,
                                                @Field("ProductID") String ProductId,
                                                @Field("SocietyId") String SocietyId
    );


}
