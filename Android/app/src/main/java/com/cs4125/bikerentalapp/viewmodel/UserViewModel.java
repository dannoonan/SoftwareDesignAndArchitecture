package com.cs4125.bikerentalapp.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.cs4125.bikerentalapp.model.entity.User;
import com.cs4125.bikerentalapp.repository.UserRepository;

public class UserViewModel extends ViewModel {

    private LiveData<User> user;
    private UserRepository userRepository;

    public UserViewModel(UserRepository userRepo) {
        this.userRepository = userRepo;
    }

    public void init(String username) {
        if (this.user != null) {
            return;
        }
        user = userRepository.getUser(username);
    }

    public LiveData<User> getUser() {
        return this.user;
    }

}
