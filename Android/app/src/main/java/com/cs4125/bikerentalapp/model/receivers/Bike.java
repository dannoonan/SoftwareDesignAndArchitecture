package com.cs4125.bikerentalapp.model.receivers;

import android.arch.lifecycle.LiveData;

import com.cs4125.bikerentalapp.model.entity.RentReturnDetails;
import com.cs4125.bikerentalapp.repository.IBikeRepository;
import com.cs4125.bikerentalapp.web.ResponseBody;

public class Bike implements Vehicle {

    private final RentReturnDetails details;
    private IBikeRepository repository;

    public Bike(RentReturnDetails details, IBikeRepository repository){
        this.details = details;
        this.repository = repository;
    }

    @Override
    public LiveData<ResponseBody> rentVehicle() {
        LiveData<ResponseBody> liveResponse;
        liveResponse = repository.rentVehicle(details.getVehicleId(), details.getUserId());
        return liveResponse;
    }

    @Override
    public LiveData<ResponseBody> returnVehicle() {
        LiveData<ResponseBody> liveResponse;
        liveResponse = repository.returnVehicle(details);
        return liveResponse;
    }
}
