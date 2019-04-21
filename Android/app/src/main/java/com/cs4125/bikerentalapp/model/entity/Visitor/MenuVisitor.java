package com.cs4125.bikerentalapp.model.entity.Visitor;

import com.cs4125.bikerentalapp.model.entity.userLevel.Admin;
import com.cs4125.bikerentalapp.model.entity.userLevel.Maintenance;
import com.cs4125.bikerentalapp.model.entity.userLevel.StandardUser;
import com.cs4125.bikerentalapp.model.entity.userLevel.PremiumUser;

public class MenuVisitor implements Visitor<String> {

    @Override
    public String visitStandardUser(StandardUser standard) { return "false"; }

    @Override
    public String visitPremiumUser(PremiumUser premium) { return "false"; }

    @Override
    public String visitAdmin(Admin admin) {
        return "true";
    }

    @Override
    public String visitMaintenance(Maintenance msintenance) {
        return "false";
    }
}
