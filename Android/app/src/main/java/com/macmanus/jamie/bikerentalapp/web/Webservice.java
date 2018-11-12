package com.macmanus.jamie.bikerentalapp.web;

import com.macmanus.jamie.bikerentalapp.model.entity.GithubUser;
import com.macmanus.jamie.bikerentalapp.model.entity.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Webservice {

    @GET("/user/{user}")
    Call<User> getUser(@Path("user") String userId);

    @PUT("/user")
    Call<User> loginUser(@Body String userId, @Body String password);

    @POST("/user")
    Call<User> registerUser(@Body String userId, @Body String password);

    @GET("https://api.github.com/users/{username}")
    Call<GithubUser> getGithubUserData(@Path("username") String username);
}
