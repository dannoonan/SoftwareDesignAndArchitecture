package com.cs4125.bikerentalapp.repository.user;

public abstract class UserRepositoryDecorator implements IUserRepository {
    protected IUserRepository decoratedRepository;

    public UserRepositoryDecorator(IUserRepository decoratedRepository){
        this.decoratedRepository = decoratedRepository;
    }

}
