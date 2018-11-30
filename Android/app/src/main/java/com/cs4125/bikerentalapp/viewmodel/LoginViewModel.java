package com.cs4125.bikerentalapp.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.cs4125.bikerentalapp.model.entity.UserCredential;
import com.cs4125.bikerentalapp.repository.UserRepository;
import com.cs4125.bikerentalapp.web.ResponseBody;

public class LoginViewModel extends ViewModel{
    private UserRepository repository;

    public void init(UserRepository repository){
        this.repository = repository;
    }

    public LiveData<ResponseBody> login(UserCredential credential){

        return repository.loginUser(credential);
    }


}
