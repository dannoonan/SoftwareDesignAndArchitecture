package com.macmanus.jamie.bikerentalapp.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.macmanus.jamie.bikerentalapp.repository.UserRepository;

import retrofit2.Response;

public class RentViewModel extends ViewModel {
    private UserRepository repository;

    public RentViewModel(UserRepository repository){
        this.repository = repository;
    }

    public LiveData<Response> setStatus(int UserId, int StatusId){

        return repository.setBikeStatus(UserId, StatusId);
    }
}
