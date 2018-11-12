package com.macmanus.jamie.bikerentalapp.view;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.macmanus.jamie.bikerentalapp.R;
import com.macmanus.jamie.bikerentalapp.model.dao.GithubUserDao;
import com.macmanus.jamie.bikerentalapp.model.db.GithubUserDatabase;
import com.macmanus.jamie.bikerentalapp.repository.TestRepository;
import com.macmanus.jamie.bikerentalapp.sl.ServiceLocator;
import com.macmanus.jamie.bikerentalapp.web.RetrofitInstance;
import com.macmanus.jamie.bikerentalapp.web.Webservice;

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
     * */
    private void initializeServiceLocator(){
        Webservice webservice = RetrofitInstance.getRetrofitInstance().create(Webservice.class);

        GithubUserDao guDao = Room.databaseBuilder(this, GithubUserDatabase.class,
                "GithubUserDatabase")
                .fallbackToDestructiveMigration()
                .build()
                .githubUserDao();
        TestRepository tRepo = new TestRepository(webservice, guDao, Executors.newSingleThreadExecutor());


        ServiceLocator.init(this);
        ServiceLocator.addServiceInstance(TestRepository.class, tRepo);
        ServiceLocator.addServiceInstance(Webservice.class, webservice);
    }
}
