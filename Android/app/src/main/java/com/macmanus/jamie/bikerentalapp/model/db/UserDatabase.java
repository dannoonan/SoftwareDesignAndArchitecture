package com.macmanus.jamie.bikerentalapp.model.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.macmanus.jamie.bikerentalapp.model.dao.UserDao;
import com.macmanus.jamie.bikerentalapp.model.entity.User;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {

    public abstract UserDao userDao();

}
