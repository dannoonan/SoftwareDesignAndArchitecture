package com.macmanus.jamie.bikerentalapp.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.macmanus.jamie.bikerentalapp.repository.UserRepository;
import com.macmanus.jamie.bikerentalapp.web.ResponseBody;

public class RegisterViewModel extends ViewModel implements IRegisterViewModel{
    private UserRepository repository;

    public void init(UserRepository repository){
        this.repository = repository;
    }

    public LiveData<ResponseBody> register(String username, String password,
                                           String email, String studentCardId){

        return repository.registerUser(username, password, email, studentCardId);
    }
}
