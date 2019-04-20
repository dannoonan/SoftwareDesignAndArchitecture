package com.cs4125.bikerentalapp.model.entity.Visitor;

import com.cs4125.bikerentalapp.model.entity.userLevel.Admin;
import com.cs4125.bikerentalapp.model.entity.userLevel.Maintenance;
import com.cs4125.bikerentalapp.model.entity.userLevel.StandardUser;
import com.cs4125.bikerentalapp.model.entity.userLevel.PremiumUser;

public class MenuVisitor implements Visitor {

    @Override
    public boolean visitStandardUser(StandardUser standard) { return false; }

    @Override
    public boolean visitPremiumUser(PremiumUser premium) { return false; }

    @Override
    public boolean visitAdmin(Admin admin) {
        return true;
    }

    @Override
    public boolean visitMaintenance(Maintenance miantenance) {
        return false;
    }
}
