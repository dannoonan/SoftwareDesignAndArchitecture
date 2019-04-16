package com.cs4125.bikerentalapp.model.receivers;

import android.arch.lifecycle.LiveData;

import com.cs4125.bikerentalapp.web.ResponseBody;

public interface Vehicle {

    LiveData<ResponseBody> rentBike();

    LiveData<ResponseBody> returnBike();
}
