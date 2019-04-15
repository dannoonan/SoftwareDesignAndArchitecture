package com.cs4125.bikerentalapp.model.entity.Visitor;

import com.cs4125.bikerentalapp.model.entity.userLevel.StandardUser;
import com.cs4125.bikerentalapp.model.entity.userLevel.PremiumUser;

public class DiscountVisitor implements Visitor {

    @Override
    public double visitStandardUser(StandardUser standard) { return 0.05; }

    @Override
    public double visitPremiumUser(PremiumUser premium) { return .1; }
}
