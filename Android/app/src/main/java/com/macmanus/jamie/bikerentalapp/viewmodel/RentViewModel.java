package com.macmanus.jamie.bikerentalapp.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.macmanus.jamie.bikerentalapp.repository.BikeRepository;
import com.macmanus.jamie.bikerentalapp.web.ResponseBody;

import retrofit2.Response;

public class RentViewModel extends ViewModel {
    private BikeRepository repository;

    public RentViewModel(BikeRepository repository){
        this.repository = repository;
    }

    public LiveData<Response> setStatus(int UserId, int StatusId){

        return repository.setBikeStatus(UserId, StatusId);
    }

    public LiveData<ResponseBody> rentBike(String username, int bikeId, int paid){

        return repository.placeOrder(username, bikeId, paid);
    }
}
