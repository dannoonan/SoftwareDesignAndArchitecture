package com.cs4125.bikerentalapp.model.entity;

public enum UserType {
    STANDARD_CUSTOMER(1),
    PREMIUM_CUSTOMER(2),
    STANDARD_ADMIN(3),
    MAINTENANCE_ADMIN(4);

    private final int value;

    UserType(final int newValue) {
        value = newValue;
    }

    public int getValue() { return value; }
}
