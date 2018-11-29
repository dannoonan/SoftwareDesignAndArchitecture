package com.cs4125.bikerentalapp.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.cs4125.bikerentalapp.repository.UserRepository;
import com.cs4125.bikerentalapp.web.ResponseBody;

public class LoginViewModel extends ViewModel{
    private UserRepository repository;

    public void init(UserRepository repository){
        this.repository = repository;
    }

    public LiveData<ResponseBody> login(String username, String password){

        return repository.loginUser(username, password);
    }


}
