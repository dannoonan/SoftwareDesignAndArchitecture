package com.cs4125.bikerentalapp.model.commands;

import android.arch.lifecycle.LiveData;

import com.cs4125.bikerentalapp.model.receivers.Vehicle;
import com.cs4125.bikerentalapp.web.ResponseBody;

public class Return implements Command {

    private Vehicle vehicle;

    public Return(Vehicle aVehicle){
        vehicle = aVehicle;
    }

    public LiveData<ResponseBody> execute(){
       return vehicle.returnVehicle();
    }

}
