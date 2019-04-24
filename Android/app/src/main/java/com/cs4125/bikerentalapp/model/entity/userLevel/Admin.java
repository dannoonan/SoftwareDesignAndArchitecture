package com.cs4125.bikerentalapp.model.entity.userLevel;

import com.cs4125.bikerentalapp.model.entity.Visitor.Visitor;

public class Admin implements IType{

    private Boolean booleanValue;

    @Override
    public String getType() {
        return "Admin";
    }

    @Override
    public void accept(Visitor v) {
        booleanValue = v.visit(this).equals("true");
    }

    public boolean returnBoolean(){
        return booleanValue;
    }
}
