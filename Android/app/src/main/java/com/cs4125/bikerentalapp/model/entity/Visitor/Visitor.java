package com.cs4125.bikerentalapp.model.entity.Visitor;

import com.cs4125.bikerentalapp.model.entity.userLevel.Admin;
import com.cs4125.bikerentalapp.model.entity.userLevel.Maintenance;
import com.cs4125.bikerentalapp.model.entity.userLevel.PremiumUser;
import com.cs4125.bikerentalapp.model.entity.userLevel.StandardUser;

public interface Visitor<T> {
    boolean visitStandardUser(StandardUser standard);
    boolean visitPremiumUser(PremiumUser premium);
    boolean visitAdmin(Admin admin);
    boolean visitMaintenance(Maintenance miantenance);
}
