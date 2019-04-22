package com.cs4125.bikerentalapp.model.entity.userLevel;

import com.cs4125.bikerentalapp.model.entity.Visitor.Visitor;

public class PremiumUser implements IType {

    private boolean booleanValue;

    @Override
    public String getType() {
        return "Silver";
    }

    @Override
    public void accept(Visitor v) {
        booleanValue = v.visitPremiumUser(this).equals("true");
    }

    public boolean returnBoolean(){
        return booleanValue;
    }
}
