package com.cs4125.bikerentalapp.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.cs4125.bikerentalapp.repository.IBikeRepository;
import com.cs4125.bikerentalapp.web.ResponseBody;

class RentViewModel extends ViewModel {
    private IBikeRepository repository;

    public void init(IBikeRepository repository){
        this.repository = repository;
    }

    public LiveData<ResponseBody> rentVehicle(int bikeId, int userId){

        return repository.rentVehicle(bikeId, userId);
    }
}
