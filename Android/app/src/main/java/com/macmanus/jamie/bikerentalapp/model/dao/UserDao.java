package com.macmanus.jamie.bikerentalapp.model.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.macmanus.jamie.bikerentalapp.model.entity.User;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface UserDao {

    @Insert(onConflict = REPLACE)
    void save(User user);

    @Query("SELECT * FROM user WHERE username = :username")
    LiveData<User> load(String username);

    @Query("SELECT * FROM user WHERE username = :username")
    User getUser(String username);
}
