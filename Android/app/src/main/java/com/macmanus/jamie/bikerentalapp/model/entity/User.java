package com.macmanus.jamie.bikerentalapp.model.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class User {

    @PrimaryKey
    private @NonNull String username;
    private boolean banned;

    public User(@NonNull String username, boolean banned){
        this.banned = banned;
        this.username = username;
    }

    @NonNull
    public String getUsername(){
        return username;
    }

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }

    public void setUsername(@NonNull String username) {
        this.username = username;
    }
}
