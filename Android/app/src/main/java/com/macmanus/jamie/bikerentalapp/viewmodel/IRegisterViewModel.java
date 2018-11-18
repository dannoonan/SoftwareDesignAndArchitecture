package com.macmanus.jamie.bikerentalapp.viewmodel;

import android.arch.lifecycle.LiveData;

import com.macmanus.jamie.bikerentalapp.repository.UserRepository;
import com.macmanus.jamie.bikerentalapp.web.ResponseBody;

public interface IRegisterViewModel {
    void init(UserRepository repository);

    LiveData<ResponseBody> register(String username, String password,
                                    String email, String studentCardId);
}
