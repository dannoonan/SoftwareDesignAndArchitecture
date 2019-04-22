package com.cs4125.bikerentalapp.model.entity;

public abstract class AbstractBikeInfo {
    protected String name;
    final BikeLocation location;

    AbstractBikeInfo(BikeLocation location){
        this.location=location;
    }

    public abstract String showMessage();
}
