package com.cs4125.bikerentalapp.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.cs4125.bikerentalapp.model.entity.ReturnDetails;
import com.cs4125.bikerentalapp.repository.BikeRepository;
import com.cs4125.bikerentalapp.web.ResponseBody;

public class ReturnViewModel extends ViewModel {
    private BikeRepository repository;

    public void init(BikeRepository repository){
        this.repository = repository;
    }

    public LiveData<ResponseBody> returnBike(ReturnDetails details){

        return repository.returnBike(details);
    }
}
