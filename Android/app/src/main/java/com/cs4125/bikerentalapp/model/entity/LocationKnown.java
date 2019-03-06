package com.cs4125.bikerentalapp.model.entity;

public class LocationKnown implements BikeLocation {
    @Override
    public String Display(String L) {
        return("Node ID: "+L);
    }
}
