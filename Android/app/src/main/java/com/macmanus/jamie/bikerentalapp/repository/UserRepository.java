package com.macmanus.jamie.bikerentalapp.repository;

import android.arch.lifecycle.LiveData;

import com.macmanus.jamie.bikerentalapp.model.entity.User;
import com.macmanus.jamie.bikerentalapp.model.dao.UserDao;
import com.macmanus.jamie.bikerentalapp.web.Webservice;

import java.io.IOException;
import java.util.concurrent.Executor;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Response;

@Singleton
public class UserRepository {
    private final Webservice webservice;
    private final UserDao userDao;
    private final Executor executor;

    @Inject
    public UserRepository(Webservice webservice, UserDao userDao, Executor executor){
        this.webservice = webservice;
        this.userDao = userDao;
        this.executor = executor;
    }

    public LiveData<User> getUser(String userId) {

        refreshUser(userId);

        // Returns a LiveData object directly from the database.
        return userDao.load(userId);
    }

    private void refreshUser(final String username) {
        // Runs in a background thread.
        executor.execute(() -> {
            // Check if user data was fetched recently.
            User user = userDao.getUser(username);
            if (!(user == null)) {
                // Refreshes the data.
                Response<User> response = null;
                try {
                    response = webservice.getUser(username).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // Updates the database. The LiveData object automatically
                // refreshes, so we don't need to do anything else here.
                userDao.save(response.body());
            }
        });
    }
}
