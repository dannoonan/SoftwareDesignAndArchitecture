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

    public LiveData<ResponseBody> rentBike(int bikeId, int userId){

        return repository.rentBike(bikeId, userId);
    }

    public LiveData<ResponseBody> returnBike(int orderId,
                                             int latitude,
                                             int longitude,
                                             int amountPaid,
                                             int studentCardId,
                                             int nodeId){

        return repository.returnBike(orderId, latitude, longitude, amountPaid, studentCardId, nodeId);
    }
}
