package com.cs4125.bikerentalapp.repository.user;

import android.arch.lifecycle.LiveData;

import com.cs4125.bikerentalapp.model.db_entity.User;
import com.cs4125.bikerentalapp.model.entity.UserCredential;
import com.cs4125.bikerentalapp.web.ResponseBody;

import java.util.List;


public class AdminRepository extends UserRepositoryDecorator {

    public AdminRepository(IUserRepository decoratedRepository) {
        super(decoratedRepository);
    }

    // Stubbed out method implementations
    public LiveData<ResponseBody> addBikes(int numBikes,
                                           int nodeId,
                                           String bikeType){
        return null;
    }

    public LiveData<ResponseBody> scheduleCollection(List<Integer> bikeIds,
                                                     int dropOffNodeId){
        return null;
    }

    public LiveData<ResponseBody> scheduleRepair(List<Integer> bikeIds){
        return null;
    }

    @Override
    public LiveData<User> getUser(String userId) {
        return decoratedRepository.getUser(userId);
    }

    @Override
    public LiveData<ResponseBody> registerUser(UserCredential credential) {
        return decoratedRepository.registerUser(credential);
    }

    @Override
    public LiveData<ResponseBody> loginUser(UserCredential credential) {
        return decoratedRepository.loginUser(credential);
    }
}
