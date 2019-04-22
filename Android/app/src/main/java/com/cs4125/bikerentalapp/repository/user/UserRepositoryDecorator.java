package com.cs4125.bikerentalapp.repository.user;

//DECORATOR PATTERN
abstract class UserRepositoryDecorator implements IUserRepository {
    final IUserRepository decoratedRepository;

    UserRepositoryDecorator(IUserRepository decoratedRepository){
        this.decoratedRepository = decoratedRepository;
    }
}
