package com.cs4125.bikerentalapp.model.entity;

import com.cs4125.bikerentalapp.model.entity.userLevel.*;

public class UserLevel {
    ILevel level;
    UserLevel(int levelInt) {
        switch(levelInt) {
            case 1:
                level = new BronzeUser();
            case 2:
                level = new SilverUser();
            case 3:
                level = new GoldUser();
            default:
                level = new BronzeUser();
        }
    }

    public ILevel getLevel() { return level; }
}
