package com.cs4125.bikerentalapp.model.entity.userLevel;

import com.cs4125.bikerentalapp.model.entity.Visitor.Visitor;

public class Maintenance implements IType {

    boolean booleanValue;

    @Override
    public String getType() {
        return "Maintenance Admin";
    }

    @Override
    public void accept(Visitor v) {
        if(v.visitMaintenance(this).equals("true"))
            booleanValue=true;
        else
            booleanValue=false;
    }

    public boolean returnBoolean(){
        return booleanValue;
    }
}
