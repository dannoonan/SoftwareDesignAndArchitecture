package com.cs4125.bikerentalapp.model.entity.Visitor;

import com.cs4125.bikerentalapp.model.entity.userLevel.Admin;
import com.cs4125.bikerentalapp.model.entity.userLevel.Maintenance;
import com.cs4125.bikerentalapp.model.entity.userLevel.StandardUser;
import com.cs4125.bikerentalapp.model.entity.userLevel.PremiumUser;

public class MenuVisitor implements Visitor<String> {

    @Override
    public String visit(StandardUser standard) { return "false"; }

    @Override
    public String visit(PremiumUser premium) { return "false"; }

    @Override
    public String visit(Admin admin) {
        return "true";
    }

    @Override
    public String visit(Maintenance maintenance) {
        return "false";
    }
}
