package com.macmanus.jamie.bikerentalapp.web;

import com.macmanus.jamie.bikerentalapp.model.entity.GithubUser;
import com.macmanus.jamie.bikerentalapp.model.entity.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Webservice {

    @GET("/user/{user}")
    Call<User> getUser(@Path("user") String userId);

    @PUT("/user")
    Call<User> loginUser(@Body String userId, @Body String password);

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

    @GET("https://api.github.com/users/{username}")
    Call<GithubUser> getGithubUserData(@Path("username") String username);
}
