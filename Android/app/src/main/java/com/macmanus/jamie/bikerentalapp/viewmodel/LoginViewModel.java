package com.macmanus.jamie.bikerentalapp.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.macmanus.jamie.bikerentalapp.repository.UserRepository;
import com.macmanus.jamie.bikerentalapp.web.ResponseBody;

public class LoginViewModel extends ViewModel implements ILoginViewModel {
    private UserRepository repository;

    public void init(UserRepository repository){
        this.repository = repository;
    }

    public LiveData<ResponseBody> login(String username, String password){

        return repository.loginUser(username, password);
    }


}
