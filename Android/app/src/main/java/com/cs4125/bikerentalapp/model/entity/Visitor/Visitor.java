package com.cs4125.bikerentalapp.model.entity.Visitor;

import com.cs4125.bikerentalapp.model.entity.userLevel.BronzeUser;
import com.cs4125.bikerentalapp.model.entity.userLevel.GoldUser;
import com.cs4125.bikerentalapp.model.entity.userLevel.SilverUser;

public interface Visitor {
    double visitBronzeUser(BronzeUser bronze);
    double visitSilverUser(SilverUser silver);
    double visitGoldUser(GoldUser gold);
}
