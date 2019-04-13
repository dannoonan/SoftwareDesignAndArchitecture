package com.cs4125.bikerentalapp.web;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

public class Extend{
    @SerializedName("error")
    @Expose
    private HashMap returned;

    @Override
    public String toString(){
        return returned.toString();
    }
}
