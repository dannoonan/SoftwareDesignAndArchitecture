package com.cs4125.bikerentalapp.viewmodel;

import android.arch.lifecycle.LiveData;

import com.cs4125.bikerentalapp.repository.UserRepository;
import com.cs4125.bikerentalapp.web.ResponseBody;

public interface IRegisterViewModel {
    void init(UserRepository repository);

    LiveData<ResponseBody> register(String username, String password,
                                    String email, String studentCardId);
}
