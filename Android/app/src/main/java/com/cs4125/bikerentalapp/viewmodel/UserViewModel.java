package com.cs4125.bikerentalapp.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.cs4125.bikerentalapp.model.db_entity.User;
import com.cs4125.bikerentalapp.repository.user.IUserRepository;

public class UserViewModel extends ViewModel {

    private LiveData<User> user;
    private IUserRepository userRepository;

    public void init(IUserRepository userRepo) {
        this.userRepository = userRepo;
    }



    public LiveData<User> getUser(String username) {
        if(this.user == null){
            this.user = userRepository.getUser(username);
        }
        return this.user;
    }

    public void insertUser(User user) {
        userRepository.insertUser(user);
    }
}
