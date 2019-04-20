package com.cs4125.bikerentalapp.model.entity.userLevel;

import com.cs4125.bikerentalapp.model.entity.Visitor.Visitor;

public interface IType {
    String getType();
    void accept(Visitor v);
    boolean returnBoolean();
}
