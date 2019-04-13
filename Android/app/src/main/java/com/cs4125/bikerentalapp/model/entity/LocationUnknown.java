package com.cs4125.bikerentalapp.model.entity;

public class LocationUnknown implements BikeLocation {
    @Override
    public String display(String L) {
        return("Longitude and Latitude: "+L);
    }
}
