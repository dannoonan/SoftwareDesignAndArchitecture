package com.cs4125.bikerentalapp.viewmodel;

import android.arch.lifecycle.LiveData;

import com.cs4125.bikerentalapp.repository.BikeRepository;
import com.cs4125.bikerentalapp.web.ResponseBody;

import retrofit2.Response;

public interface IRentViewModel {
    void init(BikeRepository repository);

    LiveData<Response> setStatus(int UserId, int StatusId);

    LiveData<ResponseBody> rentBike(int bikeId, int userId);

    LiveData<ResponseBody> returnBike(int orderId,
                                      int latitude,
                                      int longitude,
                                      int amountPaid,
                                      int studentCardId,
                                      int nodeId);
}
