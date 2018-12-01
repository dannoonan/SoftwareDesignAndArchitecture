package com.cs4125.bikerentalapp.repository.user;

//DECORATOR PATTERN
public abstract class UserRepositoryDecorator implements IUserRepository {
    protected IUserRepository decoratedRepository;

    public UserRepositoryDecorator(IUserRepository decoratedRepository){
        this.decoratedRepository = decoratedRepository;
    }

}
