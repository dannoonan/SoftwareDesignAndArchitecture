package com.cs4125.bikerentalapp.model.entity;

public class BikeCredential {

    private String bikeType;
    private String nodeId;

    private BikeCredential(){

    }

    //BUILDER PATTERN
    public static class Builder{

        private String bikeType;
        private String nodeId;

        public BikeCredential.Builder setBikeType(String bikeType){
            this.bikeType = bikeType;

            return this;
        }

        public BikeCredential.Builder setNodeId(String nodeId){
            this.nodeId = nodeId;

            return this;
        }

        public BikeCredential build(){
            BikeCredential bikeCredential = new BikeCredential();

            bikeCredential.bikeType = this.bikeType;
            bikeCredential.nodeId = this.nodeId;

            return bikeCredential;
        }
    }

    public String getBikeType(){ return bikeType; }

    public String getNodeId() {
        return nodeId;
    }

    @Override
    public String toString(){
        return "Bike Type: " + bikeType
                + "Node Id: " + nodeId;
    }
}
