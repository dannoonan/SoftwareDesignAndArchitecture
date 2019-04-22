package com.cs4125.bikerentalapp.model.entity.userLevel;

import com.cs4125.bikerentalapp.model.entity.Visitor.Visitor;

public class Maintenance implements IType {

    private boolean booleanValue;

    @Override
    public String getType() {
        return "Maintenance Admin";
    }

    @Override
    public void accept(Visitor v) {
        booleanValue = v.visitMaintenance(this).equals("true");
    }

    public boolean returnBoolean(){
        return booleanValue;
    }
}
