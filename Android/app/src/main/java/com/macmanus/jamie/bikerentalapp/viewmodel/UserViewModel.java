package com.macmanus.jamie.bikerentalapp.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.macmanus.jamie.bikerentalapp.model.entity.User;
import com.macmanus.jamie.bikerentalapp.repository.UserRepository;

public class UserViewModel extends ViewModel {

    private LiveData<User> user;
    private UserRepository userRepository;

    public UserViewModel(UserRepository userRepo) {
        this.userRepository = userRepo;
    }

    public void init(String username) {
        if (this.user != null) {
            // ViewModel is created on a per-Fragment basis, so the userId
            // doesn't change.
            return;
        }
        user = userRepository.getUser(username);
    }

    public LiveData<User> getUser() {
        return this.user;
    }

}
