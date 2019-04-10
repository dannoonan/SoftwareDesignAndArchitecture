package com.cs4125.bikerentalapp.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.cs4125.bikerentalapp.model.db_entity.User;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface UserDao {

    @Insert(onConflict = REPLACE)
    long insert(User user);

    @Query("SELECT * FROM user WHERE username LIKE :username")
    User load(String username);
}
