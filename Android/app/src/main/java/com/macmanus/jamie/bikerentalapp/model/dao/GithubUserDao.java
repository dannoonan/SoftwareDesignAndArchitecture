package com.macmanus.jamie.bikerentalapp.model.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.macmanus.jamie.bikerentalapp.model.entity.GithubUser;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface GithubUserDao {

    @Insert(onConflict = REPLACE)
    void save(GithubUser user);

    @Query("SELECT * FROM githubuser WHERE login LIKE :username")
    LiveData<GithubUser> load(String username);
}
