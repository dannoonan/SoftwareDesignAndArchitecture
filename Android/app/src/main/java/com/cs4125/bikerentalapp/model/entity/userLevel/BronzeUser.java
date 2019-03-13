package com.cs4125.bikerentalapp.model.entity.userLevel;

import com.cs4125.bikerentalapp.model.entity.Visitor.Visitor;

public class BronzeUser implements ILevel {
    @Override
    public String getType() {
        return "Bronze";
    }

    @Override
    public void accept(Visitor v) {
        v.visitBronzeUser(this);
    }
}
