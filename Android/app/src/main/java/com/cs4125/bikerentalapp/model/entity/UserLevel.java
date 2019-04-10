package com.cs4125.bikerentalapp.model.entity;

import com.cs4125.bikerentalapp.model.entity.userLevel.*;

public class UserLevel {
    IType type;
    UserLevel(int levelInt) {
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

    public IType getLevel() { return type; }
}
