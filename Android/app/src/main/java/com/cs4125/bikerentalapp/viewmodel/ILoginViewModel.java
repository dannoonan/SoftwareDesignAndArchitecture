package com.cs4125.bikerentalapp.viewmodel;

import android.arch.lifecycle.LiveData;

import com.cs4125.bikerentalapp.repository.UserRepository;
import com.cs4125.bikerentalapp.web.ResponseBody;

public interface ILoginViewModel {

    void init(UserRepository userRepository);

    LiveData<ResponseBody> login(String username, String password);
}
