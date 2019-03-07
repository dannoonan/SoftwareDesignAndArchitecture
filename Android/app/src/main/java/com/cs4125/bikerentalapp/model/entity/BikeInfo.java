package com.cs4125.bikerentalapp.model.entity;

public class BikeInfo extends  AbstractBikeInfo {
    private String type;
    private String bikeLocation;

    public BikeInfo(String type, String l, BikeLocation location){
        super(location);
        this.type=type;
        this.bikeLocation =l;
    }

    @Override
    public String showMessage() {
        return("Bike Type: "+type+"\n"+location.display(bikeLocation));
    }
}
