package com.macmanus.jamie.bikerentalapp.web;

import com.macmanus.jamie.bikerentalapp.model.entity.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Webservice {

    @GET("/user/{user}")
    Call<User> getUser(@Path("user") String userId);

    @FormUrlEncoded
    @PUT("/user")
    Call<ResponseBody> loginUser(@Field("username") String userId, @Field("password") String password);
    //issue with 2 body params
    @FormUrlEncoded
    @POST("/user")
    Call<ResponseBody> registerUser(@Field("username") String username,
                            @Field("password") String password,
                            @Field("email") String email,
                            @Field("userTypeId") int userTypeId,
                            @Field("studentCardId") String studentCardId);

    @FormUrlEncoded
    @POST("/user")
    Call<ResponseBody> registerUser(@Field("username") String username,
                            @Field("password") String password,
                            @Field("email") String email,
                            @Field("userTypeId") int userTypeId);

    @PUT("/bike/{id}")
    Call<User> setBikeStatus(@Path("id") int id, @Query("status") int statusid);

    @POST("/order")
    Call<ResponseBody> placeOrder(@Query("userName") String username,@Query("bikeId") int bikeId, @Query("amountPaid") int paid);

}
