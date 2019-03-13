package com.cs4125.bikerentalapp.model.entity.userLevel;

import com.cs4125.bikerentalapp.model.entity.Visitor.Visitor;

public class SilverUser implements ILevel {
    @Override
    public String getType() {
        return "Silver";
    }

    @Override
    public void accept(Visitor v) {
        v.visitSilverUser(this);
    }
}
