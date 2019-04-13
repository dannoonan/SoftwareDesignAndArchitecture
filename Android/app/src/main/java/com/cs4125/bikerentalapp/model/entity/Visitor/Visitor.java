package com.cs4125.bikerentalapp.model.entity.Visitor;

import com.cs4125.bikerentalapp.model.entity.userLevel.StandardUser;
import com.cs4125.bikerentalapp.model.entity.userLevel.PremiumUser;

public interface Visitor {
    double visitStandardUser(StandardUser standard);
    double visitPremiumUser(PremiumUser premium);
}
