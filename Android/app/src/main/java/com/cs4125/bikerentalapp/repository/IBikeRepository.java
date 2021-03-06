package com.cs4125.bikerentalapp.repository;

import android.arch.lifecycle.LiveData;

import com.cs4125.bikerentalapp.model.entity.BikeCredential;
import com.cs4125.bikerentalapp.model.entity.RentReturnDetails;
import com.cs4125.bikerentalapp.web.ResponseBody;

public interface IBikeRepository {
    LiveData<ResponseBody> rentVehicle(int bikeId, int userId);

    LiveData<ResponseBody> returnVehicle(RentReturnDetails details);

    LiveData<ResponseBody> getAllBikes();

    LiveData<ResponseBody> addBike(BikeCredential bikeCredential);
}
