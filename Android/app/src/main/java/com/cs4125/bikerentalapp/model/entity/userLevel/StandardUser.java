package com.cs4125.bikerentalapp.model.entity.userLevel;

import com.cs4125.bikerentalapp.model.entity.Visitor.Visitor;

public class StandardUser implements IType {

    private boolean booleanValue;

    @Override
    public String getType() {
        return "Standard";
    }

    @Override
    public void accept(Visitor v) {
        if(v.visit(this).equals("true"))
            booleanValue=true;
        else
            booleanValue=false;
    }

    public boolean returnBoolean(){
        return booleanValue;
    }
}
