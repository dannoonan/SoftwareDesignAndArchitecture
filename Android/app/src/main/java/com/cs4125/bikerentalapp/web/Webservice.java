package com.cs4125.bikerentalapp.web;

import com.cs4125.bikerentalapp.model.db_entity.User;

import java.util.List;

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

    @FormUrlEncoded
    @POST("/rentBike/{bikeId}")
    Call<ResponseBody> rentBike(@Path("bikeId") int bikeId, @Field("userId") int userId);

    @FormUrlEncoded
    @POST("/return}")
    Call<ResponseBody> returnBike(@Field("orderId") int orderId,
                                  @Field("latitude") int latitude,
                                  @Field("longitude") int longitude,
                                  @Field("amountPaid") double amountPaid,
                                  @Field("studentCardId") int studentCardId,
                                  @Field("nodeId") int nodeId);
    @FormUrlEncoded
    @POST("/bike/add")
    Call<ResponseBody> addBikes(@Field("numBikes") int numBikes,
                                @Field("bikeType") String bikeType,
                                @Field("nodeId") int nodeId);

    @FormUrlEncoded
    @POST("/bike/collect")
    Call<ResponseBody> scheduleCollection(@Field("bikeIds") List<Integer> bikeIds,
                                          @Field("dropOffNodeId") int dropOffNodeId);

    @FormUrlEncoded
    @POST("/bike/repair")
    Call<ResponseBody> scheduleRepair(@Field("bikeIds") List<Integer> bikeIds);

    @FormUrlEncoded
    @POST("/bike/reintroduce")
    Call<ResponseBody> reintroduceBikes(@Field("bikeIds") List<Integer> bikIds,
                                        @Field("nodeId") int nodeId);


    @GET("/bike")
    Call<ResponseBody> getAllBikes();

}
