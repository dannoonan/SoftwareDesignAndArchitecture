package com.cs4125.bikerentalapp.model.entity.userLevel;

import com.cs4125.bikerentalapp.model.entity.Visitor.Visitor;

public class Maintenance implements IType {

    boolean returnedValue;

    @Override
    public String getType() {
        return "Maintenance Admin";
    }

    @Override
    public void accept(Visitor v) {
        if(v.visitMaintenance(this).equals("true"))
            returnedValue=true;
        else
            returnedValue=false;
    }

    public boolean returnBoolean(){
        return returnedValue;
    }
}
