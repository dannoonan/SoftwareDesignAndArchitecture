package com.macmanus.jamie.bikerentalapp.model.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.macmanus.jamie.bikerentalapp.model.dao.GithubUserDao;
import com.macmanus.jamie.bikerentalapp.model.entity.GithubUser;

@Database(entities = {GithubUser.class}, version = 2, exportSchema = false)
public abstract class GithubUserDatabase extends RoomDatabase {
    public abstract GithubUserDao githubUserDao();
}
