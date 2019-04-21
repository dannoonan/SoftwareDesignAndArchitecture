package com.cs4125.bikerentalapp.model.entity;

public class RentReturnDetails {


    private int orderId;
    private Integer nodeId;
    private Double latitude;
    private Double longitude;
    private int studentCardId;
    private int userId;
    private int vehicleId;
    private double amountPaid;

    public static class Builder{
        private int userId;
        private int vehicleId;
        private int orderId;
        private Integer nodeId;
        private Double latitude;
        private Double longitude;
        private int studentCardId;
        private double amountPaid;

        public Builder setStudentCardId(int studentCardId) {
            this.studentCardId = studentCardId;
            return this;
        }

        public Builder setUserId(int userId){
            this.userId = userId;
            return this;
        }

        public Builder setVehicleId(int vehicleId){
            this.vehicleId = vehicleId;
            return this;
        }

        public Builder setLatitude(Double latitude) {
            this.latitude = latitude;
            return this;
        }

        public Builder setLongitude(Double longitude) {
            this.longitude = longitude;
            return this;
        }

        public Builder setAmountPaid(double amountPaid) {
            this.amountPaid = amountPaid;
            return this;
        }

        public Builder setOrderId(int orderId) {
            this.orderId = orderId;
            return this;
        }

        public Builder setNodeId(Integer nodeId) {
            this.nodeId = nodeId;
            return this;
        }

        public RentReturnDetails build(){
            RentReturnDetails details = new RentReturnDetails();

            details.orderId = this.orderId;
            details.latitude = this.latitude;
            details.longitude = this.longitude;
            details.studentCardId = this.studentCardId;
            details.userId = this.userId;
            details.vehicleId = this.vehicleId;
            details.amountPaid = this.amountPaid;
            details.nodeId = this.nodeId;

            return details;
        }
    }

    public int getOrderId(){
        return orderId;
    }

    public Integer getNodeId() {
        return nodeId;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public int getStudentCardId() {
        return studentCardId;
    }

    public int getUserId() {
        return userId;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public double getAmountPaid() {
        return amountPaid;
    }
}
