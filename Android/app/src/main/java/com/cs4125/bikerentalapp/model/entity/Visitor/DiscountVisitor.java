package com.cs4125.bikerentalapp.model.entity.Visitor;

import com.cs4125.bikerentalapp.model.entity.userLevel.BronzeUser;
import com.cs4125.bikerentalapp.model.entity.userLevel.GoldUser;
import com.cs4125.bikerentalapp.model.entity.userLevel.SilverUser;

public class DiscountVisitor implements Visitor {
    @Override
    public double visitBronzeUser(BronzeUser bronze) {
        return .05;
    }

    @Override
    public double visitSilverUser(SilverUser silver) {
        return .1;
    }

    @Override
    public double visitGoldUser(GoldUser gold) {
        return .2;
    }
}
