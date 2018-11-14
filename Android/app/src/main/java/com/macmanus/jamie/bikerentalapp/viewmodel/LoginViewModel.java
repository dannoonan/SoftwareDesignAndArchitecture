package com.macmanus.jamie.bikerentalapp.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.macmanus.jamie.bikerentalapp.repository.UserRepository;

import retrofit2.Response;

public class LoginViewModel extends ViewModel {
    private UserRepository repository;

    public LoginViewModel(UserRepository repository){
        this.repository = repository;
    }

    public LiveData<Response> login(String username, String password){

        return repository.loginUser(username, password);
    }


}
