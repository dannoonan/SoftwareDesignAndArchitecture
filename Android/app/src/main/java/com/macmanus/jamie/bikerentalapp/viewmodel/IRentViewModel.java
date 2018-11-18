package com.macmanus.jamie.bikerentalapp.viewmodel;

import android.arch.lifecycle.LiveData;

import com.macmanus.jamie.bikerentalapp.repository.BikeRepository;
import com.macmanus.jamie.bikerentalapp.web.ResponseBody;

import retrofit2.Response;

public interface IRentViewModel {
    void init(BikeRepository repository);

    LiveData<Response> setStatus(int UserId, int StatusId);

    LiveData<ResponseBody> rentBike(String username, int bikeId, int paid);
}
