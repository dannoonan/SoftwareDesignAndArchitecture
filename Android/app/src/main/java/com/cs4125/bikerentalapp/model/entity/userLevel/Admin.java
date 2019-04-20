package com.cs4125.bikerentalapp.model.entity.userLevel;

import com.cs4125.bikerentalapp.model.entity.Visitor.Visitor;

public class Admin implements IType{

    Boolean booleanValue;

    @Override
    public String getType() {
        return "Admin";
    }

    @Override
    public void accept(Visitor v) {
        if(v.visitAdmin(this).equals("true"))
            booleanValue=true;
        else
            booleanValue=false;
    }

    public boolean returnBoolean(){
        return booleanValue;
    }
}
