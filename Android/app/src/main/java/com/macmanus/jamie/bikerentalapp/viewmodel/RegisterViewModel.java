package com.macmanus.jamie.bikerentalapp.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.macmanus.jamie.bikerentalapp.repository.UserRepository;

import retrofit2.Response;

public class RegisterViewModel extends ViewModel {
    private UserRepository repository;

    public RegisterViewModel(UserRepository repository){
        this.repository = repository;
    }

    public LiveData<Response> register(String username, String password,
                                       String email, int userTypeId,
                                       String studentCardId){

        return repository.registerUser(username, password, email,
                userTypeId, studentCardId);
    }
}
