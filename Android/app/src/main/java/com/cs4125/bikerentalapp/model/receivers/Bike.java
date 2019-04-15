package com.cs4125.bikerentalapp.model.receivers;

import android.arch.lifecycle.LiveData;

import com.cs4125.bikerentalapp.model.entity.RentReturnDetails;
import com.cs4125.bikerentalapp.repository.IBikeRepository;
import com.cs4125.bikerentalapp.web.ResponseBody;

public class Bike implements Vehicle {

    private int bikeId;
    private int userId;
    private RentReturnDetails details;
    private IBikeRepository repository;

    public Bike(RentReturnDetails details, IBikeRepository repository){
        this.bikeId = details.getVehicleId();
        this.userId = details.getUserId();
        this.repository = repository;
    }

    @Override
    public LiveData<ResponseBody> Rent() {
        LiveData<ResponseBody> liveResponse;
        liveResponse = repository.rentBike(bikeId, userId);
        return liveResponse;
    }

    @Override
    public LiveData<ResponseBody> Return() {
        LiveData<ResponseBody> liveResponse;
        liveResponse = repository.returnBike(details);
        return liveResponse;
    }
}
