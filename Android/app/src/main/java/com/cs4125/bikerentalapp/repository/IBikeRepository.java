package com.cs4125.bikerentalapp.repository;

import android.arch.lifecycle.LiveData;

import com.cs4125.bikerentalapp.model.entity.ReturnDetails;
import com.cs4125.bikerentalapp.web.ResponseBody;

public interface IBikeRepository {
    LiveData<ResponseBody> rentBike(int bikeId, int userId);

    LiveData<ResponseBody> returnBike(ReturnDetails details);

    LiveData<ResponseBody> getAllBikes();
}
