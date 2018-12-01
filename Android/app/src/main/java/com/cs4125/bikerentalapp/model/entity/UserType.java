package com.cs4125.bikerentalapp.model.entity;

public enum UserType {
    ADMIN(1),
    CUSTOMER(2),
    DELIVERY_DRIVER(3),
    REPAIR_MAN(4);

    private final int value;

    UserType(final int newValue) {
        value = newValue;
    }

    public int getValue() { return value; }
}
