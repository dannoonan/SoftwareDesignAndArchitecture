package com.cs4125.bikerentalapp.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.cs4125.bikerentalapp.repository.UserRepository;
import com.cs4125.bikerentalapp.web.ResponseBody;

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
