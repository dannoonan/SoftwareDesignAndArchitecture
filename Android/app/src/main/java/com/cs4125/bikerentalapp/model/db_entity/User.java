package com.cs4125.bikerentalapp.model.db_entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "user")
public class User {

    @PrimaryKey
    @ColumnInfo(name = "userId")
    private int userId;

    @ColumnInfo(name = "studentCardId")
    private String studentCardId;

    @ColumnInfo(name = "username")
    private String username;

    @ColumnInfo(name = "email")
    private String email;

    @ColumnInfo(name = "userType")
    private String userType;

    @ColumnInfo(name = "banned")
    private boolean banned;

    public int getUserId(){ return userId; }
    public void setUserId(int in){ userId = in; }
    public String getStudentCardId(){ return studentCardId; }
    public void setStudentCardId(String in){ studentCardId = in; }
    public String getUsername(){ return username; }
    public void setUsername(String in){ username = in; }
    public String getEmail(){ return email; }
    public void setEmail(String in){ email = in; }
    public String getUserType(){ return userType; }
    public void setUserType(String in){ userType = in; }
    public boolean getBanned(){ return banned; }
    public void setBanned(boolean in){ banned = in; }
}
