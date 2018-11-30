package com.cs4125.bikerentalapp.model.entity;

public class ReturnDetails {

    private int orderId;
    private int nodeId;
    private int latitude;
    private int longitude;
    private int studentCardId;
    private double amountPaid;

    public static class Builder{
        private int orderId;
        private int nodeId;
        private int latitude;
        private int longitude;
        private int studentCardId;
        private double amountPaid;

        public Builder setStudentCardId(int studentCardId) {
            this.studentCardId = studentCardId;
            return this;
        }

        public Builder setLatitude(int latitude) {
            this.latitude = latitude;
            return this;
        }

        public Builder setLongitude(int longitude) {
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

        public Builder setNodeId(int nodeId) {
            this.nodeId = nodeId;
            return this;
        }

        public ReturnDetails build(){
            ReturnDetails details = new ReturnDetails();

            details.orderId = this.orderId;
            details.latitude = this.latitude;
            details.longitude = this.longitude;
            details.studentCardId = this.studentCardId;
            details.amountPaid = this.amountPaid;

            return details;
        }
    }

    public int getOrderId(){
        return orderId;
    }

    public int getNodeId() {
        return nodeId;
    }

    public int getLatitude() {
        return latitude;
    }

    public int getLongitude() {
        return longitude;
    }

    public int getStudentCardId() {
        return studentCardId;
    }

    public double getAmountPaid() {
        return amountPaid;
    }
}
