package com.cs4125.bikerentalapp.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.cs4125.bikerentalapp.repository.BikeRepository;
import com.cs4125.bikerentalapp.web.ResponseBody;

import retrofit2.Response;

public class RentViewModel extends ViewModel {
    private BikeRepository repository;

    public void init(BikeRepository repository){
        this.repository = repository;
    }

    public LiveData<Response> setStatus(int UserId, int StatusId){

        return repository.setBikeStatus(UserId, StatusId);
    }

    public LiveData<ResponseBody> rentBike(String username, int bikeId, int paid){

        return repository.placeOrder(username, bikeId, paid);
    }
}
