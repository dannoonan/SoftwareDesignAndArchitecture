package com.cs4125.bikerentalapp.model.entity;

import com.cs4125.bikerentalapp.model.entity.userLevel.IType;
import com.cs4125.bikerentalapp.model.entity.userLevel.Maintenance;
import com.cs4125.bikerentalapp.model.entity.userLevel.PremiumUser;
import com.cs4125.bikerentalapp.model.entity.userLevel.Admin;
import com.cs4125.bikerentalapp.model.entity.userLevel.StandardUser;

public class UserType {

    private final IType type;
    private int levelInt;
    public UserType(int levelInt) {
        this.levelInt = levelInt;
        switch(levelInt) {
            case 1:
                type = new StandardUser();
                break;
            case 2:
                type = new PremiumUser();
                break;
            case 3:
                type = new Admin();
                break;
            case 4:
                type = new Maintenance();
                break;
            default:
                type = new StandardUser();
                break;
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
