package com.cs4125.bikerentalapp.model.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.cs4125.bikerentalapp.model.dao.UserDao;
import com.cs4125.bikerentalapp.model.db_entity.User;


@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {

    public abstract UserDao userDao();

}
