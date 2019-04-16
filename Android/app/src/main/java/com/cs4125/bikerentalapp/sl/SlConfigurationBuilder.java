package com.cs4125.bikerentalapp.sl;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.cs4125.bikerentalapp.model.dao.UserDao;
import com.cs4125.bikerentalapp.model.db.LocalDatabase;
import com.cs4125.bikerentalapp.repository.BikeRepository;
import com.cs4125.bikerentalapp.repository.user.IUserRepository;
import com.cs4125.bikerentalapp.repository.user.UserRepository;
import com.cs4125.bikerentalapp.web.RetrofitInstance;
import com.cs4125.bikerentalapp.web.Webservice;

import java.util.concurrent.Executors;

public class SlConfigurationBuilder {

    public static void init(Context context){
        ServiceLocator.init(context);
    }

    public static void addWebservice(){
        Webservice webservice = RetrofitInstance.getRetrofitInstance().create(Webservice.class);
        ServiceLocator.addServiceInstance(Webservice.class, webservice);
    }

    public static void addUserRepositories(Context context){
        Webservice webservice = ServiceLocator.get(Webservice.class);

        UserDao userDao = Room.databaseBuilder(
                context,
                LocalDatabase.class,
                LocalDatabase.class.getName())
                .fallbackToDestructiveMigration()
                .build()
                .userDao();

        IUserRepository userRepository = new UserRepository(
                webservice,
                userDao,
                Executors.newSingleThreadExecutor());

        ServiceLocator.addServiceInstance(UserRepository.class, userRepository);
    }

    public static void addBikeRepository(){
        Webservice webservice = ServiceLocator.get(Webservice.class);

        BikeRepository bikeRepository = new BikeRepository(webservice,
                Executors.newSingleThreadExecutor());

        ServiceLocator.addServiceInstance(BikeRepository.class, bikeRepository);
    }
}
