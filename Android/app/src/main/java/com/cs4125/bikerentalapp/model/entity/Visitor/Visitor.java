package com.cs4125.bikerentalapp.model.entity.Visitor;

import com.cs4125.bikerentalapp.model.entity.userLevel.Admin;
import com.cs4125.bikerentalapp.model.entity.userLevel.Maintenance;
import com.cs4125.bikerentalapp.model.entity.userLevel.PremiumUser;
import com.cs4125.bikerentalapp.model.entity.userLevel.StandardUser;

public interface Visitor<T> {
    T visitStandardUser(StandardUser standard);
    T visitPremiumUser(PremiumUser premium);
    T visitAdmin(Admin admin);
    T visitMaintenance(Maintenance miantenance);
}
