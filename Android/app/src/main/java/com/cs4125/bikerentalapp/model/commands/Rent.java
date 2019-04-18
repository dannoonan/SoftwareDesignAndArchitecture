package com.cs4125.bikerentalapp.model.commands;

import android.arch.lifecycle.LiveData;

import com.cs4125.bikerentalapp.model.receivers.Vehicle;
import com.cs4125.bikerentalapp.web.ResponseBody;

public class Rent implements Command {

    private Vehicle vehicle;

    public Rent(Vehicle aVehicle){
        vehicle = aVehicle;
    }

    public LiveData<ResponseBody> execute(){
        return vehicle.rentVehicle();
    }
}
