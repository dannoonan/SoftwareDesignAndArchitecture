package com.cs4125.bikerentalapp.repository.user;

import android.arch.lifecycle.LiveData;

import com.cs4125.bikerentalapp.model.db_entity.User;
import com.cs4125.bikerentalapp.model.entity.UserCredential;
import com.cs4125.bikerentalapp.web.ResponseBody;

import java.util.List;

public class CollectionDriverRepository extends UserRepositoryDecorator{


    CollectionDriverRepository(IUserRepository decoratedRepository){
        super(decoratedRepository);
    }

    public LiveData<ResponseBody> reintroduceBikes(List<Integer> bikeIds,
                                                   int nodeId){
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
