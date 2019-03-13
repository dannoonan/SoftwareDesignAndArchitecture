package com.cs4125.bikerentalapp.model.entity.userLevel;

import com.cs4125.bikerentalapp.model.entity.Visitor.Visitor;

public class GoldUser implements ILevel {
    @Override
    public String getType() {
        return "Gold";
    }

    @Override
    public void accept(Visitor v) {
        v.visitGoldUser(this);
    }
}
