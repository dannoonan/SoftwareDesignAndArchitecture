package com.cs4125.bikerentalapp.model.entity.userLevel;

import com.cs4125.bikerentalapp.model.entity.Visitor.Visitor;

public class StandardAdmin implements IType{
    @Override
    public String getType() {
        return "Admin";
    }

    @Override
    public void accept(Visitor v) {

    }
}
