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
    @POST("/rent")
    Call<ResponseBody> rentVehicle(@Field("id") int bikeId, @Field("userId") int userId);

    @FormUrlEncoded
    @POST("/return")
    Call<ResponseBody> returnVehicle(@Field("userId") int userId,
                                     @Field("bikeId") int vehicleId,
                                     @Field("latitude") Double latitude,
                                     @Field("longitude") Double longitude,
                                     @Field("amountPaid") double amountPaid,
                                     @Field("studentCardId") int studentCardId,
                                     @Field("nodeId") Integer nodeId);

    @FormUrlEncoded
    @POST("/bike")
    Call<ResponseBody> addBikes(@Field("bikeType") String bikeType,
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


    @GET("/nodes")
    Call<ResponseBody> getAllNodes();

    @GET("/bike")
    Call<ResponseBody> getAllBikes();

}
