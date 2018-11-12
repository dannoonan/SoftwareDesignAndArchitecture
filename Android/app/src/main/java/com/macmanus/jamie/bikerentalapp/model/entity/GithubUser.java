package com.macmanus.jamie.bikerentalapp.model.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class GithubUser {

    @PrimaryKey
    private @NonNull String login;
    private String avatar_url;

    public GithubUser(@NonNull String login, String avatar_url){
        this.login = login;
        this.avatar_url = avatar_url;
    }

    public void setLogin(@NonNull String login) {
        this.login = login;
    }

    public void setAvatar_url(String avatar_url){
        this.avatar_url = avatar_url;
    }

    @NonNull
    public String getLogin(){
        return login;
    }

    public String getAvatar_url(){
        return avatar_url;
    }
}
