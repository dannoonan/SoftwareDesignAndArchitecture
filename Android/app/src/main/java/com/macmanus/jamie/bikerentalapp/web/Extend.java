package com.macmanus.jamie.bikerentalapp.web;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Extend{
    @SerializedName("error")
    @Expose
    private String error;

    @Override
    public String toString(){
        return "error: " + error;
    }
}
