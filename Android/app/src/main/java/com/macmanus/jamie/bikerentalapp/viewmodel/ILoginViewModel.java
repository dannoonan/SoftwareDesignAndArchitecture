package com.macmanus.jamie.bikerentalapp.viewmodel;

import android.arch.lifecycle.LiveData;

import com.macmanus.jamie.bikerentalapp.repository.UserRepository;
import com.macmanus.jamie.bikerentalapp.web.ResponseBody;

public interface ILoginViewModel {

    void init(UserRepository userRepository);

    LiveData<ResponseBody> login(String username, String password);
}
