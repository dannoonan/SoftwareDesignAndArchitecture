package com.cs4125.bikerentalapp.model.entity.userLevel;

import com.cs4125.bikerentalapp.model.entity.Visitor.Visitor;

public class StandardUser implements IType {
    @Override
    public String getType() {
        return "Standard";
    }

    @Override
    public void accept(Visitor v) {
        v.visitStandardUser(this);
    }
}
