package com.cs4125.bikerentalapp.model.entity;

import com.cs4125.bikerentalapp.model.entity.userLevel.IType;
import com.cs4125.bikerentalapp.model.entity.userLevel.MaintenanceAdmin;
import com.cs4125.bikerentalapp.model.entity.userLevel.PremiumUser;
import com.cs4125.bikerentalapp.model.entity.userLevel.StandardAdmin;
import com.cs4125.bikerentalapp.model.entity.userLevel.StandardUser;

public class UserType {

    IType type;
    int levelInt;
    public UserType(int levelInt) {
        this.levelInt = levelInt;
        switch(levelInt) {
            case 1:
                type = new StandardUser();
            case 2:
                type = new PremiumUser();
            case 3:
                type = new StandardAdmin();
            case 4:
                type = new MaintenanceAdmin();
            default:
                type = new StandardUser();
        }
    }

    public com.cs4125.bikerentalapp.model.entity.userLevel.IType getLevel() { return type; }
    public int getLevelInt() { return levelInt; }

    /*
    STANDARD_CUSTOMER(1),
    PREMIUM_CUSTOMER(2),
    STANDARD_ADMIN(3),
    MAINTENANCE_ADMIN(4);

    private final int value;

    UserType(final int newValue) {
        value = newValue;
    }

    public int getValue() { return value; }
    */
}
