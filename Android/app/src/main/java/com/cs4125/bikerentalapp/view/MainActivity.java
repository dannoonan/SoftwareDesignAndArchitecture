package com.cs4125.bikerentalapp.view;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.cs4125.bikerentalapp.R;
import com.cs4125.bikerentalapp.model.dao.UserDao;
import com.cs4125.bikerentalapp.model.db.UserDatabase;
import com.cs4125.bikerentalapp.repository.BikeRepository;
import com.cs4125.bikerentalapp.repository.UserRepository;
import com.cs4125.bikerentalapp.sl.ServiceLocator;
import com.cs4125.bikerentalapp.web.RetrofitInstance;
import com.cs4125.bikerentalapp.web.Webservice;

import java.util.concurrent.Executors;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        initializeServiceLocator();
    }

    /**
     * All Repositories should be added to the ServiceLocator here
     *
     * TODO: Find a cleaner way of implementing this. Perhaps using factory classes.
     * */
    private void initializeServiceLocator(){
        Webservice webservice = RetrofitInstance.getRetrofitInstance().create(Webservice.class);

        // USER REPOSITORY


        UserDao userDao = Room.databaseBuilder(this,
                UserDatabase.class,
                UserDatabase.class.getName())
                .fallbackToDestructiveMigration()
                .build()
                .userDao();

        UserRepository userRepository = new UserRepository(webservice,
                userDao,
                Executors.newSingleThreadExecutor());

        BikeRepository bikeRepository = new BikeRepository(webservice,
                Executors.newSingleThreadExecutor());

        ServiceLocator.init(this);
        ServiceLocator.addServiceInstance(Webservice.class, webservice);
        ServiceLocator.addServiceInstance(UserRepository.class, userRepository);
        ServiceLocator.addServiceInstance(BikeRepository.class, bikeRepository);
    }


}
