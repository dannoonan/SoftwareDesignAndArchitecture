package com.macmanus.jamie.bikerentalapp.model.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class User {

    @PrimaryKey
    public int userId;
    public String studentCardId;
    public String username;
    public String email;
    public String userType;
    public boolean banned;
}
