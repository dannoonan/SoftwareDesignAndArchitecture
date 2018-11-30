package com.cs4125.bikerentalapp.repository.user;

import android.arch.lifecycle.LiveData;

import com.cs4125.bikerentalapp.model.db_entity.User;
import com.cs4125.bikerentalapp.model.entity.UserCredential;
import com.cs4125.bikerentalapp.web.ResponseBody;

public interface IUserRepository {
    LiveData<User> getUser(String userId);

    LiveData<ResponseBody> registerUser(UserCredential credential);

    LiveData<ResponseBody> loginUser(UserCredential credential);
}
